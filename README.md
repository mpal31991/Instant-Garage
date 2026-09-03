# Instant Garage App

Instant Garage is an Android application that helps users find nearby mechanics and request vehicle service. The app provides a simple interface to browse mechanics, view their details, and submit a service request.



## Setup Instructions

### Requirements

- Android Studio
- Android SDK 36
- Minimum Android version: Android 7.0 (API 24)
- Internet connection

### Installation

Follow these steps to run the project locally:

1. **Clone the repository**

   ```bash
   git clone https://github.com/mpal31991/Instant-Garage.git
   ```

2. **Open the project**
- Open Android Studio
- Click Open an Existing Project
- Select the cloned Instant-Garage folder

3. **Sync dependencies**
- Let Gradle sync complete automatically

4. **Run the application**
- Connect an Android device or start an Android Emulator
- Click Run in Android Studio



## API / Data Details

The application uses a custom REST API created using **JSONBin.io**.

### Data Source

For this project, a dummy JSON dataset containing information for 20 garages was created for demonstration purposes. The dataset was then uploaded to JSONBin.io to create a custom JSON bin.

JSONBin.io provides REST API endpoints that allow the application to access the data stored in the bin.

### API Configuration

- **Data Hosting:** JSONBin.io
- **API Type:** REST API
- **HTTP Client:** Retrofit
- **JSON Parser:** Gson
- **Request Method:** GET

### Mechanic Data

The dataset contains information about 20 garages, including:

- Mechanic ID
- Garage/Mechanic name
- Location
- Contact information
- Services offered
- Availability

### API Requests

The application uses the JSONBin API to retrieve mechanic data.

**Fetch all mechanics**

Retrieves the complete list of available mechanics from the JSON bin.

**Fetch mechanic by ID**

Retrieves a specific mechanic based on the selected mechanic ID. The ID is dynamically passed through the `X-JSON-Path` request header.

Example:

```text
X-JSON-Path: mechanics[?(@.id==5)]
```
Here, 5 represents the ID of the mechanic requested by the application.
### Data Flow

```text
Custom Dummy JSON Data
        ↓
     JSONBin.io
        ↓
   REST API Request
        ↓
     Retrofit
        ↓
    Repository
        ↓
     ViewModel
        ↓
Jetpack Compose UI
```



## Architecture Explanation

The application follows the **MVVM (Model-View-ViewModel)** architecture to separate the user interface, business logic, and data handling.

### UI Layer

The UI layer is built using **Jetpack Compose**. It contains the application's screens and composables responsible for displaying data and handling user interactions.

### ViewModel Layer

ViewModels manage the UI state and business logic. They request data from the repository and expose the required state to the Compose UI.

### Repository Layer

The repository acts as a bridge between the ViewModel and the remote data source. It handles API requests and provides mechanic data to the ViewModel.

### Remote Data Layer

The remote data layer contains the Retrofit API service responsible for communicating with the JSONBin REST API. Gson is used to convert JSON responses into Kotlin data models.

### Architecture Flow

```text
Jetpack Compose UI
        ↓
     ViewModel
        ↓
    Repository
        ↓
   Retrofit API Service
        ↓
     JSONBin.io
```



## Screenshots
<p float="left">
   <img width="200" src="https://github.com/user-attachments/assets/6c5b92f3-adc3-4120-aa01-ec4d0a93a8f0" />
   <img width="200" src="https://github.com/user-attachments/assets/486868fb-a673-488a-a60c-4e433bf09e0e" />
   <img width="200" src="https://github.com/user-attachments/assets/d107d9ea-3b41-4462-abf7-e7f0c4d4a2c1" />
   <img width="200" src="https://github.com/user-attachments/assets/567d16b4-f0df-45ac-9dad-c05d035cea39" />
   <img width="200" src="https://github.com/user-attachments/assets/93affe85-120e-4fe0-bc6f-dfa79cd88401" />
</p>



## Demo Video

A short demonstration of the Instant Garage application is available below.

[▶ Watch the Demo Video](https://drive.google.com/file/d/1QYK5M27ewvWnWTPU95xGts3ErD1QzYBr/view?usp=drivesdk)



## Assumptions & Additional Features

### Assumptions

- The application uses dummy garage data created specifically for this assignment.
- Garage information is stored in a custom JSONBin.io data bin and fetched through REST APIs.
- The garage data is static and does not represent real garages or real-time service availability.
- User authentication, real-time garage availability, live location tracking, payment integration, and backend-based service request management are outside the scope of the current implementation.

### Additional Features

- Clean and modern UI built using Jetpack Compose.
- MVVM architecture for better separation of concerns and maintainability.
- Hilt for dependency injection and efficient management of dependencies.
- Users are prevented from requesting a service when a garage is closed.
- Retrofit for REST API integration.
- Responsive UI designed using modern Android development practices.
- Well-structured and modular codebase for improved readability and maintainability.
