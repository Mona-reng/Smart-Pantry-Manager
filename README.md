# Smart Pantry Manager

## 1. Project Overview

Smart Pantry Manager is an Android application designed to help users manage ingredients stored in their pantry and discover meal ideas based on available ingredients.

The application provides a simple interface for managing pantry items, viewing saved ingredients, editing ingredient information, and generating recipe suggestions. It also includes a Settings screen for basic application information.

## 2. Project Objectives

The main objectives of Smart Pantry Manager are to:

- Help users keep track of ingredients in their pantry.
- Allow users to add, view, and edit pantry ingredients.
- Store ingredient information locally.
- Suggest meal ideas based on ingredients available in the pantry.
- Provide a simple and user-friendly Android interface.
- Organize the application into separate screens for easier navigation.

## 3. Features

### Home Screen
- Provides access to the main application features.
- Includes navigation to My Pantry, Add Ingredient, Suggested Recipes, and Settings.

### My Pantry
- Displays saved pantry ingredients.
- Allows users to view and edit ingredient information.
- Provides a separate screen for pantry management.

### Add Ingredient
- Allows users to enter new pantry ingredients.
- Stores ingredient information in the local database.

### Suggested Recipes
- Checks the ingredients saved in the pantry.
- Displays meal ideas based on supported ingredients.
- Includes a Find Recipes button to generate suggestions.

### Settings
- Displays basic application information.
- Includes navigation back to the home screen.

## 4. Technologies Used

- **Programming Language:** Java
- **Development Environment:** Android Studio
- **User Interface:** XML layouts
- **Database:** SQLite
- **Android Components:** Activities, Intents, and RecyclerView
- **Version Control:** Git and GitHub
- **Build System:** Gradle

## 5. Application Structure

The project is organized into the following main components:

- `MainActivity.java` — Main screen and application navigation.
- `MyPantryActivity.java` — Displays pantry ingredients.
- `AddEditIngredientActivity.java` — Handles adding and editing ingredients.
- `SuggestedRecipesActivity.java` — Generates recipe suggestions.
- `SettingsActivity.java` — Displays application settings and information.
- `Database/DatabaseHelper.java` — Manages local database operations.
- `Adapter/Ingredientadapter.java` — Displays pantry ingredients in a RecyclerView.
- `Adapter/Recipeadapter.java` — Provides an adapter for recipe data.
- `Model/Ingredient.java` — Represents ingredient data.
- `Model/Recipe.java` — Represents recipe data.

The application layouts are stored in the `app/src/main/res/layout/` directory.

## 6. Database

Smart Pantry Manager uses SQLite for local data storage.

The database supports storing and retrieving pantry ingredient information. It also supports updating and deleting ingredient records through database operations.

The database allows saved pantry information to remain available between normal application sessions.

## 7. Installation and Setup

### Requirements

- Android Studio
- Java development support
- Android SDK
- An Android emulator or compatible Android device

### Steps

1. Clone or download the Smart Pantry Manager repository from GitHub.
2. Open Android Studio.
3. Select **Open** and choose the project folder.
4. Allow Gradle to synchronize the project.
5. Connect an Android device or start an emulator.
6. Click **Run** to install and launch the application.

## 8. How to Use the Application

1. Launch Smart Pantry Manager.
2. Select **Add Ingredient** to enter an ingredient and its details.
3. Open **My Pantry** to view saved ingredients.
4. Edit an ingredient when its information needs to change.
5. Open **Suggested Recipes** and select **Find Recipes** to view available meal ideas.
6. Open **Settings** to view basic application information.

## 9. Testing

The following application functions have been tested during development:

- Opening the home screen.
- Navigating between application screens.
- Viewing saved pantry ingredients.
- Editing pantry ingredients.
- Displaying recipe suggestions.
- Opening the Settings screen.
- Returning to the home screen.

Further testing can include ingredient deletion, invalid input handling, expiry-date checks, and recipe matching for additional ingredients.

## 10. Future Improvements

Possible future enhancements include:

- Ingredient expiry alerts and notifications.
- More recipes and detailed cooking instructions.
- Improved recipe matching using multiple available ingredients.
- Ingredient quantity and unit validation.
- Recipe categories and search functionality.
- A more polished user interface.
- Additional automated tests.

## 11. Project Status

Smart Pantry Manager is an Android application under development. Core pantry management, recipe suggestion, navigation, and basic settings functionality have been implemented.

## 12. Author

Developed as a programming project using Android Studio, Java, XML, and SQLite.

## 13. License

This project was developed for educational purposes. No specific open-source license has been assigned.
