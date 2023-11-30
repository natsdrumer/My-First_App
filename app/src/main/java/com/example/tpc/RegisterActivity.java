package com.example.tpc;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class RegisterActivity extends AppCompatActivity {

    private EditText nome, mail, pass, pass2;
    String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_activity);
        nome = findViewById(R.id.etName);
        mail = findViewById(R.id.etMail);
        pass = findViewById(R.id.etPass);
        pass2 = findViewById(R.id.etPass2);

    }

    public void register(View view) {
        String p1 = String.valueOf(pass.getText());
        String p2 = String.valueOf(pass2.getText());
        String email = String.valueOf(mail.getText());
        if(nome.getText().length() == 0 || mail.getText().length() == 0 || pass.getText().length() == 0 || pass2.getText().length() == 0){
            Toast.makeText(this, R.string.vazio, Toast.LENGTH_SHORT).show();
        }else if (!email.matches(emailPattern)) {
            Toast.makeText(this, R.string.invalid, Toast.LENGTH_SHORT).show();
        }else if(!p2.equals(p1)){
                Toast.makeText(this, R.string.erro, Toast.LENGTH_SHORT).show();
            }else {
                String txt = String.valueOf(nome.getText());
                String tx = String.valueOf(R.string.sucesso);
                Toast.makeText(this,tx  + txt, Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(this, MainActivity.class);
                intent.putExtra("email", email);
                startActivity(intent);

            }
        }
    }

