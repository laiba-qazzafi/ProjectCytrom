package com.example.projectcytrom;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class SuperiorModule extends AppCompatActivity {

    CardView student, faculty;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_superior_module);

        student= (CardView) findViewById(R.id.studentSuperiorCard);
        student.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent= new Intent(SuperiorModule.this, Login.class);
                startActivity(intent);
            }
        });

        faculty = (CardView) findViewById(R.id.facultySuperiorCard);
        faculty.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SuperiorModule.this, Login.class);
                startActivity(intent);
            }
        });
    }
}