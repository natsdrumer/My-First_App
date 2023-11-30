package com.example.tpc;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyViewHolder> {

    Context context;
    List<User> items;

    //MyViewHolder holder;

    public MyAdapter(Context context, List<User> items) {
        this.context = context;
        this.items = items;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(context).inflate(R.layout.item_view, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        
        int position2 = holder.getAbsoluteAdapterPosition();
        holder.nameview.setText(items.get(position2).getName());
        holder.emailview.setText(items.get(position2).getEmail());
        holder.imageView.setImageResource(items.get(position2).getImage());

        holder.llrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Dialog dialog = new Dialog(context);

                dialog.setContentView(R.layout.add_layout);

                EditText edtname = dialog.findViewById(R.id.edtname);
                EditText edtnumber = dialog.findViewById(R.id.edtnumber);
                Button btnAction = dialog.findViewById(R.id.btnAction);
                TextView title = dialog.findViewById(R.id.title);

                title.setText("Update Contact");

                btnAction.setText("Update");

                edtname.setText(items.get(position2).name);
                edtnumber.setText(items.get(position2).email);


                btnAction.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        String name = "", number ="";

                        if(!edtname.getText().toString().equals("")) {
                            name = edtname.getText().toString();
                        }else {
                            Toast.makeText(context,"Please Enter Contact Name", Toast.LENGTH_SHORT).show();
                        }

                        if(!edtnumber.getText().toString().equals("")) {
                            number = edtnumber.getText().toString();
                        }else {
                            Toast.makeText(context,"Please Enter Contact Number", Toast.LENGTH_SHORT).show();
                        }

                        items.get(position2).setName(name);
                        items.get(position2).setEmail(number);

                        notifyItemChanged(position2);

                        dialog.dismiss();

                    }
                });

                dialog.show();
            }
        });

        /*holder.llrow.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {

                AlertDialog.Builder builder = new AlertDialog.Builder(context)
                        .setTitle("Delete Contact")
                        .setMessage("Are you sure?")
                        .setIcon(R.drawable.baseline_delete_forever_24)
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                items.remove(position2);
                                notifyItemRemoved(position2);

                            }
                        })
                        .setNegativeButton("NO", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        });

                builder.show();

                return true;
            }
        });*/
    }

    @Override
    public int getItemCount() {
        return items.size();
    }
}
