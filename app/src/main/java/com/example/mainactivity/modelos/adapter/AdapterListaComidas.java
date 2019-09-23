package com.example.mainactivity.modelos.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mainactivity.modelos.Comida;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;

import com.example.mainactivity.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AdapterListaComidas extends RecyclerView.Adapter<AdapterListaComidas.ViewHolderDatos> {

    ArrayList<Comida> lista_comidas;

    public AdapterListaComidas(ArrayList<Comida> lista_comidas) {
        this.lista_comidas = lista_comidas;
    }

    @NonNull
    @Override
    public ViewHolderDatos onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list, null, false);
        return new ViewHolderDatos(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderDatos holder, int position) {
        holder.asignarDatos(lista_comidas.get(position));
    }


    @Override
    public int getItemCount() {
        return lista_comidas.size();
    }









    public class ViewHolderDatos extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView nombre, ingredientes, precio, restaurante;
        Button comprar;
        TextView id_comida;

        private DatabaseReference dbreference;
        private FirebaseUser user;
        Map<String, Object> mapa;

        public ViewHolderDatos(@NonNull View itemView) {
            super(itemView);

            nombre = itemView.findViewById(R.id.nombre);
            ingredientes = itemView.findViewById(R.id.ingredientes);
            precio = itemView.findViewById(R.id.precio);
            restaurante = itemView.findViewById(R.id.restaurante);
            id_comida = itemView.findViewById(R.id.id_comida);

            comprar = itemView.findViewById(R.id.comprar);
            comprar.setOnClickListener(this);

            mapa = new HashMap<>();
            dbreference = FirebaseDatabase.getInstance().getReference("pedidos");
            user = FirebaseAuth.getInstance().getCurrentUser();


        }

        public void asignarDatos(Comida comida) {
            nombre.setText(comida.getNombre());
            ingredientes.setText(comida.getIngredientes());
            precio.setText(Integer.toString(comida.getPrecio()));
            restaurante.setText(Integer.toString(comida.getIdRestaurante()));
            id_comida.setText(Integer.toString(comida.getId()));
        }

        @Override
        public void onClick(View v) {
            SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
            mapa.put("fecha", formato.format(new GregorianCalendar().getTime()));
            mapa.put("id_comida", id_comida.getText().toString());
            mapa.put("estatus_pago", false);
            mapa.put("estatus_pedido", false);
            mapa.put("costo_total", precio.getText().toString());
            mapa.put("id_repartidor", 0);
            mapa.put("id_cliente", user.getUid());
            String clave = dbreference.push().getKey();
            mapa.put("id",clave);

            dbreference = FirebaseDatabase.getInstance().getReference("pedidos");

            dbreference.push().updateChildren(mapa).addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    if(task.isSuccessful()){
                        Toast.makeText(comprar.getContext(),"Se registro el pedido correctamente",Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }
}
