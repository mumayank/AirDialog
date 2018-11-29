![alt text](https://github.com/mumayank/AirDialog/blob/master/image.png "Logo")

# AirDialog 

An android library written in Kotlin which makes creating new dialogs in android a piece of cake!

Method counts ~50

Size ~50KB

[![](https://jitpack.io/v/mumayank/AirDialog.svg)](https://jitpack.io/#mumayank/AirDialog)

## Usage
```kotlin
AirDialog.show(
                activity = this,
                title = "This is title",
                message = "This is message",
                iconDrawableId = R.drawable.notification_icon_background,
                isCancelable = false,
                airButton1 = AirDialog.Button(
                    textOnButton = "OK",
                    onClick = fun() {
                        // do something
                    }
                ),
                airButton2 = AirDialog.Button(
                    textOnButton = "Cancel",
                    onClick = fun() {
                        // do something
                    }
                ),
                airButton3 = AirDialog.Button(
                    textOnButton = "Retry",
                    onClick = fun() {
                        // do something
                    }
                )
            )
```

## Setup
Add this line in your root build.gradle at the end of repositories:

```gradle
allprojects {
  repositories {
    ...
    maven { url 'https://jitpack.io' } // this line
  }
}
  ```
Add this line in your app build.gradle:
```gradle
dependencies {
  implementation 'com.github.mumayank:AirDialog:LATEST_VERSION' // this line
}
```
where LATEST_VERSION is [![](https://jitpack.io/v/mumayank/AirDialog.svg)](https://jitpack.io/#mumayank/AirDialog)

That's all!
