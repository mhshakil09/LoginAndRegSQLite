package com.example.loginandregsqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {


    EditText etUsername, etPassword;
    Button btnLogin, btnRegister;
    DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        etUsername = findViewById(R.id.etUsername);
        etPassword = findViewById(R.id.etPassword);

        btnLogin = findViewById(R.id.btnLogin);
        btnRegister = findViewById(R.id.btnRegister);

        databaseHelper = new DatabaseHelper(this);

        // checking if user is logged in or not
        if (sharedPref.getUsername(LoginActivity.this) != null && !sharedPref.getUsername(LoginActivity.this).equals(" ") &&
        !sharedPref.getPassword(LoginActivity.this).equals(" ")){
            Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
            startActivity(intent);
            finish();
        }

        // showing username to shared pref after logged out
        if (sharedPref.getUsername(LoginActivity.this) != null && sharedPref.getPassword(LoginActivity.this).equals(" ")){
            etUsername.setText(sharedPref.getUsername(LoginActivity.this).toString());
        }


        // user Login function is called
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                btnLoginFunction();

            }
        });

        // taking this activity to registration activity
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, RegistrationActivity.class);
                startActivity(intent);
                finish();
            }
        });


    }


    // function for checking and logging in user
    public void btnLoginFunction(){

        String etUsernameValue = etUsername.getText().toString();
        String etPasswordValue = etPassword.getText().toString();


        //checking whether the login credentials are in the database or not
        if (databaseHelper.isLoginValid(etUsernameValue, etPasswordValue)){
            sharedPref.saveUsername(etUsernameValue, LoginActivity.this);
            sharedPref.savePassword(etPasswordValue, LoginActivity.this);

            Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
            startActivity(intent);
            finish();

            Toast.makeText(LoginActivity.this, "Logged in successful.", Toast.LENGTH_SHORT).show();;

        }
        else {

            Toast.makeText(LoginActivity.this, "Invalid username or password!", Toast.LENGTH_SHORT).show();
        }
    }


}