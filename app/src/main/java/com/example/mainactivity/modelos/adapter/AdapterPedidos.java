package com.example.mainactivity.modelos.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mainactivity.R;
import com.example.mainactivity.modelos.Pedido;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class AdapterPedidos extends RecyclerView.Adapter<AdapterPedidos.ViewHolderDatosPedidos> {

    ArrayList<Pedido> lista_pedidos;

    public AdapterPedidos(ArrayList<Pedido> lista_pedidos) {
        this.lista_pedidos = lista_pedidos;
    }


    @NonNull
    @Override
    public ViewHolderDatosPedidos onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.pedido_item, null, false);
        return new ViewHolderDatosPedidos(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderDatosPedidos holder, int position) {
        holder.asignarDatos(lista_pedidos.get(position));
    }

    @Override
    public int getItemCount() {
        return lista_pedidos.size();
    }










    public class ViewHolderDatosPedidos extends RecyclerView.ViewHolder {

        TextView clave, nombre_platillo, estatus_pedido, estatus_pago, fecha_orden;

        private DatabaseReference dbreference;
        private FirebaseUser user;

        public ViewHolderDatosPedidos(@NonNull View itemView) {
            super(itemView);

            clave = itemView.findViewById(R.id.clave);
            nombre_platillo = itemView.findViewById(R.id.nombre_platillo);
            estatus_pedido = itemView.findViewById(R.id.estatus_pedido);
            estatus_pago = itemView.findViewById(R.id.estatus_pago);
            fecha_orden = itemView.findViewById(R.id.fecha_orden);

            dbreference = FirebaseDatabase.getInstance().getReference("pedidos");
            user = FirebaseAuth.getInstance().getCurrentUser();
        }

        public void asignarDatos(Pedido pedido) {
            clave.setText(pedido.getId());
            nombre_platillo.setText(pedido.getId_comida());
            estatus_pago.setText(Boolean.toString(pedido.isEstatus_pago()));
            estatus_pedido.setText(Boolean.toString(pedido.isEstatus_pedido()));
            fecha_orden.setText(pedido.getFecha());
        }


    }
}
