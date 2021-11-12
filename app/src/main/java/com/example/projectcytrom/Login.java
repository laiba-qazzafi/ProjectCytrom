package com.example.projectcytrom;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Login extends AppCompatActivity {

    EditText e1, e2;
    TextView t;
    Button button;
    int flag=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        button = (Button) findViewById(R.id.loginButton);
        button.setOnClickListener(new View.OnClickListener() {
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
        e1= (EditText) findViewById(R.id.email);
        String username= e1.getText().toString();
        e2 =(EditText) findViewById(R.id.password);
        String password = e2.getText().toString().trim();
//        e2.getText().toString().trim().equals(password);

        if(flag==0)
        {
            if(username.equals("admin") && password.equals("cytrom")){
                Intent intent = new Intent(Login.this, MainActivity2.class);
                startActivity(intent);
            }
            else {
                Toast.makeText(Login.this, "Invalid Username or Password!", Toast.LENGTH_SHORT).show();
            }

        }
        else {

            if(username.equals("admin") && password.equals("cytrom")){
                Intent intent = new Intent(Login.this, TeachersModule.class);
                startActivity(intent);
            }
            else {
                Toast.makeText(Login.this, "Invalid Username or Password!", Toast.LENGTH_SHORT).show();
            }
        }


    }
}