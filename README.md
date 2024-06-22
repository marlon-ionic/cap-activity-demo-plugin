# cap-activity-demo-plugin

This is a simple demo of how the `appRestoredResult` event is called in a Capacitor plugin, when the main activity is restored after being killed by the system.

## Install

```bash
# Install dependencies for the plugin, then build it
npm install cap-activity-demo-plugin
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

To test the plugin, you can try either:

- Use the [Don't Keep Activities](https://developer.android.com/studio/debug/dev-options) developer option to simulate the app being killed by the system. This option is found in the Developer Options section of the device settings.
- Kill the app by running `adb shell am kill com.example.app` in the terminal. This will simulate the app being killed by the system.

If you choose either button on the homepage, you should see the `appRestoredResult` event being called when you return to the example app.



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
