package com.example.medicalapp;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.example.medicalapp.adapter.MedicoAdapter;
import com.example.medicalapp.databinding.FragmentMedicosBinding;
import com.example.medicalapp.data.DatosClinica;
import com.example.medicalapp.model.EspecialidadOLD;
import com.example.medicalapp.model.Medico;

import java.util.ArrayList;

public class MedicosFragment extends Fragment {

    FragmentMedicosBinding binding;
    ArrayList<Medico> listaMedicos;
    MedicoAdapter medicoAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentMedicosBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        //Cargar las especialidades y médicos
        DatosClinica.cargarDatosInicialesEspecialidad();
        DatosClinica.cargarDatosInicialesMedicos();

        //Cargar las especialidades en el AutocompleteTextView
        cargarEspecialidades();

        //Instanciar el arrayList de médicos por especialidad
        listaMedicos = new ArrayList<>();

        //Instanciar el adaptador
        medicoAdapter = new MedicoAdapter(listaMedicos);

        //COnfigurar el recyclerView
        binding.rvMedicos.setLayoutManager(new LinearLayoutManager(requireContext()));

        //Asignar el adaptador al recyclerView
        binding.rvMedicos.setAdapter(medicoAdapter);

        //Al seleccionar un item (especialidad) del AutoCompleteText se debe mostrar los médicos pertenecientes a dicha especialidad
        binding.actvEspecialidad.setOnItemClickListener((parent, view1, position, id) -> {
            String especialidadSeleccionada = binding.actvEspecialidad.getText().toString().trim();
            filtrarMedicosPorEspecialidad(especialidadSeleccionada);
        });

        //Seleccionar de manera automática la primera especialidad
        if (! DatosClinica.listaEspecialidades.isEmpty()){
            String especialidad = DatosClinica.listaEspecialidades.get(0).getNombre();
            binding.actvEspecialidad.setText(especialidad, false);
            filtrarMedicosPorEspecialidad(especialidad);
        }

    }

    private void filtrarMedicosPorEspecialidad(String especialidadSeleccionada) {
        //Limpiar el arrayList
        listaMedicos.clear();
        //Obtener los medicos que pertenecen a la especialidad seleccionada
        listaMedicos = DatosClinica.obtenerListaMedicosPorEspecialidad(especialidadSeleccionada);
        //Enviar al adaptador a la lista de mpedicos de la especialidad seleccionada
        medicoAdapter.actualizarLista(listaMedicos);

    }

    private void cargarEspecialidades() {
        ArrayAdapter<EspecialidadOLD> adapter = new ArrayAdapter<>(
                requireContext(),
                android.R.layout.simple_dropdown_item_1line,
                DatosClinica.listaEspecialidades
        );
        binding.actvEspecialidad.setAdapter(adapter);

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}