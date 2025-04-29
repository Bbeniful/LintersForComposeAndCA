# This is a demo for writing custom Linters for Android.

- I used lint plugin
### Use it in a module
```kotlin
lintChecks(projects.lintRules)
```

# Composable ```testTag("")```
- This linter checks if the composable function uses ```.testTag("")```. We use this tag to be able to find the element locators, or when we write native component tests.
- Created a separated annotation if we want to skip test tag, also a custom Modifier if we want to skip it just for a simple view.
  
### Composable function checks for ```.testTag("")``` is missing
<img width="991" alt="image" src="https://github.com/user-attachments/assets/b92fe77e-cb80-427a-947e-3cbeb1519af8" />

### Composable views check for ```.testTag("")``` is missing
<img width="699" alt="image" src="https://github.com/user-attachments/assets/1d600fac-f593-4881-9f12-2c82626eb858" />

### Clean Architecture proper implementation check
<img width="780" alt="image" src="https://github.com/user-attachments/assets/8b04fffe-a0bb-4fa6-8a80-2db88bdc8dcf" />

### Hungarian notation cgeck with quickFix
<img width="772" alt="image" src="https://github.com/user-attachments/assets/14fd1c1b-7e0f-4d88-8aac-4a334633e2e9" />
