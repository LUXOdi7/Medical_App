package com.example.medicalapp;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.example.medicalapp.data.Especialidad;
import com.example.medicalapp.databinding.FragmentHorariosDisponiblesBinding;
import com.example.medicalapp.response.EspecialidadListadoResponse;
import com.example.medicalapp.retrofit.ApiService;
import com.example.medicalapp.retrofit.RetrofitClient;
import com.example.medicalapp.util.Helper;

import java.util.ArrayList;
import java.util.Arrays;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class HorariosDisponiblesFragment extends Fragment {

    FragmentHorariosDisponiblesBinding binding;
    ArrayList<Especialidad> lista = new ArrayList<>();
    public HorariosDisponiblesFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = FragmentHorariosDisponiblesBinding.inflate(inflater, container, false);

        //Cargar las especialidades en el AutoComplete text view
        cargarEspecialidades();

        //Configurar los chips (desativar los check)
        binding.chipDesde.setCheckable(false);
        binding.chipHasta.setCheckable(false);
        //Setear la fecha actual
        binding.chipDesde.setText(Helper.obtenerFechaActual());
        binding.chipHasta.setText(Helper.obtenerFechaActual());


        return binding.getRoot();
    }

    private void cargarEspecialidades() {
        ApiService apiService = RetrofitClient.createService();
        Call<EspecialidadListadoResponse> call = apiService.getEspecialidades();

        call.enqueue(new Callback<EspecialidadListadoResponse>() {
            @Override
            public void onResponse(Call<EspecialidadListadoResponse> call, Response<EspecialidadListadoResponse> response) {
                if(response.isSuccessful() && response.body().isStatus()){
                    //Limpiar las especialidades cargadas en el Arraylist
                    lista.clear();
                    //Cargar las especialidades que vienen del API REST(endpoint)
                    lista.addAll(Arrays.asList(response.body().getData()));
                    //Generar el arrayadapter
                    ArrayAdapter<Especialidad> adapter = new ArrayAdapter<>(
                            requireContext(),
                            android.R.layout.simple_dropdown_item_1line,
                            lista
                    );

                    //Setear el arrayAdapter al autoCompleteTextView
                    binding.actvEspecialidad.setAdapter(adapter);
                }
            }
            @Override
            public void onFailure(Call<EspecialidadListadoResponse> call, Throwable t) {
                Helper.mensajeError(requireContext(), "Error", t.getMessage());
            }
        });
    }

}