package io.ionic.cap.plugin.activity.demo;

import static android.app.Activity.RESULT_OK;

import android.Manifest;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;

import androidx.activity.result.ActivityResult;

import com.getcapacitor.JSObject;
import com.getcapacitor.PermissionState;
import com.getcapacitor.Plugin;
import com.getcapacitor.PluginCall;
import com.getcapacitor.PluginMethod;
import com.getcapacitor.annotation.ActivityCallback;
import com.getcapacitor.annotation.CapacitorPlugin;
import com.getcapacitor.annotation.Permission;
import com.getcapacitor.annotation.PermissionCallback;

@CapacitorPlugin(name = "ActivityDemo", permissions = {
        @Permission(
                alias = "contacts",
                strings = {
                        Manifest.permission.READ_CONTACTS
                })
})
public class ActivityDemoPlugin extends Plugin {
    private String contactSaved;
    private ActivityDemo implementation = new ActivityDemo();

    @PluginMethod
    public void echo(PluginCall call) {
        String value = call.getString("value");

        JSObject ret = new JSObject();
        ret.put("value", implementation.echo(value));
        call.resolve(ret);
    }

    @PluginMethod
    public void pick(PluginCall call) {
        if (getPermissionState("contacts") != PermissionState.GRANTED) {
            requestPermissionForAlias("contacts", call, "contactsPermsCallback");
        } else {
            doPick(call);
        }
    }

    @PermissionCallback
    private void contactsPermsCallback(PluginCall call) {
        if (getPermissionState("contacts") == PermissionState.GRANTED) {
            doPick(call);
        } else {
            call.reject("Permission is required to take a picture");
        }
    }

    private void doPick(PluginCall call) {
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType(ContactsContract.Contacts.CONTENT_TYPE);

        // Start the Activity for result using the name of the callback method
        startActivityForResult(call, intent, "pickContact");
    }

    @ActivityCallback
    private void pickContact(PluginCall call, ActivityResult result) {
        if (call == null) {
            return;
        }

        if(result.getResultCode() == RESULT_OK) {
            Uri contactUri = result.getData().getData();
            JSObject ret = getContactResponseObject(contactUri);
            contactSaved = contactUri.toString();
            call.resolve(ret);
        }
        // Do something with the result data
    }
    
    private JSObject getContactResponseObject(Uri contactUri) {
        JSObject ret = new JSObject();
        try {
            String[] projection = {
                    ContactsContract.Contacts._ID,
                    ContactsContract.Contacts.DISPLAY_NAME
            };

            Cursor cursor = getActivity().getApplicationContext().getContentResolver().query(contactUri, projection, null, null, null);

            if (cursor != null && cursor.moveToFirst()) {
                // Retrieve the contact ID and name
                int contactIdIndex = cursor.getColumnIndex(ContactsContract.Contacts._ID);
                String contactId = cursor.getString(contactIdIndex);
                int contactNameIndex = cursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME);
                String contactName = cursor.getString(contactNameIndex);

                // Close the cursor
                cursor.close();

                // Use the contact ID to query for the phone number
                Cursor phoneCursor = getActivity().getApplicationContext().getContentResolver().query(
                        ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                        new String[]{ContactsContract.CommonDataKinds.Phone.NUMBER},
                        ContactsContract.CommonDataKinds.Phone.CONTACT_ID + " = ?",
                        new String[]{contactId},
                        null
                );

                if (phoneCursor != null && phoneCursor.moveToFirst()) {
                    // Retrieve the phone number
                    int phoneIndex = phoneCursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER);
                    String phoneNumber = phoneCursor.getString(phoneIndex);
                    Log.d("Contact Info", "Name: " + contactName + ", Phone Number: " + phoneNumber);
                    ret.put("phone", phoneNumber);
                    // Close the phone cursor
                    phoneCursor.close();
                }

                ret.put("value", contactUri.toString());
                ret.put("name", contactName);

            }


            return  ret;
        } catch (Exception e) {
            throw e;
        }

    }
}
