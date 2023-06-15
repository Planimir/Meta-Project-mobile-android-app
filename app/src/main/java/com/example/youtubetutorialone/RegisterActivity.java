package com.example.youtubetutorialone;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.dynamiclinks.FirebaseDynamicLinks;
import com.google.firebase.dynamiclinks.PendingDynamicLinkData;
import com.google.firebase.firestore.FirebaseFirestore;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {


    private static AppCompatButton btnRedirectToLogin, btnFacebook, btnGoogle, btnRegister;

    private static EditText inputEmailRegister, inputUsernameRegister, inputPasswordRegister, inputConfirmPasswordRegister;

    private static RadioGroup radioGroupGender;

    private static RadioButton radioButtonRegisterGenderSelected;

    private ProgressBar progressBar;

    private FirebaseFirestore firestore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        firestore = FirebaseFirestore.getInstance();


         btnRedirectToLogin = findViewById(R.id.btnRedirectToLogin);
         btnFacebook = findViewById(R.id.btnFacebookRegister);
         btnGoogle = findViewById(R.id.btnLogout);
         btnRegister = findViewById(R.id.btnRegisterMainActivity);

         inputEmailRegister = findViewById(R.id.inputEmailLogin);
         inputUsernameRegister = findViewById(R.id.inputUsernameLogin);
         inputPasswordRegister = findViewById(R.id.inputPasswordLogin);
         inputConfirmPasswordRegister = findViewById(R.id.inputConfirmPasswordRegister);

         radioGroupGender = findViewById(R.id.radio_group_register_gender);
         radioGroupGender.clearCheck();

        btnRedirectToLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(RegisterActivity.this, "Redirecting to Login", Toast.LENGTH_SHORT).show();
                redirectToLogin();
            }
        }); // Activity Login redirect

        btnFacebook.setOnClickListener(view -> {
           String url = "https://www.facebook.com/";
           Intent intent = new Intent(Intent.ACTION_VIEW);
           intent.setData(Uri.parse(url));
           startActivity(intent);
        }); // Facebook button

        btnGoogle.setOnClickListener(view -> {
            String url = "https://www.google.com/";
            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.setData(Uri.parse(url));
            startActivity(intent);

        }); // Google button

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int selectedGenderId = radioGroupGender.getCheckedRadioButtonId();
                radioButtonRegisterGenderSelected = findViewById(selectedGenderId);

                // Obtain the entered data

                String textEmail = inputEmailRegister.getText().toString();
                String textUsername = inputUsernameRegister.getText().toString();
                String textPassword = inputPasswordRegister.getText().toString();
                String textGender;  // Won`t obtain any value if a button hasn`t been selected.

                if(TextUtils.isEmpty(textUsername)){ // TextUtils returns boolean value. isEmpty returns null only if length() = 0
                    Toast.makeText(RegisterActivity.this, "Please enter your username!", Toast.LENGTH_SHORT).show();
                    inputUsernameRegister.setError("A name is required!");
                    inputUsernameRegister.requestFocus();

                }else if(TextUtils.isEmpty(textEmail)){
                    Toast.makeText(RegisterActivity.this, "Please enter your email!", Toast.LENGTH_SHORT).show();
                    inputEmailRegister.setError("An email is required.");
                    inputEmailRegister.requestFocus();
                }else if(!Patterns.EMAIL_ADDRESS.matcher(textEmail).matches()){
                    inputPasswordRegister.setError("Valid email is required!");
                    inputPasswordRegister.requestFocus();
                }else if(TextUtils.isEmpty(textPassword)){
                    inputPasswordRegister.setError("Please enter your password!");
                    inputPasswordRegister.requestFocus();
                }else if(radioGroupGender.getCheckedRadioButtonId() == -1){
                    radioButtonRegisterGenderSelected.setError("Please select your gender!");
                    radioButtonRegisterGenderSelected.requestFocus();
                }
            }
        });

//        here you start the dynamic link setup

//        getDynamicLinkFromFireBase();  DYNAMIC LINK FAILURE

    }


//    DYNAMIC LINK FAILURE

//    private void getDynamicLinkFromFireBase() {
//
//        FirebaseDynamicLinks.getInstance().getDynamicLink(getIntent()).addOnSuccessListener(new OnSuccessListener<PendingDynamicLinkData>() {
//            @Override
//            public void onSuccess(PendingDynamicLinkData pendingDynamicLinkData) {
//                Log.i("SignInActivity", "We have a Dynamic Link");
//                Uri deepLink = null;
//
//                if(pendingDynamicLinkData!=null){
//                    deepLink = pendingDynamicLinkData.getLink();
//                }
//
//                if(deepLink!=null){
//                    Log.i("SignActivity", "The Dynamic Link : \n" + deepLink.toString());
//                    String email = deepLink.getQueryParameter("email"); // that way we get data from the user who is using the link.
//                    String password = deepLink.getQueryParameter("password");
//
//                }
//            }
//        }).addOnFailureListener(new OnFailureListener() {
//            @Override
//            public void onFailure(@NonNull Exception e) {
//                Toast.makeText(RegisterActivity.this, e.toString(), Toast.LENGTH_SHORT).show();
//            }
//        });
//    }

    private void redirectToLogin() {
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }

    @Override
    public void onClick(View view) {

    }
}
