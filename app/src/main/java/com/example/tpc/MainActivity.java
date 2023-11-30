package com.example.tpc;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText txt_password;
    private EditText txt_email;
    private final String password = "teste";
    private final String user = "natslevy@gmail.com";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txt_password = findViewById(R.id.txt_password);
        txt_email = findViewById(R.id.txt_email);

        if(getIntent().hasExtra("email")){
            String user = getIntent().getStringExtra("email");
            txt_email.setText(String.valueOf(user));
        }
    }

    //@Override
    //protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        //super.onActivityResult(requestCode, resultCode, data);
        //if (requestCode == 1){

          //      String user = data.toString();
        //        txt_email.setText(String.valueOf(data));

      //  }
    //}

    public void cancel_handler(View view) {
        Intent intent = new Intent(this, RegisterActivity.class);
        startActivity(intent);
        txt_email.setText("");
        txt_password.setText("");
    }

    public void login_handler(View view) {
        String mail = String.valueOf(txt_email.getText());
        String pass = String.valueOf(txt_password.getText());
        if(mail.equals(user) && pass.equals(password)){
            String txt = "User with email " + mail + " loged in";
            Toast.makeText(this, txt, Toast.LENGTH_SHORT).show();
            txt_email.setText("");
            txt_password.setText("");
            Intent intent = new Intent(this, HomeActivity.class);
            startActivity(intent);
        }else{
            String txts = "User or Password incorret";
            Toast.makeText(this, txts, Toast.LENGTH_SHORT).show();
        }

    }



}