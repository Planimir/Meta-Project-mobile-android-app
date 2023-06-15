package com.example.youtubetutorialone;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.firebase.firestore.FirebaseFirestore;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    private static AppCompatButton btnRedirectToRegister, btnFacebook, btnGoogle, btnLogin;

    private static EditText inputEmailLogin, inputUsernameLogin, inputPasswordLogin;

    private FirebaseFirestore firestore;

    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        firestore = FirebaseFirestore.getInstance();

        btnRedirectToRegister = findViewById(R.id.btnRedirectToRegister);
        btnFacebook = findViewById(R.id.btnFacebookLogin);
        btnGoogle = findViewById(R.id.btnGoogleLogin);
        btnLogin = findViewById(R.id.btnLogin);
        inputEmailLogin = findViewById(R.id.inputEmailLogin);
        inputUsernameLogin = findViewById(R.id.inputUsernameLogin);
        inputPasswordLogin = findViewById(R.id.inputPasswordLogin);

        btnRedirectToRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(LoginActivity.this, "Redirecting to Register", Toast.LENGTH_SHORT).show();
                redirectToRegister();
            }
        });

        btnFacebook.setOnClickListener(view -> {
            String url = "https://www.facebook.com/";
            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.setData(Uri.parse(url));
            startActivity(intent);
        }); // button Facebook

        btnGoogle.setOnClickListener(view -> {
            String url = "https://www.google.com/";
            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.setData(Uri.parse(url));
            startActivity(intent);
        }); // button Google

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(LoginActivity.this, "Logged in", Toast.LENGTH_SHORT).show();
                redirectToLoggedIn();
            }
        }); // button Login
    }

    private void redirectToRegister(){
        Intent registerIntent = new Intent(this, RegisterActivity.class);
        startActivity(registerIntent);
    }

    private void redirectToLoggedIn(){
        Intent loggedIntent = new Intent(this, LoggedInActivity.class);
        startActivity(loggedIntent);
    }


    @Override
    public void onClick(View view) {

    }
}