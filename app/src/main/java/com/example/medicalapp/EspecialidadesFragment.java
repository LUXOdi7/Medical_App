package com.example.medicalapp;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.medicalapp.adapter.EspecialidadAdaptador;
import com.example.medicalapp.databinding.FragmentEspecialidadesBinding;
import com.example.medicalapp.data.DatosClinica;
import com.example.medicalapp.model.Especialidad;

import java.util.ArrayList;

public class EspecialidadesFragment extends Fragment {

    FragmentEspecialidadesBinding binding;
    ArrayList<Especialidad> listaEspecialidades;
    EspecialidadAdaptador especialidadAdaptador;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentEspecialidadesBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        //Cargar las especialidades almacenadas en el array
        DatosClinica.cargarDatosInicialesEspecialidad();

        listaEspecialidades = DatosClinica.listaEspecialidades;
        especialidadAdaptador = new EspecialidadAdaptador(listaEspecialidades);

        //Configurar el RecyclerView
        binding.rvEspecialidades.setLayoutManager(new LinearLayoutManager(requireContext()));

        //Asignar el adaptador al RecyclerView
        binding.rvEspecialidades.setAdapter(especialidadAdaptador);


    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}