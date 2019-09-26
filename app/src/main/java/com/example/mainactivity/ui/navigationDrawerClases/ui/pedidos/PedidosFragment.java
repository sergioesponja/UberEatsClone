package com.example.mainactivity.ui.navigationDrawerClases.ui.pedidos;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mainactivity.R;
import com.example.mainactivity.modelos.Comida;
import com.example.mainactivity.modelos.Pedido;
import com.example.mainactivity.modelos.adapter.AdapterListaComidas;
import com.example.mainactivity.modelos.adapter.AdapterPedidos;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.GenericTypeIndicator;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class PedidosFragment extends Fragment {

    private ArrayList<Pedido> lista_pedidos;
    RecyclerView recycler;
    DatabaseReference dbreference;
    private FirebaseUser user;
    ArrayList<Comida> lista_comidas;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_pedidos, container, false);

        recycler = root.findViewById(R.id.recycler_pedidos);
        recycler.setLayoutManager(new LinearLayoutManager(root.getContext(),LinearLayoutManager.VERTICAL,false));
        lista_pedidos = new ArrayList<>();
        lista_comidas = new ArrayList<>();

        dbreference = FirebaseDatabase.getInstance().getReference("pedidos");
        user = FirebaseAuth.getInstance().getCurrentUser();

        dbreference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists()){


                    for(DataSnapshot x: dataSnapshot.getChildren()){
                        if(x.child("id_cliente").getValue().equals(user.getUid())) {
                            Pedido pedido = new Pedido();
                            pedido.setId(x.child("id").getValue().toString());
                            pedido.setId_cliente(x.child("id_cliente").getValue().toString());
                            pedido.setId_comida(x.child("id_comida").getValue().toString());
                            pedido.setFecha(x.child("fecha").getValue().toString());
                            pedido.setCosto_total(x.child("costo_total").getValue().toString());
                            pedido.setEstatus_pago(Boolean.parseBoolean(x.child("estatus_pago").getValue().toString()));
                            pedido.setEstatus_pedido(Boolean.parseBoolean(x.child("estatus_pedido").getValue().toString()));
                            pedido.setId_repartidor("0");
                            lista_pedidos.add(pedido);
                        }
                    }


                AdapterPedidos adapter = new AdapterPedidos(lista_pedidos);
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