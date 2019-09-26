package com.example.mainactivity.ui.navigationDrawerClases.ui.logout;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.mainactivity.R;
import com.example.mainactivity.registro.SignUpScreen;
import com.google.firebase.auth.FirebaseAuth;

public class LogoutFragment extends Fragment {

    private SendViewModel sendViewModel;
    private FirebaseAuth auth;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        auth = FirebaseAuth.getInstance();
        auth.signOut();
        Intent intent = new Intent(this.getActivity(), SignUpScreen.class);
        this.getActivity().finish();
         startActivity(intent);

     return null;

    }
}