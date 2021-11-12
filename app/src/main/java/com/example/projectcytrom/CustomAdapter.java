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

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.ViewHolder> {

    ArrayList teacherName;
    ArrayList teacherPic;
    Context context;

    public CustomAdapter(ArrayList teacherName, ArrayList teacherPic, Context context){
        this.teacherName= teacherName;
        this.teacherPic= teacherPic;
        this.context= context;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.teachers_item, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.tName.setText((CharSequence) teacherName.get(position));
        holder.tPic.setImageResource((Integer) teacherPic.get(position));
        String name = teacherName.get(position).toString();
        String pic= teacherPic.get(position).toString();

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context.getApplicationContext(), name, Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(context.getApplicationContext(), ExpandedTeachers.class);
                intent.putExtra("teacher_image_url", pic);
                intent.putExtra("teacher_name_url", name);
                context.startActivity(intent);
            }
        });
    }


    @Override
    public int getItemCount() {
        return teacherName.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tName;
        ImageView tPic;

        public ViewHolder(View v) {
            super(v);
            tName= (TextView) v.findViewById(R.id.teacherName);
            tPic=(ImageView) v.findViewById(R.id.teacherPic);
        }
    }
}
