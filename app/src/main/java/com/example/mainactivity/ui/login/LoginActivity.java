package com.example.mainactivity.ui.login;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.mainactivity.R;
import com.example.mainactivity.ui.navigationDrawerClases.NavigationDrawerActivityCliente;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {

    private String username = "",password = "";
    private FirebaseAuth auth;
    private EditText usernameEditText, passwordEditText;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


   usernameEditText = (EditText)findViewById(R.id.username);
   passwordEditText   = (EditText)findViewById(R.id.password);
        final Button loginButton = findViewById(R.id.login);

        auth = FirebaseAuth.getInstance();

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                username = usernameEditText.getText().toString();
                password = passwordEditText.getText().toString();

                if(!(username.isEmpty() && password.isEmpty())){
                    loginUser();
                }else{
                    Toast.makeText(LoginActivity.this,"Debe de llenar todos los campos", Toast.LENGTH_SHORT).show();

                }

            }
        });


    }

    private void loginUser(){
      auth.signInWithEmailAndPassword(username,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
          @Override
          public void onComplete(@NonNull Task<AuthResult> task) {
              if(task.isSuccessful()){
                  startActivity(new Intent(LoginActivity.this, NavigationDrawerActivityCliente.class));
                  finish();
              }else{
                  Toast.makeText(LoginActivity.this,"No se pudo iniciar sesion", Toast.LENGTH_SHORT).show();

              }
          }
      });
    }
}
