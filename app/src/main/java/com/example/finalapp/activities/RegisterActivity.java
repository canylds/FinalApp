package com.example.finalapp.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.finalapp.R;
import com.example.finalapp.models.UserModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

public class RegisterActivity extends AppCompatActivity {

    EditText firstname, lastname, email, password;
    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        firstname = findViewById(R.id.et_firstname_register);
        lastname = findViewById(R.id.et_lastname_register);
        email = findViewById(R.id.et_email_register);
        password = findViewById(R.id.et_password_register);
    }

    public void Login_Register(View v)
    {
        startActivity(new Intent(getApplicationContext(), LoginActivity.class));
        finish();
    }

    public void Register_Register(View v)
    {
        String str_firstname = firstname.getText().toString();
        String str_lastname = lastname.getText().toString();
        String str_email = email.getText().toString();
        String str_password = password.getText().toString();

        if (!str_firstname.isEmpty() && !str_lastname.isEmpty() && !str_email.isEmpty() && !str_password.isEmpty())
        {
            auth = FirebaseAuth.getInstance();
            auth.createUserWithEmailAndPassword(str_email, str_password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()) {
                        String uid = task.getResult().getUser().getUid();
                        FirebaseFirestore db = FirebaseFirestore.getInstance();
                        UserModel userModel = new UserModel(str_firstname, str_lastname, str_email);
                        db.collection("UserModel").document(uid).set(userModel).addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void unused) {
                                Toast.makeText(getApplicationContext(), "Kayıt işlemi başarılı", Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(getApplicationContext(), LoginActivity.class));
                                finish();
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(getApplicationContext(), "Kayıt olma sırasında bir hata gerçekleşti", Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Toast.makeText(getApplicationContext(), "Kayıt olma sırasında bir hata gerçekleşti", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
}