package com.example.medicalapp.retrofit;


import com.example.medicalapp.request.EspecialidadRegistrarRequest;
import com.example.medicalapp.request.LoginRequest;
import com.example.medicalapp.request.MedicoRegistrarRequest;
import com.example.medicalapp.response.EspecialidadListadoResponse;
import com.example.medicalapp.response.EspecialidadRegistrarResponse;
import com.example.medicalapp.response.LoginResponse;
import com.example.medicalapp.response.MedicoPorEspecialidadListadoResponse;
import com.example.medicalapp.response.MedicoRegistrarResponse;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface ApiService {
    //Aqui se implementa las llamadas a los endpoints

    @POST("login")
    Call<LoginResponse> login(@Body LoginRequest request);

    @GET("usuarios/foto/{id}")
    Call<ResponseBody> obtenerFotoUsuario(@Path("id") int id);
    @GET("especialidades")
    Call<EspecialidadListadoResponse> getEspecialidades();
    @POST("especialidades")
    Call<EspecialidadRegistrarResponse> registrarEspecialidad(@Body EspecialidadRegistrarRequest request);

    @GET("medicos/especialidad/{id}")
    Call<MedicoPorEspecialidadListadoResponse> getMedicosPorEspecialidad(@Path("id") int id);

    @POST("medicos")
    Call<MedicoRegistrarResponse> registrarMedico(@Body MedicoRegistrarRequest request);

//    @GET("agricultor/foto/{id}")
//    Call<ResponseBody> getFotoAgricultor(@Path("id") int id);
//
//    @GET("parcela/listar/{id}")
//    Call<ParcelaResponse> getParcelas(@Path("id") int id);
//
//    @GET("cultivo/listar/{id}")
//    Call<CultivoResponse> getCultivos(@Path("id") int id);
//
//    @GET("cultivo/datos/{id}")
//    Call<CultivoDatosResponse> getDatosCultivo(@Path("id") int id);
//
//    @GET("actividad/listar/{id}")
//    Call<ActividadListadoResponse> getActividadListado(@Path("id") int id);
//
//    @GET("actividad/tipo/listar")
//    Call<ActividadTipoListarResponse> getTipoActividadListar();
//
//    @POST("actividad/registrar")
//    Call<ActividadRegistrarResponse> actividadRegistrar(@Body ActividadRegistrarRequest request);
//
//    @PUT("agricultor/registrar/token")
//    Call<FcmTokenRegistrarResponse> FcmTokenRegistrar(@Body FcmTokenRegistrarRequest request);

}
