package com.example.projectcytrom;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class SuperiorModule extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_superior_module);

        CardView cardView1= (CardView) findViewById(R.id.studentSuperiorCard);
        cardView1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(SuperiorModule.this, MainActivity2.class);
                startActivity(intent);
            }
        });
    }
}