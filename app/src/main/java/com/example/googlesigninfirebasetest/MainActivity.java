package com.example.googlesigninfirebasetest;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


public class MainActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private GoogleSignInClient mGoogleSignInClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // This is where 'user' is declared and initialized
        mAuth = FirebaseAuth.getInstance();
        FirebaseUser user = mAuth.getCurrentUser();
        // Check if the user is already signed in. If not, redirect to LoginScreen.
        if (user == null) {
            goToLoginScreen();
        } else {
            // User is signed in. Proceed to set up the main screen content.

            // Configure Google Sign In to get the client for sign-out
            GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                    .requestIdToken(getString(R.string.default_web_client_id))
                    .requestEmail()
                    .build();
            mGoogleSignInClient = GoogleSignIn.getClient(this, gso);

            // Find the views from the new, redesigned XML layout
            TextView nameTextView = findViewById(R.id.user_name_text_view);
            TextView emailTextView = findViewById(R.id.user_email_text_view);
            Button signOutButton = findViewById(R.id.sign_out_button);

            // Display user details
            nameTextView.setText(user.getDisplayName());
            emailTextView.setText(user.getEmail());

            // Set up the sign out button click listener
            signOutButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    signOut();
                }
            });
        }
    }
    private void signOut() {
        // First, sign out from Firebase
        mAuth.signOut();

        // Next, sign out from Google
        mGoogleSignInClient.signOut().addOnCompleteListener(this, task -> {
            Toast.makeText(MainActivity.this, "Signed out successfully", Toast.LENGTH_SHORT).show();
            goToLoginScreen(); // Redirect to the login screen after successful sign out
        });
    }

    private void goToLoginScreen() {
        Intent intent = new Intent(MainActivity.this, LoginScreen.class);
        startActivity(intent);
        finish(); // End MainActivity so the user can't press back to it
    }
}