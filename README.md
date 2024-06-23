# cap-activity-demo-plugin

This is a simple demo of how the `appRestoredResult` event is called in a custom Capacitor plugin, when the main activity is restored after being killed by the system. If you check out the `ActivityDemoPlugin` @ `android/src/main/java/io/ionic/cap/plugin/activity/demo/ActivityDemoPlugin.java`, you'll see that it's configured to launch a an Actvitity to choose a contact, then return the contact's name and phone number. The `ng-example` app is configured to listen for the `appRestoredResult` event, and display the contact's name and phone number when the event is called. The `Camera` plugin is also included in the `ng-example` app, to demonstrate that the `appRestoredResult` event is only called when the main activity is restored.

The callback of the `appRestoredResult` event will display the result of either plugin that's called.

**Please note:** You'll need to take specific steps to test the plugin, as described in the [Special Notes](#special-notes) section below.

## Install

```bash
# Install dependencies for the plugin, then build it
npm install
npm run build

# Install dependencies in the ng-example app
cd ./ng-example
npm install
ionic build
npx cap sync

# Try it out!
npx cap run android
```

## Special Notes

To test the plugin, you can try either of the following methods to kill the app and simulate the main activity being restored:

- Before launching the app, enable the [Don't Keep Activities](https://developer.android.com/studio/debug/dev-options) developer option to simulate the app being killed by the system. This option is found in the Developer Options section of the device settings.
- Open the custom plugin's activity by tapping the `Pick` button, then while the list of contacts is visible, kill the app by running `adb shell am kill io.ionic.starter` in the terminal. This will simulate the app being killed by the system.

## API

<docgen-index>

* [`echo(...)`](#echo)
* [`pick(...)`](#pick)
* [Interfaces](#interfaces)

</docgen-index>

<docgen-api>
<!--Update the source file JSDoc comments and rerun docgen to update the docs below-->

### echo(...)

```typescript
echo(options: { value: string; }) => Promise<{ value: string; }>
```

| Param         | Type                            |
| ------------- | ------------------------------- |
| **`options`** | <code>{ value: string; }</code> |

**Returns:** <code>Promise&lt;{ value: string; }&gt;</code>

--------------------


### pick(...)

```typescript
pick(options: { value: string; }) => Promise<ActivityDemoPickResult>
```

| Param         | Type                            |
| ------------- | ------------------------------- |
| **`options`** | <code>{ value: string; }</code> |

**Returns:** <code>Promise&lt;<a href="#activitydemopickresult">ActivityDemoPickResult</a>&gt;</code>

--------------------


### Interfaces


#### ActivityDemoPickResult

| Prop        | Type                |
| ----------- | ------------------- |
| **`value`** | <code>string</code> |
| **`name`**  | <code>string</code> |
| **`phone`** | <code>string</code> |

</docgen-api>
