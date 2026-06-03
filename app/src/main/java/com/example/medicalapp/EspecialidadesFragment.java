package com.example.medicalapp;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.medicalapp.adapter.EspecialidadAdapter;
import com.example.medicalapp.data.Especialidad;
import com.example.medicalapp.databinding.FragmentEspecialidadesBinding;
import com.example.medicalapp.data.DatosClinica;
import com.example.medicalapp.model.EspecialidadOLD;
import com.example.medicalapp.response.EspecialidadListadoResponse;
import com.example.medicalapp.retrofit.ApiService;
import com.example.medicalapp.retrofit.RetrofitClient;

import java.util.ArrayList;
import java.util.Arrays;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EspecialidadesFragment extends Fragment {

    FragmentEspecialidadesBinding binding;
    ArrayList<Especialidad> listaEspecialidades = new ArrayList<>();
    EspecialidadAdapter especialidadAdapter;

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

        especialidadAdapter = new EspecialidadAdapter(listaEspecialidades);

        //Configurar el RecyclerView
        binding.rvEspecialidades.setLayoutManager(new LinearLayoutManager(requireContext()));

        //Asignar el adaptador al RecyclerView
        binding.rvEspecialidades.setAdapter(especialidadAdapter);

        //Leer las especialidades del API REST
        leerEspecialidades();

    }

    private void leerEspecialidades() {
        ApiService apiService = RetrofitClient.createService();
        Call<EspecialidadListadoResponse> call= apiService.getEspecialidades();
        call.enqueue(new Callback<EspecialidadListadoResponse>() {
            @Override
            public void onResponse(Call<EspecialidadListadoResponse> call, Response<EspecialidadListadoResponse> response) {
                if (response.isSuccessful()) {
                    //Limpiar las especialidades cargadas en el ArrayList
                    listaEspecialidades.clear();
                    //Cargar las especialidades que vienen del API REST
                    listaEspecialidades.addAll(Arrays.asList(response.body().getData()));
                    //Refrescar el listado del adapter
                    especialidadAdapter.actualizarListaEspecialidad(listaEspecialidades);
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