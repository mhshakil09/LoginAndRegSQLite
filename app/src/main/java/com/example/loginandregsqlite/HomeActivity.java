package com.example.loginandregsqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class HomeActivity extends AppCompatActivity {

    TextView tvGreetings, tvUserDetails;
    EditText etNewPass;
    Button btnReset, btnLogout;
    DatabaseHelper databaseHelper;
    String etNewPassValue, tvUsernameValue, tvPasswordValue;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        tvGreetings = findViewById(R.id.tvGreetings);
        tvUserDetails = findViewById(R.id.tvUserDetails);
        etNewPass = findViewById(R.id.etNewPass);
        btnReset = findViewById(R.id.btnReset);
        btnLogout = findViewById(R.id.btnLogout);

        databaseHelper = new DatabaseHelper(this);

        tvUsernameValue = sharedPref.getUsername(this).toString();
        tvPasswordValue = sharedPref.getPassword(this).toString();

        tvUserDetails.setText("username: '" + tvUsernameValue + "' pass: " + tvPasswordValue);


        btnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                btnResetFunction();

            }
        });


        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Toast.makeText(HomeActivity.this, ("'" + tvUsernameValue + "' is Logging out!"), Toast.LENGTH_SHORT).show();

                //resetting the shared password for ew login
                sharedPref.savePassword(" ", HomeActivity.this);

                Intent intent = new Intent(HomeActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();

            }
        });

    }

    public void btnResetFunction(){

        etNewPassValue = etNewPass.getText().toString();

//                databaseHelper.isNewPass(tvUsernameValue, etNewPassValue);
//                Toast.makeText(HomeActivity.this, "password changed successfully", Toast.LENGTH_SHORT).show();

//                tvUserDetails.setText("username: '" + etNewPassValue + "' pass: '" + tvPasswordValue + "'");

        // checking whether the input field is blank or not and also new password is eequal to old password or not
        if ( etNewPassValue.isEmpty() || etNewPassValue.equals(tvPasswordValue)){
            Toast.makeText(HomeActivity.this, "enter new password", Toast.LENGTH_SHORT).show();
        }
        else{
            databaseHelper.isNewPass(tvUsernameValue, etNewPassValue);
            Toast.makeText(HomeActivity.this, "password changed successfully", Toast.LENGTH_SHORT).show();
            sharedPref.savePassword(etNewPassValue, HomeActivity.this);
            tvPasswordValue = sharedPref.getPassword(HomeActivity.this).toString();
        }
        tvUserDetails.setText("username: '" + tvUsernameValue + "' pass: " + tvPasswordValue);

    }

}