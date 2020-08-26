package com.example.loginandregsqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class RegistrationActivity extends AppCompatActivity {


    EditText etUsername, etPassword, etEmail, etDob, etCountry;
    Button btnRegister, btnCancel;
    DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);


        etUsername = findViewById(R.id.etUsername);
        etPassword = findViewById(R.id.etPassword);
        etEmail = findViewById(R.id.etEmail);
        etDob = findViewById(R.id.etDob);
        etCountry = findViewById(R.id.etCountry);

        btnRegister = findViewById(R.id.btnRegister);
        btnCancel = findViewById(R.id.btnCancel);

        databaseHelper = new DatabaseHelper(this);


        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String etUsernameValue = etUsername.getText().toString();
                String etPasswordValue = etPassword.getText().toString();
                String etEmailValue = etEmail.getText().toString();
                String etDobValue = etDob.getText().toString();
                String etCountryValue = etCountry.getText().toString();

                if ((etUsernameValue.length()>1) && (etPasswordValue.length()>1)){

//                    if (!databaseHelper.isUsernameAvailable(etUsernameValue)){
//                        Toast.makeText(RegistrationActivity.this, "already taken", Toast.LENGTH_SHORT).show();
//                    }
//                    else {
//                        Toast.makeText(RegistrationActivity.this, "available", Toast.LENGTH_SHORT).show();
//                    }

                    ContentValues contentValues= new ContentValues();

                    contentValues.put("username", etUsernameValue);
                    contentValues.put("password", etPasswordValue);
                    contentValues.put("email", etEmailValue);
                    contentValues.put("dob", etDobValue);
                    contentValues.put("country", etCountryValue);

                    databaseHelper.insertUser(contentValues);

                    Toast.makeText(RegistrationActivity.this, "Registration complete", Toast.LENGTH_SHORT).show();

                    Intent intent = new Intent(RegistrationActivity.this, LoginActivity.class);
                    startActivity(intent);
                    finish();
                }
                else {

                    Toast.makeText(RegistrationActivity.this, "username and password required", Toast.LENGTH_SHORT).show();
                }




            }
        });


        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                Intent intent = new Intent(RegistrationActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });











    }
}