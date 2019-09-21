package com.example.mainactivity.ui.cliente.MenuPrueba;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

import com.example.mainactivity.R;
import com.example.mainactivity.modelos.Comida;
import com.example.mainactivity.modelos.adapter.AdapterListaComidas;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.GenericTypeIndicator;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ListaComidas extends AppCompatActivity {

    private DatabaseReference dbreference;
    private FirebaseAuth auth;

    private ArrayList<Comida> comidas;
    RecyclerView recycler;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_comidas);

        dbreference = FirebaseDatabase.getInstance().getReference("comidas");
        auth = FirebaseAuth.getInstance();
        recycler = findViewById(R.id.recycler_lista_comidas);
        comidas = new ArrayList<Comida>();



        dbreference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists()){


                    for(DataSnapshot x: dataSnapshot.getChildren()){
                        Comida comida = x.getValue(Comida.class);
                        comidas.add(comida);
                    }
                    Comida c1 = new Comida(1,1,30,"panqueques","pan");
                    comidas.add(c1);
                    comidas.add(c1);
                    comidas.add(c1);
                    comidas.add(c1);
                    comidas.add(c1);
                    comidas.add(c1);
                    comidas.add(c1);
                    comidas.add(c1);

                    recycler.setLayoutManager(new LinearLayoutManager(ListaComidas.this,LinearLayoutManager.VERTICAL,false));

                    AdapterListaComidas adapter = new AdapterListaComidas(comidas);
                    recycler.setAdapter(adapter);
                }
            }


            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });








    }
}
