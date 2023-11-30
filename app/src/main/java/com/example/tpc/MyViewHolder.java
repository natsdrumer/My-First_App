package com.example.tpc;

import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class MyViewHolder extends RecyclerView.ViewHolder {

    ImageView imageView;
    TextView nameview, emailview;
    RelativeLayout llrow;

    public MyViewHolder(@NonNull View itemView) {
        super(itemView);
        imageView = itemView.findViewById(R.id.imageview);
        nameview = itemView.findViewById(R.id.name);
        emailview = itemView.findViewById(R.id.email);
        llrow=itemView.findViewById(R.id.llrow);
    }
}
