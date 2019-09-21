package com.example.mainactivity.modelos.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mainactivity.modelos.Comida;

import java.util.ArrayList;

import com.example.mainactivity.R;

public class AdapterListaComidas extends RecyclerView.Adapter<AdapterListaComidas.ViewHolderDatos> {

    ArrayList<Comida> lista_comidas;

    public AdapterListaComidas(ArrayList<Comida> lista_comidas) {
        this.lista_comidas = lista_comidas;
    }

    @NonNull
    @Override
    public ViewHolderDatos onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list,null,false);
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

    public class ViewHolderDatos extends RecyclerView.ViewHolder {

        TextView nombre,ingredientes,precio,restaurante;

        public ViewHolderDatos(@NonNull View itemView) {
            super(itemView);

            nombre = itemView.findViewById(R.id.nombre);
            ingredientes = itemView.findViewById(R.id.ingredientes);
            precio = itemView.findViewById(R.id.precio);
            restaurante = itemView.findViewById(R.id.restaurante);




        }

        public void asignarDatos(Comida comida) {
            nombre.setText(comida.getNombre());
            ingredientes.setText(comida.getIngredientes());
            precio.setText(Integer.toString(comida.getPrecio()));
            restaurante.setText(Integer.toString(comida.getIdRestaurante()));



        }
    }
}
