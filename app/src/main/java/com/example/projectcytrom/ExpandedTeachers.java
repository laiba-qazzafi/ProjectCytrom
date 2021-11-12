package com.example.projectcytrom;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class ExpandedTeachers extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.teachers_expanded);
        getIncomingIntent();

    }
    private void getIncomingIntent(){
        if(getIntent().hasExtra("teacher_image_url") && getIntent().hasExtra("teacher_name_url")){
            String imageUrl= getIntent().getStringExtra("teacher_image_url");
            String nameUrl =  getIntent().getStringExtra("teacher_name_url");

            setImage(imageUrl, nameUrl);
        }
    }

    private void setImage(String imageUrl, String nameUrl){
        TextView name = (TextView) findViewById(R.id.teacherNameExpanded);
        name.setText(nameUrl);
        ImageView image = (ImageView) findViewById(R.id.teacherPicExpanded);
        image.setImageResource(Integer.parseInt(imageUrl));
    }
}
