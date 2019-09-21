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

import java.util.ArrayList;

public class InicioFragment extends Fragment {

    private ArrayList<Comida> lista_comidas;
    RecyclerView recycler;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_home, container, false);

        recycler = root.findViewById(R.id.recycler_comidas);
        recycler.setLayoutManager(new LinearLayoutManager(root.getContext(),LinearLayoutManager.VERTICAL,false));
        lista_comidas = new ArrayList<Comida>();
        Comida c1 = new Comida(1,1,30,"panqueques","pan");
        lista_comidas.add(c1);

        AdapterListaComidas adapter = new AdapterListaComidas(lista_comidas);
        recycler.setAdapter(adapter);

        return root;

    }
}