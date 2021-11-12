package com.example.projectcytrom;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


import java.util.ArrayList;
import java.util.Arrays;

public class CoursesModule extends AppCompatActivity {

    ArrayList coursesImg = new ArrayList(Arrays.asList(R.drawable.java, R.drawable.c_plus_plus, R.drawable.database, R.drawable.php, R.drawable.java, R.drawable.c_plus_plus, R.drawable.database, R.drawable.php));

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_courses_module);

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.coursesRecyclerView);

//        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.HORIZONTAL, false);
//        recyclerView.setLayoutManager(linearLayoutManager);

        GridLayoutManager gridLayoutManager = new GridLayoutManager(getApplicationContext(), 1, GridLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(gridLayoutManager);

        CoursesCustomAdapter coursesCustomAdapter = new CoursesCustomAdapter( this, coursesImg);
        recyclerView.setAdapter(coursesCustomAdapter);

        Button button= (Button) findViewById(R.id.btn1);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(CoursesModule.this, MainActivity2.class);
                startActivity(i);
            }
        });
    }
}

