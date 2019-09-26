package com.example.mainactivity.ui.navigationDrawerClases.ui.inicio;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mainactivity.R;
import com.example.mainactivity.modelos.Comida;
import com.example.mainactivity.modelos.Restaurante;
import com.example.mainactivity.modelos.adapter.AdapterListaComidas;
import com.example.mainactivity.ui.navigationDrawerClases.NavigationDrawerActivityCliente;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class InicioFragment extends Fragment {

    private ArrayList<Comida> lista_comidas;
    private ArrayList<Restaurante> lista_restaurantes;
    RecyclerView recycler;
    DatabaseReference dbreference;
    NavigationDrawerActivityCliente act;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_home, container, false);
        dbreference = FirebaseDatabase.getInstance().getReference("comidas");

        recycler = root.findViewById(R.id.recycler_comidas);
        recycler.setLayoutManager(new LinearLayoutManager(root.getContext(),LinearLayoutManager.VERTICAL,false));
        lista_comidas = new ArrayList<>();
        lista_restaurantes = new ArrayList<>();


        dbreference.addValueEventListener(new ValueEventListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists()){


                    for(DataSnapshot x: dataSnapshot.getChildren()){
                        Comida comida = x.getValue(Comida.class);
                        lista_comidas.add(comida);
                    }

                    dbreference = FirebaseDatabase.getInstance().getReference("Restaurantes");
                    dbreference.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                            for(DataSnapshot x: dataSnapshot.getChildren()){
                                Restaurante res = x.getValue(Restaurante.class);
                                lista_restaurantes.add(res);
                            }

                            for(Restaurante r: lista_restaurantes){
                                for(Comida c: lista_comidas){
                                    if(r.getId() == c.getIdRestaurante()){
                                        c.setN_restaurante(r.getNombre());
                                    }
                                }
                            }

                            act = (NavigationDrawerActivityCliente)getActivity();

                            AdapterListaComidas adapter = new AdapterListaComidas(lista_comidas,act);
                            recycler.setAdapter(adapter);
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });


                }
            }


            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        return root;

    }
}