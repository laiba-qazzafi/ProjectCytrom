package com.example.projectcytrom;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Arrays;

public class  CoursesModule extends AppCompatActivity {

    ArrayList coursesImg = new ArrayList(Arrays.asList(
            R.drawable.java,
            R.drawable.c_plus_plus,
            R.drawable.database,
            R.drawable.php,
            R.drawable.html5,
            R.drawable.html,
            R.drawable.java,
            R.drawable.c_plus_plus,
            R.drawable.database,
            R.drawable.php));

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

    }
}

