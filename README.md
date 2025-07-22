# NASA Picture of the Day MVVM

An Android application that displays NASA's Astronomy Picture of the Day (APOD) using modern Android development practices and MVVM architecture.

## Features

- Fetch and display the NASA Astronomy Picture of the Day
- View detailed information including title, date, explanation, and image
- Support for both images and videos from NASA
- Clean architecture with separation of concerns

## Architecture

This project follows the MVVM (Model-View-ViewModel) architecture pattern along with Clean Architecture principles:

- **Data Layer**: API service, repository implementation
- **Domain Layer**: Repository interface, use cases
- **Presentation Layer**: View models, UI states, composables/activities/fragments

## Technologies Used

- **Kotlin**: 100% Kotlin codebase
- **Compose-based UI**: In compose branch
- **Coroutines & Flow**: For asynchronous operations
- **Retrofit**: For network requests
- **Hilt**: For dependency injection
- **ViewModel & LiveData/StateFlow**: For reactive UI updates
- **Material Design**: For UI components

## Setup

1. Clone this repository
2. Get a NASA API key from [NASA API](https://api.nasa.gov/)
3. Add your API key to the project:
   - Create a `local.properties` file in the project root if it doesn't exist
   - Add `NASA_API_KEY="YOUR_API_KEY"` to the file
4. Build and run the app

## API Key

This app uses the NASA APOD API which requires an API key. You can obtain a free key at [https://api.nasa.gov/](https://api.nasa.gov/).

## Screenshots
<img width="300" height="667" alt="screenShot" src="https://github.com/user-attachments/assets/fe0fae62-fea6-4d0c-86b7-e0e3f8f1b917" />
