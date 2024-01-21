package com.example.finalapp.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.finalapp.MainActivity;
import com.example.finalapp.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {
    EditText email, password;
    private FirebaseAuth auth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        email = findViewById(R.id.et_email_login);
        password = findViewById(R.id.et_password_login);
    }

    public void Register_Login(View view)
    {
        startActivity(new Intent(getApplicationContext(), RegisterActivity.class));
        finish();
    }

    public void Login_Login(View v)
    {
        String str_email = email.getText().toString();
        String str_password = password.getText().toString();

        if (!str_email.isEmpty() && !str_password.isEmpty()) {
            auth = FirebaseAuth.getInstance();
            auth.signInWithEmailAndPassword(str_email, str_password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()) {
                        startActivity(new Intent(getApplicationContext(), MainActivity.class));
                        finish();
                    }
                    else {
                        Toast.makeText(getApplicationContext(), "Girişte bir hata gerçekleşti.", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
        else {
            Toast.makeText(getApplicationContext(), "Email ve password alanları gereklidir", Toast.LENGTH_SHORT).show();
        }
    }
}