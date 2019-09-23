package com.example.mainactivity.ui.navigationDrawerClases.ui.inicio;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mainactivity.R;
import com.example.mainactivity.modelos.Comida;
import com.example.mainactivity.modelos.adapter.AdapterListaComidas;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class InicioFragment extends Fragment {

    private ArrayList<Comida> lista_comidas;
    RecyclerView recycler;
    DatabaseReference dbreference;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_home, container, false);
        dbreference = FirebaseDatabase.getInstance().getReference("comidas");

        recycler = root.findViewById(R.id.recycler_comidas);
        recycler.setLayoutManager(new LinearLayoutManager(root.getContext(),LinearLayoutManager.VERTICAL,false));
        lista_comidas = new ArrayList<Comida>();


        dbreference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists()){


                    for(DataSnapshot x: dataSnapshot.getChildren()){
                        Comida comida = x.getValue(Comida.class);
                        lista_comidas.add(comida);
                    }

                    AdapterListaComidas adapter = new AdapterListaComidas(lista_comidas);
                    recycler.setAdapter(adapter);
                }
            }


            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        return root;

    }
}