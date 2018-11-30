![alt text](https://github.com/mumayank/AirDialog/blob/master/image.png "Logo")

# AirDialog 

An android library written in Kotlin which makes creating new dialogs in android a piece of cake!

###### (method counts ~50, size ~50KB)

[![](https://jitpack.io/v/mumayank/AirDialog.svg)](https://jitpack.io/#mumayank/AirDialog)

## Usage
```kotlin
AirDialog.show(
                activity = this,                      // mandatory
                title = "This is title",              // mandatory
                message = "This is message",          // mandatory
                iconDrawableId = R.drawable.icon,
                isCancelable = false,
                airButton1 = AirDialog.Button("OK") {
                        // do something
                },
                airButton2 = AirDialog.Button("Cancel") {
                        // do something
                },
                airButton3 = AirDialog.Button("Retry") {
                        // do something
                }
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
