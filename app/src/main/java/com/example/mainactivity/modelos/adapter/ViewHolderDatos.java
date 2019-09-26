package com.example.mainactivity.modelos.adapter;

import android.content.Intent;
import android.location.Location;
import android.os.Build;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mainactivity.R;
import com.example.mainactivity.modelos.Comida;
import com.example.mainactivity.ui.navigationDrawerClases.NavigationDrawerActivityCliente;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.paypal.android.sdk.payments.PayPalPayment;
import com.paypal.android.sdk.payments.PayPalService;
import com.paypal.android.sdk.payments.PaymentActivity;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;

public class ViewHolderDatos extends RecyclerView.ViewHolder implements View.OnClickListener {

    static TextView nombre, ingredientes, precio, restaurante,id_restaurante;
    static Button comprar;
    static TextView id_comida;

    static private DatabaseReference dbreference;
    static private FirebaseUser user;
    static Map<String, Object> mapa;
    NavigationDrawerActivityCliente act;

    static private FusedLocationProviderClient mfuseLocationClient;


    public ViewHolderDatos(@NonNull View itemView, NavigationDrawerActivityCliente act) {
        super(itemView);
        this.act = act;

        nombre = itemView.findViewById(R.id.nombre);
        ingredientes = itemView.findViewById(R.id.ingredientes);
        precio = itemView.findViewById(R.id.precio);
        restaurante = itemView.findViewById(R.id.restaurante);
        id_comida = itemView.findViewById(R.id.id_comida);
        id_restaurante = itemView.findViewById(R.id.id_restaurante);

        comprar = itemView.findViewById(R.id.comprar);
        comprar.setOnClickListener(this);

        mapa = new HashMap<>();
        dbreference = FirebaseDatabase.getInstance().getReference("pedidos");
        user = FirebaseAuth.getInstance().getCurrentUser();

        Intent intent = new Intent(act, PayPalService.class);
        intent.putExtra(PayPalService.EXTRA_PAYPAL_CONFIGURATION,NavigationDrawerActivityCliente.getConfig());
        act.startService(intent);

        mfuseLocationClient = LocationServices.getFusedLocationProviderClient(act);


    }

    void asignarDatos(Comida comida) {
        nombre.setText(comida.getNombre());
        ingredientes.setText(comida.getIngredientes());
        precio.setText(Integer.toString(comida.getPrecio()));
        restaurante.setText(comida.getN_restaurante());
        id_restaurante.setText(Integer.toString(comida.getIdRestaurante()));
        id_comida.setText(Integer.toString(comida.getId()));
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onClick(View v) {
        procesarPago();
    }




    private void procesarPago()
    {
        AdapterListaComidas.setMonto(precio.getText().toString());
        PayPalPayment payPalPayment = new PayPalPayment(new BigDecimal(String.valueOf(AdapterListaComidas.getMonto())),"MXN","Pagado por usuario",PayPalPayment.PAYMENT_INTENT_SALE);
        Intent intent = new Intent(act, PaymentActivity.class);
        intent.putExtra(PayPalService.EXTRA_PAYPAL_CONFIGURATION,NavigationDrawerActivityCliente.getConfig());
        intent.putExtra(PaymentActivity.EXTRA_PAYMENT,payPalPayment);
        act.startActivityForResult(intent,NavigationDrawerActivityCliente.getPaypalRequestCode());
    }

    public static void guardarDatos(){
        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
        mapa.put("fecha", formato.format(new GregorianCalendar().getTime()));
        mapa.put("id_comida", id_comida.getText().toString());
        mapa.put("estatus_pago", true);
        mapa.put("estatus_pedido", false);
        mapa.put("costo_total", precio.getText().toString());
        mapa.put("id_repartidor", 0);
        mapa.put("id_cliente", user.getUid());
        String clave = dbreference.push().getKey();
        mapa.put("id", clave);

        mfuseLocationClient.getLastLocation().addOnSuccessListener(new OnSuccessListener<Location>() {
            @Override
            public void onSuccess(Location location) {
                if(location != null){
                    mapa.put("latitud", location.getLatitude());
                    mapa.put("longitud", location.getLongitude());



                    dbreference = FirebaseDatabase.getInstance().getReference("pedidos");

                    dbreference.push().updateChildren(mapa).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()) {
                                Toast.makeText(comprar.getContext(), "Se registro el pedido correctamente", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });

                }
            }
        });


    }


}
