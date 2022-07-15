package com.example.notepad;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Shader;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class CreatePasswordActivity extends AppCompatActivity {

    EditText editTextCreatePassword;
    Button btnCreatePassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_password);

        editTextCreatePassword = findViewById(R.id.editTextCreatePassword);
        btnCreatePassword = findViewById(R.id.btnCreatePassword);

        btnCreatePassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String textPassword = editTextCreatePassword.getText().toString();

                if(textPassword.equals("")) {
                    Toast.makeText(CreatePasswordActivity.this, "Enter Password!", Toast.LENGTH_SHORT).show();
                } else {
                    SharedPreferences settings = getSharedPreferences("PREFS", 0);
                    SharedPreferences.Editor editor = settings.edit();
                    editor.putString("password", textPassword);
                    editor.apply();

                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(intent);
                    finish();
                }
            }
        });


    }
}


















