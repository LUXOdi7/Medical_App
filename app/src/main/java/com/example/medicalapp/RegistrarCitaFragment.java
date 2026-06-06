package com.example.medicalapp;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.example.medicalapp.data.Medico;
import com.example.medicalapp.databinding.FragmentRegistrarCitaBinding;
import com.example.medicalapp.data.DatosClinica;
import com.example.medicalapp.model.EspecialidadOLD;

import java.util.ArrayList;

public class RegistrarCitaFragment extends Fragment {

    FragmentRegistrarCitaBinding binding;
    ArrayList<Medico> listaMedicos;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentRegistrarCitaBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        // Cargar las especialidades y medicos
        //DatosClinica.cargarDatosInicialesEspecialidad();
        //DatosClinica.cargarDatosInicialesMedicos();
        // Cargar en el Autocompleted de Especialidades
        cargarEspecialidades();

        binding.actvEspecialidadRegistro.setOnItemClickListener((parent, view1, position, id) -> {
            String especialidadSeleccionada = binding.actvEspecialidadRegistro.getText().toString().trim();
            filtrarMedicosPorEspecialidad(especialidadSeleccionada);
        });

    }

    private void filtrarMedicosPorEspecialidad(String especialidadSeleccionada) {
        if (listaMedicos != null) {
            listaMedicos.clear();
        }
        //Obtener los médicos que pertenezcan a la especialidad seleccionada
        listaMedicos = DatosClinica.obtenerListaMedicosPorEspecialidad(especialidadSeleccionada);
        cargarMedicos(listaMedicos);

    }

    private void cargarMedicos(ArrayList<Medico> nuevaLista) {
        ArrayAdapter<Medico> adapter = new ArrayAdapter<>(
                requireContext(),
                android.R.layout.simple_dropdown_item_1line,
                listaMedicos
        );
        binding.actvMedicoRegistro.setAdapter(adapter);
    }


    private void cargarEspecialidades() {
        ArrayAdapter<EspecialidadOLD> adapter = new ArrayAdapter<>(
                requireContext(),
                android.R.layout.simple_dropdown_item_1line,
                DatosClinica.listaEspecialidades
        );
        binding.actvEspecialidadRegistro.setAdapter(adapter);

    }



    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}