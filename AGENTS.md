# MyApplication2

Kotlin Multiplatform project with Compose Multiplatform UI.

## Commands

- `./gradlew :composeApp:build` — build all targets
- `./gradlew :composeApp:assembleDebug` — build Android APK
- `./gradlew :composeApp:run` — run Desktop app

## Coding Standards

- Shared code goes in `commonMain`. Platform-specific code uses `expect`/`actual` declarations.
- Add dependencies via `gradle/libs.versions.toml`, then reference in `composeApp/build.gradle.kts` sourceSets block.
- Use Compose Multiplatform resources (`composeApp/src/commonMain/composeResources/`), not platform-specific resource systems.

## Pitfalls

- Don't add platform-specific dependencies (Android, JVM, etc.) to `commonMain` — they won't compile on other targets.
- Don't edit files inside `iosApp/*.xcodeproj` manually — use Xcode to modify project settings.
- `actual` implementations must exist for every target that has a corresponding `expect` declaration — missing one breaks compilation on that target.
