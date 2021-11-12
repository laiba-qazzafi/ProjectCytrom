package com.example.projectcytrom;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;


import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity2 extends AppCompatActivity {

    ArrayList teacherName= new ArrayList(Arrays.asList(
            "Muhammad Ahmad",
            "Tayyaba Farhat",
            "Ahmad Shafaiq",
            "Aqib Chaudhary",
            "Dr. Tehreem Masood",
            "Mudassir Bajwa",
            "Naeem Raza",
            "Hafiz ALi Ahmed",
            "Naeem Abbas"
            ));
    ArrayList teacherPic= new ArrayList(Arrays.asList(
            R.drawable.sir_ahmad,
            R.drawable.mam_tayyaba,
            R.drawable.account,
            R.drawable.sir_aqib_chaudhary,
            R.drawable.dr_tahreem,
            R.drawable.sir_mudassir,
            R.drawable.sir_naeem_raza,
            R.drawable.sir_ali_ahmed,
            R.drawable.sir_naeem_abbas
    ));


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);


        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.teachersRecyclerView);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);

        CustomAdapter customAdapter = new CustomAdapter(teacherName, teacherPic, this);
        recyclerView.setAdapter(customAdapter);

        recyclerView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                
            }
        });

    }
}