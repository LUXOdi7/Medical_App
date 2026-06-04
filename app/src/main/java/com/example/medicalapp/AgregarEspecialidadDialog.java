package com.example.medicalapp;



import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.medicalapp.data.Login;
import com.example.medicalapp.databinding.DialogAgregarEspecialidadBinding;
import com.example.medicalapp.request.EspecialidadRegistrarRequest;
import com.example.medicalapp.response.EspecialidadRegistrarResponse;
import com.example.medicalapp.retrofit.ApiService;
import com.example.medicalapp.retrofit.RetrofitClient;
import com.example.medicalapp.util.Helper;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AgregarEspecialidadDialog extends BottomSheetDialogFragment {
    private DialogAgregarEspecialidadBinding binding;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = DialogAgregarEspecialidadBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding.txtNombre.requestFocus();



        //Botón cancelar
        binding.btnCancelar.setOnClickListener(v -> dismiss());

        //Botón guardar
        binding.btnGuardar.setOnClickListener(v -> registrarEspecialidad());
    }

    private void registrarEspecialidad() {
        Helper.mensajeConfirmacion(requireContext(), "Confirme", "¿Seguro de registrar el viaje?", "SI REGISTRAR", "NO", () -> {
            String nombre = binding.txtNombre.getText().toString();
            String descripcion = binding.txtDescripcion.getText().toString();

            // Aquí llamas a tu API o método para registrar el viaje

            ApiService apiService = RetrofitClient.createService();
            Call<EspecialidadRegistrarResponse> call = apiService.registrarEspecialidad(new EspecialidadRegistrarRequest(
                    Login.DATOS_SESION.getUsuarioId(),
                    nombre,
                    descripcion
            ));

            call.enqueue(new Callback<EspecialidadRegistrarResponse>() {
                @Override
                public void onResponse(Call<EspecialidadRegistrarResponse> call, Response<EspecialidadRegistrarResponse> response) {
                    if (response.isSuccessful()){
                        Toast.makeText(requireContext(), "Especialidad registrada correctamente", Toast.LENGTH_SHORT).show();
                        dismiss(); // cerrar el diálogo
                    }else{
                        try {
                            JSONObject jsonError = new JSONObject(response.errorBody().string());
                            String error = jsonError.getString("message");
                            Helper.mensajeError(getContext(), "Error al registrar la especialidad", error);
                        }catch (IOException | JSONException e){
                            throw new RuntimeException(e);
                        }
                    }
                }

                @Override
                public void onFailure(Call<EspecialidadRegistrarResponse> call, Throwable t) {
                    Helper.mensajeError(requireContext(), "Error al acceder al API de registro de especialidades", t.getMessage());
                }
            });



        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
