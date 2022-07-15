package com.example.notepad;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class EnterPasswordActivity extends AppCompatActivity {

    EditText editTextEnterPassword;
    Button btnEnterPassword;
    String password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enter_password);

        editTextEnterPassword = findViewById(R.id.editTextEnterPassword);
        btnEnterPassword = findViewById(R.id.btnEnterPassword);

        SharedPreferences settings = getSharedPreferences("PREFS", 0);
        password = settings.getString("password", "");

        btnEnterPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String textPassword = editTextEnterPassword.getText().toString();

                if(textPassword.equals(password)) {
                     Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                     startActivity(intent);
                     finish();
                } else {
                    Toast.makeText(EnterPasswordActivity.this, "Wrong password!", Toast.LENGTH_SHORT).show();
                }
            }
        });



    }
}




















