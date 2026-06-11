package com.example.medicalapp;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.example.medicalapp.databinding.FragmentRegistrarCitaBinding;
import com.example.medicalapp.data.DatosClinica;
import com.example.medicalapp.model.EspecialidadOLD;
import com.example.medicalapp.model.Medico;

import java.util.ArrayList;

public class RegistrarCitaFragment extends Fragment {

    public FragmentRegistrarCitaBinding binding;

    public RegistrarCitaFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentRegistrarCitaBinding.inflate(inflater, container, false);
        return binding.getRoot();

//        binding.bnvMenuOpcionesCitas.setOnItemSelectedListener(MenuItem ->{
//            return RegistrarCitaFragment.this.onNavigationItemSelected(MenuItem);
//        });
    }

    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    private boolean onNavigationItemSelected(MenuItem menuItem) {
        //Implementar la navegación
        int menuId = menuItem.getItemId();

        Fragment fragment = new Fragment();
        if (menuId == R.id.menu_horarios_disponibles) {
            fragment = new HorariosDisponiblesFragment();
        } else if (menuId == R.id.menu_citas_registradas) {
            fragment = new MisCitasFragment();
        } else if (menuId == R.id.menu_historial_citas) {
            fragment = new HistorialCitasFragment();
        }

        //Mostrar el fragment
        FragmentTransaction fragmentTransaction =
                this.getActivity().getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.contenedor, fragment);
        fragmentTransaction.commit();

        return true;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}