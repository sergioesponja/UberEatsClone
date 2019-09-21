package com.example.mainactivity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.mainactivity.ui.cliente.MenuPrueba.ListaComidas;
import com.example.mainactivity.ui.login.LoginActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class SignUpScreen extends AppCompatActivity {

    private EditText nombre, textEmail, password;
    private Button btnSignUp,btnSendToLogin;

    //Variables de los datos del usuario para la BD.
    private String name, email, pass;

    FirebaseAuth auth;
    DatabaseReference dbr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        FirebaseApp.initializeApp(SignUpScreen.this);

        setContentView(R.layout.activity_main);


        auth = FirebaseAuth.getInstance();
        dbr = FirebaseDatabase.getInstance().getReference();

        nombre = (EditText) findViewById(R.id.nombre);
        textEmail = (EditText) findViewById(R.id.textEmail);
        password = (EditText) findViewById(R.id.password);

        btnSignUp = (Button) findViewById(R.id.btnSignUp);
        btnSendToLogin = (Button) findViewById(R.id.btnSignIn);



        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                name = nombre.getText().toString();
                email = textEmail.getText().toString();
                pass = password.getText().toString();

                if (!(name.isEmpty() && email.isEmpty() && pass.isEmpty())){
                    if(pass.length()>=6){
                        registerUser();
                    }else{
                        Toast.makeText(SignUpScreen.this,"El campo password debe tener al menos seis caracteres", Toast.LENGTH_SHORT).show();
                    }

                }else {
                    Toast.makeText(SignUpScreen.this,"Debe completar los campós", Toast.LENGTH_SHORT).show();
                }


            }
        });

        btnSendToLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SignUpScreen.this, LoginActivity.class));
            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();
        Toast.makeText(this,"OnStart", Toast.LENGTH_SHORT).show();
        //La actividad está a punto de ser visible...
    }

    @Override
    protected void onResume() {
        super.onResume();
        Toast.makeText(this,"OnResume", Toast.LENGTH_SHORT).show();
        //La actividad se ha vuelto visible (se reanuda)...
    }

    @Override
    protected void onPause() {
        super.onPause();
        Toast.makeText(this,"OnPause", Toast.LENGTH_SHORT).show();
        //La actividad pasa a segundo plano...
    }

    @Override
    protected void onStop() {
        super.onStop();
        Toast.makeText(this,"OnStop", Toast.LENGTH_SHORT).show();
        //La actividad se detiene y no está visible...
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Toast.makeText(this,"OnDestroy", Toast.LENGTH_SHORT).show();
        //La actividad se concluye (se destruye)...
    }


    private void registerUser(){
      auth.createUserWithEmailAndPassword(email,pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
          @Override
          public void onComplete(@NonNull Task<AuthResult> task) {
              if(task.isSuccessful()) {

                  Map<String,Object> mapa = new HashMap<>();
                  mapa.put("name",name);
                  mapa.put("email",email);
                  mapa.put("password",pass);

                  String id = auth.getCurrentUser().getUid();
               dbr.child("Users").child(id).setValue(mapa).addOnCompleteListener(new OnCompleteListener<Void>() {
                   @Override
                   public void onComplete(@NonNull Task<Void> task2) {
                       if(task2.isSuccessful()) {
                       startActivity(new Intent(SignUpScreen.this, ListaComidas.class));
                       finish();
                       }else{
                           Toast.makeText(SignUpScreen.this,"No fue posible registrar los datos correctamente", Toast.LENGTH_SHORT).show();

                       }

                   }
               });
              }else{
                  Toast.makeText(SignUpScreen.this,"No es posible registrar este usuario", Toast.LENGTH_SHORT).show();
              }
          }
      });
    }
}
