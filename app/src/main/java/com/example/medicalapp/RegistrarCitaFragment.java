package com.example.medicalapp;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.example.medicalapp.databinding.FragmentRegistrarCitaBinding;
import com.example.medicalapp.data.DatosClinica;
import com.example.medicalapp.model.EspecialidadOLD;
import com.example.medicalapp.model.Medico;

import java.util.ArrayList;

public class RegistrarCitaFragment extends Fragment {

    FragmentRegistrarCitaBinding binding;
    ArrayList<com.example.medicalapp.model.Medico> listaMedicos;
    public RegistrarCitaFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentRegistrarCitaBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}