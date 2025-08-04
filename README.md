# 📝 TodoApp

A modern Android TODO list application built with Kotlin, Jetpack Compose, and Room Database. This project demonstrates Clean Architecture principles, state-of-the-art UI animations, and best practices in Android development.

## ✨ Features

- ➕ **Add Tasks**: Create new tasks with title and description validation
- ✏️ **Edit Tasks**: Modify existing tasks with real-time validation
- 🗑️ **Delete Tasks**: Remove tasks with confirmation dialogs
- 🎨 **Smooth Animations**: Beautiful transitions and UI animations
- 💾 **Persistent Storage**: Local data storage using Room Database
- 🎯 **Material Design 3**: Modern UI following Material Design guidelines
- 📱 **Responsive Design**: Optimized for different screen sizes
- ⚡ **Performance**: Efficient state management and data flow

## 🏗️ Architecture

This project follows **Clean Architecture** principles with a multi-module setup:

### Modules
- **🎯 App**: Application entry point and dependency injection setup
- **🎨 Presentation**: UI layer with Jetpack Compose and ViewModels
- **💾 Data**: Data persistence with Room Database and Repository pattern

### Architecture Layers
- **Presentation Layer**: Handles UI components, state management, and user interactions
- **Domain Layer**: Contains business logic, validation rules, and use cases
- **Data Layer**: Manages data sources, database operations, and repository implementations

## 🛠️ Tech Stack

### Core Technologies
- **Kotlin**: 100% Kotlin codebase
- **Jetpack Compose**: Modern declarative UI toolkit
- **Room Database**: Local SQLite database with type-safe queries
- **Dagger Hilt**: Dependency injection framework
- **Coroutines & Flow**: Asynchronous programming and reactive streams

### Architecture Components
- **ViewModel**: UI-related data holder with lifecycle awareness
- **StateFlow**: Reactive state management
- **Repository Pattern**: Abstraction layer for data access
- **State Holder Pattern**: Centralized UI state management

### UI & Animations
- **Material Design 3**: Latest Material Design components
- **Compose Animations**: Smooth transitions and micro-interactions
- **Edge-to-Edge**: Modern full-screen experience

## 🧪 Testing

- **Unit Tests**: Comprehensive testing for ViewModels and state holders
- **Mockito**: Mocking framework for dependency testing
- **JUnit**: Testing framework for business logic validation

## 🚀 Getting Started

### Prerequisites
- Android Studio Hedgehog (2023.1.1) or later
- Kotlin 2.2.0+
- Android SDK 26+ (API level 26)
- JDK 17

### Installation

1. **Clone the repository**
   ```bash
   git clone https://github.com/yourusername/TodoApp.git
   ```

2. **Open in Android Studio**
   - Launch Android Studio
   - Select "Open an existing project"
   - Navigate to the cloned repository

3. **Sync and Build**
   - Wait for Gradle sync to complete
   - Build the project (Ctrl+F9 / Cmd+F9)

4. **Run the App**
   - Connect an Android device or start an emulator
   - Click the Run button or press Shift+F10

## 📁 Project Structure

```
TodoApp/
├── app/                          # Application module
│   ├── src/main/java/            # Main source code
│   └── build.gradle.kts          # App-level build configuration
├── data/                         # Data layer module
│   ├── src/main/java/            # Data sources, DAOs, repositories
│   └── build.gradle.kts          # Data module build configuration
├── presentation/                 # Presentation layer module
│   ├── src/main/java/            # UI components, ViewModels, state holders
│   ├── src/test/java/            # Unit tests
│   └── build.gradle.kts          # Presentation module build configuration
├── gradle/                       # Gradle wrapper and version catalogs
├── build.gradle.kts              # Project-level build configuration
└── settings.gradle.kts           # Project settings
```

## 🎯 Key Highlights

- **State Management**: Robust state handling with StateFlow and state holder pattern
- **Validation**: Real-time form validation with clear error messaging
- **Animations**: Polished user experience with smooth transitions
- **Testing**: Well-tested codebase with unit tests for critical components
- **Modular Design**: Clean separation of concerns across multiple modules
- **Performance**: Optimized for smooth performance and minimal memory usage

## 📄 License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

## 🤝 Contributing

Contributions, issues, and feature requests are welcome! Feel free to check the [issues page](../../issues).

---

**Built with ❤️ using modern Android development practices**
