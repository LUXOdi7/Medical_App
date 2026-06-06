package com.example.medicalapp;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.medicalapp.data.Especialidad;
import com.example.medicalapp.databinding.DialogAgregarMedicoBinding;
import com.example.medicalapp.request.MedicoRegistrarRequest;
import com.example.medicalapp.response.EspecialidadListadoResponse;
import com.example.medicalapp.response.MedicoRegistrarResponse;
import com.example.medicalapp.retrofit.ApiService;
import com.example.medicalapp.retrofit.RetrofitClient;
import com.example.medicalapp.util.Helper;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AgregarMedicoDialog extends BottomSheetDialogFragment {
    private DialogAgregarMedicoBinding binding;
    ArrayList<Especialidad> listaEspecialidades = new ArrayList<>();

    private int especialidad_id;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = DialogAgregarMedicoBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding.txtEmail.requestFocus();

        //Botón cancelar
        binding.btnCancelar.setOnClickListener(v -> dismiss());

        //Botón guardar
        binding.btnGuardar.setOnClickListener(v -> registrarMedico());

        leerEspecialidades();

        binding.actvEspecialidad.setOnItemClickListener((parent, view1, position, id) -> {
            Especialidad especialidadSeleccionada = (Especialidad) parent.getItemAtPosition(position);
            especialidad_id = especialidadSeleccionada.getId();
        });

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

                    }

                }
            }
            @Override
            public void onFailure(Call<EspecialidadListadoResponse> call, Throwable t) {
                //Manejar el error

            }
        });
    }

    private void registrarMedico() {
        Helper.mensajeConfirmacion(requireContext(), "Confirme", "¿Seguro de registrar el medico?", "Si registrar", "No",() -> {
            String email = binding.txtEmail.getText().toString();
            String password =  binding.txtPassword.getText().toString();
            String nombres = binding.txtNombres.getText().toString();
            String apellidos = binding.txtApellidos.getText().toString();
            String dni = binding.txtDni.getText().toString();
            String cmp = binding.txtCmp.getText().toString();
            String telefono = binding.txtTelefono.getText().toString();
            String consultorio = binding.txtConsultorio.getText().toString();

            ApiService apiService = RetrofitClient.createService();
            Call<MedicoRegistrarResponse> call = apiService.registrarMedico(new MedicoRegistrarRequest(
                    email,
                    password,
                    nombres,
                    apellidos,
                    dni,
                    cmp,
                    telefono,
                    consultorio,
                    especialidad_id
            ));

            call.enqueue(new Callback<MedicoRegistrarResponse>() {
                @Override
                public void onResponse(Call<MedicoRegistrarResponse> call, Response<MedicoRegistrarResponse> response) {
                    if(response.isSuccessful()) {
                        Toast.makeText(requireContext(),"Medico registrado correctamente", Toast.LENGTH_SHORT ).show();
                        dismiss();
                    }else {
                        try {
                            JSONObject jsonError = new JSONObject(response.errorBody().string());
                            String error = jsonError.getString("message");
                            Helper.mensajeError(getContext(), "Error al registrar medico", error);
                        }catch (IOException | JSONException e) {
                            throw new RuntimeException(e);
                        }
                    }
                }

                @Override
                public void onFailure(Call<MedicoRegistrarResponse> call, Throwable t) {

                }
            });

        } );

    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
