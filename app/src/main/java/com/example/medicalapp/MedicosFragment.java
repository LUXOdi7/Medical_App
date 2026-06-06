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
import com.example.medicalapp.data.Especialidad;
import com.example.medicalapp.data.Login;
import com.example.medicalapp.data.Medico;
import com.example.medicalapp.databinding.FragmentMedicosBinding;
import com.example.medicalapp.data.DatosClinica;
import com.example.medicalapp.model.EspecialidadOLD;
import com.example.medicalapp.response.EspecialidadListadoResponse;
import com.example.medicalapp.response.MedicoPorEspecialidadListadoResponse;
import com.example.medicalapp.retrofit.ApiService;
import com.example.medicalapp.retrofit.RetrofitClient;

import java.util.ArrayList;
import java.util.Arrays;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MedicosFragment extends Fragment {

    FragmentMedicosBinding binding;
    ArrayList<Medico> listaMedicos = new ArrayList<>();
    ArrayList<Especialidad> listaEspecialidades = new ArrayList<>();
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

        String rol = Login.DATOS_SESION.getRol();
        medicoAdapter = new MedicoAdapter(listaMedicos, rol);
        binding.rvMedicos.setLayoutManager(new LinearLayoutManager(requireContext()));
        binding.rvMedicos.setAdapter(medicoAdapter);

        leerEspecialidades();

        //Al seleccionar un item (especialidad) del AutoCompleteText se debe mostrar los médicos pertenecientes a dicha especialidad
        binding.actvEspecialidad.setOnItemClickListener((parent, view1, position, id) -> {
            Especialidad especialidadSeleccionada = (Especialidad) parent.getItemAtPosition(position);
            int idEspecialidad = especialidadSeleccionada.getId();
            filtrarMedicosPorEspecialidad(idEspecialidad);
        });

        if (!"ADMINISTRATIVO".equals(rol)) {
            binding.fabAgregarMedico.setVisibility(View.GONE);
        } else {
            binding.fabAgregarMedico.setOnClickListener(v -> {
                AgregarMedicoDialog dialog = new AgregarMedicoDialog();
                dialog.show(getParentFragmentManager(), "AgregarMedicoDialog");
            });
        }
    }

    private void filtrarMedicosPorEspecialidad(int especialidadSeleccionada) {

        ApiService apiService = RetrofitClient.createService();
        Call<MedicoPorEspecialidadListadoResponse> call = apiService.getMedicosPorEspecialidad(especialidadSeleccionada);

        call.enqueue(new Callback<MedicoPorEspecialidadListadoResponse>() {
            @Override
            public void onResponse(Call<MedicoPorEspecialidadListadoResponse> call, Response<MedicoPorEspecialidadListadoResponse> response) {
                if (response.isSuccessful()) {
                    //Limpiar el arrayList
                    listaMedicos.clear();
                    listaMedicos.addAll(Arrays.asList(response.body().getData()));
                    medicoAdapter.actualizarLista(listaMedicos);
                }
            }

            @Override
            public void onFailure(Call<MedicoPorEspecialidadListadoResponse> call, Throwable t) {

            }
        });


        //Enviar al adaptador a la lista de mpedicos de la especialidad seleccionada
        medicoAdapter.actualizarLista(listaMedicos);

    }

    private void leerEspecialidades() {
        ApiService apiService = RetrofitClient.createService();
        Call<EspecialidadListadoResponse> call= apiService.getEspecialidades();
        call.enqueue(new Callback<EspecialidadListadoResponse>() {
            @Override
            public void onResponse(Call<EspecialidadListadoResponse> call, Response<EspecialidadListadoResponse> response) {
                if (response.isSuccessful()) {
                    listaEspecialidades.clear();
                    //Cargar las especialidades que vienen del API REST
                    listaEspecialidades.addAll(Arrays.asList(response.body().getData()));
                    //Refrescar el listado del adapter

                    ArrayAdapter<Especialidad> adapter = new ArrayAdapter<>(
                            requireContext(),
                            android.R.layout.simple_dropdown_item_1line,
                            listaEspecialidades
                    );
                    binding.actvEspecialidad.setAdapter(adapter);

                    if (!listaEspecialidades.isEmpty()) {
                        Especialidad primeraEspecialidad = listaEspecialidades.get(0);
                        binding.actvEspecialidad.setText(primeraEspecialidad.getNombre(), false);
                        filtrarMedicosPorEspecialidad(primeraEspecialidad.getId());
                    }

                }
            }
            @Override
            public void onFailure(Call<EspecialidadListadoResponse> call, Throwable t) {
                //Manejar el error

            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}