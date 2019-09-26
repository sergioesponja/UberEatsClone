package com.example.mainactivity.modelos.adapter;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationManager;
import android.os.Build;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.core.app.ActivityCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mainactivity.config.Config;
import com.example.mainactivity.modelos.Comida;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;

import com.example.mainactivity.R;
import com.example.mainactivity.ui.navigationDrawerClases.NavigationDrawerActivityCliente;
import com.example.mainactivity.ui.navigationDrawerClases.ui.inicio.InicioFragment;
import com.example.mainactivity.ui.navigationDrawerClases.ui.pedidos.PedidosFragment;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.paypal.android.sdk.payments.PayPalConfiguration;
import com.paypal.android.sdk.payments.PayPalPayment;
import com.paypal.android.sdk.payments.PayPalService;
import com.paypal.android.sdk.payments.PaymentActivity;

public class AdapterListaComidas extends RecyclerView.Adapter<ViewHolderDatos> {

    ArrayList<Comida> lista_comidas;
    NavigationDrawerActivityCliente act;
    protected static String monto;


    public AdapterListaComidas(ArrayList<Comida> lista_comidas, NavigationDrawerActivityCliente act) {
        this.lista_comidas = lista_comidas;
        this.act = act;
    }

    @NonNull
    @Override
    public ViewHolderDatos onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list, null, false);
        return new ViewHolderDatos(view, act);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderDatos holder, int position) {
        holder.asignarDatos(lista_comidas.get(position));
    }


    @Override
    public int getItemCount() {
        return lista_comidas.size();
    }

    public static String getMonto() {
        return monto;
    }

    public static void setMonto(String monto) {
        AdapterListaComidas.monto = monto;
    }
}




