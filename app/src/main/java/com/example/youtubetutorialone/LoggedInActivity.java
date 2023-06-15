package com.example.youtubetutorialone;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.youtubetutorialone.databinding.ActivityLoggedInBinding;

public class LoggedInActivity extends AppCompatActivity {

    private static ActivityLoggedInBinding binding;
    private static AppCompatButton btnLogout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logged_in);
        binding = ActivityLoggedInBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        replaceFragment(new PersonDetails());

        btnLogout = findViewById(R.id.btnLogout);

        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(LoggedInActivity.this, "You have logged out", Toast.LENGTH_SHORT).show();
                redirectToMainActivity();
            }
        });

        binding.navigation.setOnItemSelectedListener(item -> {

            switch (item.getItemId()){

                case R.id.personDocuments:
                    replaceFragment(new PersonDocuments());
                    break;
                case R.id.personDetails:
                    replaceFragment(new PersonDetails());
                    break;
                case R.id.personCars:
                    replaceFragment(new PersonCar());
                    break;
            }
            return true;
        });


    }

    private void replaceFragment(Fragment fragment){
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frameLayout,fragment);
        fragmentTransaction.commit();

    }

    private void redirectToMainActivity(){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

}