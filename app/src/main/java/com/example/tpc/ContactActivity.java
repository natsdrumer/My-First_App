package com.example.tpc;

import static com.google.android.material.snackbar.BaseTransientBottomBar.LENGTH_LONG;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.graphics.Canvas;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import it.xabaras.android.recyclerview.swipedecorator.RecyclerViewSwipeDecorator;

public class ContactActivity extends AppCompatActivity {

    List<User> items = new ArrayList<>();
    MyAdapter adapter = new MyAdapter(this, items);
    //RecyclerView recyclerView2 = findViewById(R.id.recyclerview);



    FloatingActionButton btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.contact_activity);

        RecyclerView recyclerView = findViewById(R.id.recyclerview);
        btn = findViewById(R.id.opendi);

        items.add(new User("Natanael", "nats@gmail.com", R.drawable.a));
        items.add(new User("Duarte", "duarte@gmail.com", R.drawable.b));
        items.add(new User("Levi", "levi@gmail.com", R.drawable.c));
        items.add(new User("Maria", "maria@gmail.com", R.drawable.d));
        items.add(new User("Ezequiel", "ezequiel@gmail.com", R.drawable.a));
        items.add(new User("Jesus", "jesus@gmail.com", R.drawable.b));
        items.add(new User("Stephanny", "stephanny@gmail.com", R.drawable.c));
        items.add(new User("Fanny", "fanny@gmail.com", R.drawable.d));
        items.add(new User("Natanael", "nats@gmail.com", R.drawable.a));
        items.add(new User("Natanael", "nats@gmail.com", R.drawable.b));
        items.add(new User("Natanael", "nats@gmail.com", R.drawable.c));
        items.add(new User("Natanael", "nats@gmail.com", R.drawable.d));
        items.add(new User("Natanael", "nats@gmail.com", R.drawable.a));
        items.add(new User("Natanael", "nats@gmail.com", R.drawable.b));
        items.add(new User("Natanael", "nats@gmail.com", R.drawable.c));
        items.add(new User("Natanael", "nats@gmail.com", R.drawable.d));

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Dialog dialog = new Dialog(ContactActivity.this);
                dialog.setContentView(R.layout.add_layout);

                EditText edtname = dialog.findViewById(R.id.edtname);
                EditText edtnumber = dialog.findViewById(R.id.edtnumber);
                Button btnAction = dialog.findViewById(R.id.btnAction);

                btnAction.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String name = "", number ="";

                        if(!edtname.getText().toString().equals("")) {
                            name = edtname.getText().toString();
                        }else {
                            Toast.makeText(ContactActivity.this,"Please Enter Contact Name", Toast.LENGTH_SHORT).show();
                        }

                        if(!edtnumber.getText().toString().equals("")) {
                            number = edtnumber.getText().toString();
                        }else {
                            Toast.makeText(ContactActivity.this,"Please Enter Contact Number", Toast.LENGTH_SHORT).show();
                        }

                        items.add(new User(name, number, R.drawable.h));


                        adapter.notifyItemInserted(items.size()-1);
                        recyclerView.scrollToPosition(items.size()-1);

                        dialog.dismiss();

                    }
                });

                dialog.show();
            }
        });





        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(simpleCallback);
        itemTouchHelper.attachToRecyclerView(recyclerView);
    }



    ItemTouchHelper.SimpleCallback simpleCallback = new ItemTouchHelper.SimpleCallback(ItemTouchHelper.UP | ItemTouchHelper.DOWN | ItemTouchHelper.START | ItemTouchHelper.END, ItemTouchHelper.LEFT ) {
        @Override
        public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {

            int fromPosition = viewHolder.getAbsoluteAdapterPosition();
            int toPosition = target.getAbsoluteAdapterPosition();

            Collections.swap(items, fromPosition, toPosition);
            recyclerView.getAdapter().notifyItemMoved(fromPosition, toPosition);

            return false;
        }

        @Override
        public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {

            int position = viewHolder.getAbsoluteAdapterPosition();


            switch (direction) {
                case ItemTouchHelper.LEFT:
                    User user = items.get(position);
                    String nameDelete = user.name;
                    items.remove(position);
                    adapter.notifyItemRemoved(position);
                    RecyclerView recyclerView = findViewById(R.id.recyclerview);
                    Snackbar.make(recyclerView, nameDelete + " deleted!", LENGTH_LONG)
                            .setAction("Undo", new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    items.add(position, user);
                                    adapter.notifyItemInserted(position);
                                }
                            }).show();





                    break;
            }



        }

        @Override
        public void onChildDraw(@NonNull Canvas c, @NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, float dX, float dY, int actionState, boolean isCurrentlyActive) {

            new RecyclerViewSwipeDecorator.Builder(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive)
                    .addSwipeLeftBackgroundColor(ContextCompat.getColor(ContactActivity.this, com.google.android.material.R.color.design_default_color_error))
                    .addSwipeLeftActionIcon(R.drawable.baseline_delete_forever_24)
                    .create()
                    .decorate();

            super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive);

        }
    };
}

