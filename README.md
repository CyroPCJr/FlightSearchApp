# Flight Search App

This project aims to put into practice the knowledge acquired during [unit 6 google basic Android](https://developer.android.com/codelabs/basic-android-kotlin-compose-flight-search#0) , with a focus on relational databases, SQL, Room, and Preferences DataStore. The flight search app will allow users to consult destinations from specific airports, managing their favorite routes and saving the search status. 

## 📱 Features: 
- **Airport search field:** Allows the user to enter the name of an airport or use the International Air Transport Association (IATA) identifier. 
- **Auto-fill suggestions:** The app will consult the database as the user types to provide airport suggestions.
- **List of available flights:** After selecting an airport, the app will display a list of available flights, showing the IATA identifier and the name of the destination airport.
- **Save favorite routes:** The user can mark individual routes as favorites for quick access. 
- **Display favorite routes:** If there is no active search, the app will display all previously saved favorite routes. 
- **Save search status:** The search text will be stored using the Preferences DataStore. When the user reopens the app, the search field will be filled in automatically with the appropriate results.

## 📖 Libraries:
- **Room**: Integration of a relational database into the app to store flight and airport information. 
- **SQL**: Use of SQL for database queries, including auto-fill suggestions. 
- **Preferences DataStore**: Storage of preferences and data such as search text to improve the user experience. 
- **Jetpack Compose**: Creation of modern, reactive interfaces for Android, with the possibility of reviewing your UI skills.

## 🚀 Project Structure
The app was developed from scratch and uses a pre-filled [database](https://github.com/google-developer-training/android-basics-kotlin-sql-basics-app/tree/project) that we provided to simplify the process. The main focus of the project is to ensure that you practice development with Room, DataStore and Compose.

### Implementation requirements 
1. Text field for airport search (by name or IATA code). 
2. Airport suggestions based on user input. 
3. Generation of a list of flights from a selected source airport. 
4. Option to bookmark and display favorite routes.
5. Saving and restoring the search via the Preferences DataStore. 

## 📝 Remarks 
- The focus of the project is on the efficient use of Room, SQL and DataStore.
- The user interface will be developed using Jetpack Compose, which will allow you to improve your UI skills and work on reactive and dynamic designs.
