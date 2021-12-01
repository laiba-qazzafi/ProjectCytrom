package com.example.projectcytrom;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.messaging.FirebaseMessaging;

public class ExpandedTeachers extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.teachers_expanded);
        getIncomingIntent();

        FirebaseMessaging.getInstance().subscribeToTopic("all");


        EditText title = findViewById(R.id.title);
        EditText msg = findViewById(R.id.msg);


        findViewById(R.id.btnAvailable).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!title.getText().toString().isEmpty() && !msg.getText().toString().isEmpty())
                {
                    FcmNotificationsSender notificationsSender = new FcmNotificationsSender("/topics/all", title.getText().toString(),
                            msg.getText().toString(), getApplicationContext(), ExpandedTeachers.this);
                    Toast.makeText(ExpandedTeachers.this, "EnterED", Toast.LENGTH_SHORT).show();

                    notificationsSender.SendNotifications();

                }else

                    Toast.makeText(ExpandedTeachers.this, "Enter Details", Toast.LENGTH_SHORT).show();


            }

        });
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
