Secure Android App with Firebase Google Sign-In
This project is a clean, modern Android application demonstrating a robust user authentication flow using Firebase and Google Sign-In. It's designed as a template for apps that require a protected, authenticated user experience.

✨ Overview & Technologies
This app features:

User-friendly Google Sign-In powered by the Play Services API.

Secure user session management with Firebase Authentication.

A two-activity architecture (LoginScreen and MainActivity) for a clear separation of concerns.

Automated redirection for a seamless user experience upon login.

A modern UI built with ConstraintLayout and CardView, featuring a user profile display loaded with Glide.

⚙️ Quick Setup
Firebase Project: Create a new project on the Firebase Console, add your Android app, and enable the Google sign-in provider in the Authentication section.

google-services.json: Download the file and place it in your app/ directory.

Dependencies: Add the following to your app/build.gradle file:

implementation 'com.google.firebase:firebase-auth:22.0.0'
implementation 'com.google.android.gms:play-services-auth:20.5.0'
implementation 'androidx.cardview:cardview:1.0.0'
implementation 'com.github.bumptech.glide:glide:4.12.0'
annotationProcessor 'com.github.bumptech.glide:compiler:4.12.0'

strings.xml: Add your web client ID from google-services.json (the one with client_type: 3) to strings.xml.

<string name="default_web_client_id">YOUR_UNIQUE_WEB_CLIENT_ID_HERE</string>

Ensure you copy the entire string, including the .apps.googleusercontent.com suffix.

📂 Architecture
The app uses LoginScreen as the initial entry point. If a user is not authenticated, they see the sign-in button. Once authenticated, they are automatically navigated to MainActivity, which serves as the main content screen. MainActivity is protected and redirects unauthenticated users back to LoginScreen.

🚀 Future Enhancements
Support for additional authentication methods (e.g., email/password).

Add a loading indicator during the authentication process.

Implement data persistence with Firestore.

📄 License
This project is licensed under the MIT License.
