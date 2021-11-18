package com.example.projectcytrom;

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
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class SignUp extends AppCompatActivity {
    private EditText email;
    private EditText password;
    private EditText name;
    private EditText roll;
    private EditText section;
    private Button button;
    private FirebaseAuth auth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);


        email=findViewById(R.id.email);
        password=findViewById(R.id.password);
        name=findViewById(R.id.name);
        roll=findViewById(R.id.roll);
        section=findViewById(R.id.section);
        button=findViewById(R.id.button);
        auth=FirebaseAuth.getInstance();


        TextView t = (TextView) findViewById(R.id.loginPage);
        t.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(SignUp.this, Login.class);
                startActivity(i);
            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HashMap<String,Object> m=new HashMap<String,Object>();
                m.put("name",name.getText().toString());
                m.put("roll",roll.getText().toString());
                m.put("section",section.getText().toString());
                FirebaseDatabase.getInstance().getReference().child("student").setValue(m);

                String text_email=email.getText().toString().trim();
                String text_password=password.getText().toString().trim();
                String text_name=name.getText().toString().trim();
                String text_section=section.getText().toString().trim();
                String text_roll=roll.getText().toString().trim();


                if (TextUtils.isEmpty(text_email) || TextUtils.isEmpty(text_password) || TextUtils.isEmpty(text_name) || TextUtils.isEmpty(text_roll) || TextUtils.isEmpty(text_section))
                {
                    Toast.makeText(SignUp.this, "Kindly fill all the fields!", Toast.LENGTH_SHORT).show();

                }else if (text_password.length()<6)
                {
                    Toast.makeText(SignUp.this, "Password must be 8 Charachter", Toast.LENGTH_SHORT).show();
                }else
                {
                    signupuser(text_email,text_password);
                }
            }

            private void signupuser(String email, String password)
            {
                auth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(SignUp.this, new OnCompleteListener<AuthResult>()
                {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful())
                        {
                            Toast.makeText(SignUp.this, "Register Successfully", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(SignUp.this ,Login.class));
                            finish();
                        }else
                        {
                            Toast.makeText(SignUp.this, "Registration Failed", Toast.LENGTH_SHORT).show();
                        }
                    }
                });

            }
        });

        }
}