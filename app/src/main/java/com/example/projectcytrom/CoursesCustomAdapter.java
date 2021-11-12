package com.example.projectcytrom;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CoursesCustomAdapter extends RecyclerView.Adapter<CoursesCustomAdapter.ViewHolder> {

    ArrayList courseLogo;
    Context context;

    public CoursesCustomAdapter(Context context, ArrayList courseLogo){
        this.context= context;
        this.courseLogo= courseLogo;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.courses_item, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.cPic.setImageResource((Integer) courseLogo.get(position));
//        String name = teacherName.get(position).toString();
//        String pic= teacherPic.get(position).toString();
//
//        holder.itemView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Toast.makeText(context.getApplicationContext(), name, Toast.LENGTH_SHORT).show();
//                Intent intent = new Intent(context.getApplicationContext(), ExpandedTeachers.class);
//                intent.putExtra("teacher_image_url", pic);
//                intent.putExtra("teacher_name_url", name);
//                context.startActivity(intent);
//            }
//        });
    }

    @Override
    public int getItemCount() {

        return courseLogo.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView cPic;

       public ViewHolder(View v){
           super(v);
           cPic=(ImageView) v.findViewById(R.id.courseLogo);
        }
    }
}
