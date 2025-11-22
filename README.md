# Mobile Blog App (Pocket Concept)

A modern Android application built with Jetpack Compose, featuring a unique "Pocket" blueprint aesthetic.

## 📱 Features
- **Unique UI Design**: Dark blue, blueprint-style interface inspired by "Pocket" concepts.
- **Custom Components**: Bespoke cards, search bars, and buttons designed for the theme.
- **Modern Tech Stack**: Built entirely with Kotlin and Jetpack Compose Material3.
- **Navigation**: Seamless navigation using `navigation-compose`.

## 🛠 Prerequisites
- **JDK**: Java 17 or higher.
- **Android SDK**: API Level 34 (UpsideDownCake).
- **Gradle**: Wrapper included (v8.2).

## 🏗 Building the Application
To build the debug APK, run the following command in the project root:

```bash
./gradlew assembleDebug
```

The output APK will be located at:
`app/build/outputs/apk/debug/app-debug.apk`

## 🧪 Running Tests
To run unit tests:
```bash
./gradlew testDebugUnitTest
```

To run lint checks:
```bash
./gradlew lintDebug
```

## 📲 Installation
### Using ADB
You can install the app on a connected device or emulator (like Genymotion) using ADB:

```bash
# List connected devices
adb devices

# Install the APK (replace <device-id> with your device ID if multiple are connected)
adb install -r app/build/outputs/apk/debug/app-debug.apk
```

### Using Gradle
Alternatively, you can install directly via Gradle if a device is connected:

```bash
./gradlew installDebug
```

## 🎨 Design System
The app uses a custom theme defined in `ui/theme`:
- **Colors**: Monochromatic blue palette (`#050A0E`, `#5C80A0`).
- **Typography**: Monospace font family for a technical/terminal look.
- **Components**: Custom `PocketCard`, `PocketSearchBar`, and `PocketButton`.

## 📂 Project Structure
- `app/src/main/java/com/example/blogapp/ui`: UI components and screens.
- `app/src/main/java/com/example/blogapp/data`: Mock data repository.
- `app/src/main/java/com/example/blogapp/model`: Data models.
