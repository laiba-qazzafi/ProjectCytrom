package com.example.projectcytrom;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

public class MainActivity3 extends AppCompatActivity {

    LinearLayout courses, superior;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        courses = (LinearLayout) findViewById(R.id.coursesModuleCard);
        superior = (LinearLayout) findViewById(R.id.universityModuleCard);

        courses.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i1 = new Intent(MainActivity3.this, CoursesModule.class);
                startActivity(i1);
            }
        });

        superior.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i2 = new Intent(MainActivity3.this, SuperiorModule.class);
                startActivity(i2);
            }
        });
    }
}