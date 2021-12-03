package com.example.projectcytrom;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.nfc.tech.TagTechnology;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class TeachersLogin extends AppCompatActivity {

    TextView textView;

    EditText t1, t2;
    Button button;
    ProgressBar bar;
    FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teachers_login);

        t1 = (EditText) findViewById(R.id.email);
        t2 = (EditText) findViewById(R.id.password);
        button = (Button) findViewById(R.id.tLoginButton);
        textView = (TextView) findViewById(R.id.singupPage);
        bar = (ProgressBar) findViewById(R.id.progressBar);

        auth = FirebaseAuth.getInstance();

        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(TeachersLogin.this, TeachersSignup.class);
                startActivity(intent);
                finish();
            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bar.setVisibility(View.VISIBLE);

                String email = t1.getText().toString().trim();
                String password = t2.getText().toString().trim();

                if (email.isEmpty() || password.isEmpty())
                {
                    Toast.makeText(TeachersLogin.this, "Empty credentials", Toast.LENGTH_SHORT).show();
                }
                else {
                    loginuser(email, password);
                }
            }
        });


    }

    private void loginuser(String email, String password) {
        auth.signInWithEmailAndPassword(email,password).addOnCompleteListener(TeachersLogin.this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                if (!task.isSuccessful())
                {
                    bar.setVisibility(View.INVISIBLE);
                    Toast.makeText(TeachersLogin.this, "Incorrect Email Or Password", Toast.LENGTH_SHORT).show();
                }else {
                    bar.setVisibility(View.INVISIBLE);
                    Toast.makeText(TeachersLogin.this, "Login SuccessFull", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(TeachersLogin.this, TeachersModule.class);
                    intent.putExtra("email", auth.getCurrentUser().getEmail());
                    startActivity(intent);

                }
            }


        });

    }
}