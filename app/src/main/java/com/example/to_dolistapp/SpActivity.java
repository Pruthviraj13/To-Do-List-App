package com.example.to_dolistapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class SpActivity extends AppCompatActivity {
    TextView textView;
    EditText email, pass;
    Button buttonreg;
    FirebaseAuth auth;
    Button login;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try
        {
            this.getSupportActionBar().hide();
        }
        catch (NullPointerException e){}
        setContentView(R.layout.activity_sp);
        email = findViewById(R.id.editTextTextEmailAddress);
        pass = findViewById(R.id.editTextNumberPassword);
        buttonreg = findViewById(R.id.buttonlogin);
        auth = FirebaseAuth.getInstance();
        login=findViewById(R.id.button3);

        buttonreg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Email = email.getText().toString();
                String password = pass.getText().toString();
                if (TextUtils.isEmpty(Email)) {
                    email.setError("Email is required.");
                    email.requestFocus();
                } else if (TextUtils.isEmpty(password)) {
                    pass.setError("Password is required.");
                    pass.requestFocus();
                } else if (password.length() < 6) {
                    pass.setError("Password must be 6 digits");
                    pass.requestFocus();
                } else {
                    auth.createUserWithEmailAndPassword(Email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                Toast.makeText(SpActivity.this, "User successfully added", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(SpActivity.this, LoginPage.class);
                                startActivity(intent);
                               // email.setText("");
                               // pass.setText("");
                            } else {
                                Toast.makeText(SpActivity.this, "Error", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
            }
        });
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SpActivity.this, LoginPage.class);
                startActivity(intent);
            }
        });
    }


    }
