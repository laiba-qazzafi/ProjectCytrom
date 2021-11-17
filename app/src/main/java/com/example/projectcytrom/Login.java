package com.example.projectcytrom;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Login extends AppCompatActivity {

    private EditText email;
    private EditText password;
    private Button bat1;
    private FirebaseAuth auth;
    TextView t;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        email=findViewById(R.id.email);
        password=findViewById(R.id.password);
        bat1=findViewById(R.id.loginButton);
        auth=FirebaseAuth.getInstance();

        bat1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                login();
            }
        });

        t = (TextView) findViewById(R.id.singupPage);
        t.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Login.this, SignUp.class);
                startActivity(i);
            }
        });
    }
    public void login()
    {
        String text_email=email.getText().toString();
        String text_password=password.getText().toString();

        if (TextUtils.isEmpty(text_email) || TextUtils.isEmpty(text_password))
        {
            Toast.makeText(Login.this, "Field is Empty", Toast.LENGTH_SHORT).show();
        }
        else
        {
            loginuser(text_email,text_password);
        }

    }
    private void loginuser(String email, String password)
    {
        auth.signInWithEmailAndPassword(email,password).addOnSuccessListener(Login.this, new OnSuccessListener<AuthResult>() {
            @Override
            public void onSuccess(AuthResult authResult) {

                Toast.makeText(Login.this, "Login SuccessFull", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(Login.this, MainActivity2.class);
                startActivity(intent);

            }
        });
    }







    }
