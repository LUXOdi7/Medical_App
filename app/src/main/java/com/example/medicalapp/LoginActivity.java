package com.example.medicalapp;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.medicalapp.data.Login;
import com.example.medicalapp.request.LoginRequest;
import com.example.medicalapp.response.LoginResponse;
import com.example.medicalapp.retrofit.ApiService;
import com.example.medicalapp.retrofit.RetrofitClient;
import com.example.medicalapp.sharedpreferences.LoginStorage;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.checkbox.MaterialCheckBox;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    TextInputEditText txtEmail, txtPassword;
    MaterialButton btnIniciarSesion;
    ProgressBar progressbar;
    LinearLayout loginLayout;
    MaterialCheckBox chkRecordarSesion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_login);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            Insets ime = insets.getInsets(WindowInsetsCompat.Type.ime());
            int bottom = Math.max(systemBars.bottom, ime.bottom);
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, bottom);
            return insets;
        });

        //Enlazar los controles xml con los controles java
        txtEmail = findViewById(R.id.txtEmail);
        txtPassword = findViewById(R.id.txtPassword);
        btnIniciarSesion = findViewById(R.id.btnIniciarSesion);
        progressbar = findViewById(R.id.progressbar);
        loginLayout = findViewById(R.id.loginLayout);
        chkRecordarSesion = findViewById(R.id.chkRecordarSesion);

        btnIniciarSesion.setOnClickListener(v -> {
            iniciarSesion();
        });

        //LLamar al método leerCredenciales
        leerCredenciales();

    }

    private void leerCredenciales(){
        if(LoginStorage.autoLogin(LoginActivity.this)){ //Validar si encontramos credenciales almacenadas
            String email = LoginStorage.getCredentials(LoginActivity.this)[0]; //Email
            String password = LoginStorage.getCredentials(LoginActivity.this)[1]; //Password

            //Setear las credenciales para realizar el inicio de sesión automático
            txtEmail.setText(email);
            txtPassword.setText(password);

            //LLamar al metodo que iniciar la sesión
            iniciarSesion();
        }
    }
    private void iniciarSesion(){
        String email = txtEmail.getText().toString();
        String password = txtPassword.getText().toString();

        //Validar el ingrese de las credenciales
        if (email.isEmpty() || password.isEmpty()){
            mostrarSnackbar("Complete el email y contraseña", android.R.color.holo_orange_light);
            return;
        }

        //Al momento de iniciar la validación de credenciales contra el API REST se muestra un progressbar y se oculta el layout de login
        progressbar.setVisibility(View.VISIBLE);
        loginLayout.setVisibility(View.GONE);

        //Crear una instancia del ApiService para llamar al endpoint
        ApiService apiService = RetrofitClient.createService();

        //Realizar la petición(request) al endpoint
        Call<LoginResponse> call = apiService.login(new LoginRequest(email, password));
        call.enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                if (response.isSuccessful()){ //200->ok
                    String nombre = response.body().getData().getNombre();
                    int usuarioId = response.body().getData().getUsuarioId();

                    Log.e("LOGIN", "NOMBRE: " + nombre);
                    Log.e("LOGIN", "ID: " + usuarioId);

                    //Almacenar el token del usuario
                    RetrofitClient.API_TOKEN=response.body().getData().getToken();

                    //Almacenar los datos de la sesión para que pueda ser consultado posteriormente desde cualquier parte de la app
                    Login.DATOS_SESION = response.body().getData();

                    //Alamacenar las credenciales del usuario, cuando el check "Recordar contraseña"
                    if (chkRecordarSesion.isChecked()){
                        LoginStorage.saveCredentials(LoginActivity.this, email, password, response.body().getData().getRol());
                    }

                    //Llamar al MenuActivity
                    new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            //Navegar al menú principal
                            Intent intent = new Intent(LoginActivity.this, MenuActivity.class);
                            startActivity(intent);

                            //Finalizar el loginActivity
                            finish();
                        }
                    }, 1000);

                }else{ //400, 401, 500, etc.
                    try {
                        //Mostrar el error que ha devuelto el endpoint
                        JSONObject jsonObject = new JSONObject(response.errorBody().string());
                        String message = jsonObject.getString("message");
                        mostrarSnackbar(message, android.R.color.holo_red_light);

                        //Mostrar el loginLayout y ocular el progressbar
                        progressbar.setVisibility(View.GONE);
                        loginLayout.setVisibility(View.VISIBLE);

                    }catch (IOException e){
                        throw new RuntimeException(e);
                    }catch (JSONException e){
                        throw new RuntimeException(e);
                    }
                }
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                mostrarSnackbar("Error al acceder al API REST: " + t.getMessage(), android.R.color.holo_red_light);
                Log.e("LOGIN", "ERROR: " + t.getMessage());
                //Mostrar el loginLayout y ocular el progressbar
                progressbar.setVisibility(View.GONE);
                loginLayout.setVisibility(View.VISIBLE);
            }
        });




        /*
        if (validarCredenciales(email, clave)){
            Toast.makeText(this, "Bienvenido a la aplicación", Toast.LENGTH_SHORT).show();
            //Navegar al menú principal
            Intent intent = new Intent(LoginActivity.this, MenuActivity.class);
            startActivity(intent);

            //Finalizar el loginActivity
            this.finish();
        }else{
            mostrarSnackbar("Credenciales incorrectas", android.R.color.holo_red_light);
        }
         */


    }

    private void mostrarSnackbar(String mensaje, int color){
        Snackbar snackbar = Snackbar.make(findViewById(android.R.id.content), mensaje, Snackbar.LENGTH_LONG);
        //Personalizar el color del snackbar
        View view = snackbar.getView();
        view.setBackgroundTintList(ContextCompat.getColorStateList(getApplicationContext(), color));
        snackbar.show();
    }
}