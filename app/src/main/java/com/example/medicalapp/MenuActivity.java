package com.example.medicalapp;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.GravityCompat;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.medicalapp.data.Login;
import com.example.medicalapp.databinding.ActivityMenuBinding;
import com.example.medicalapp.retrofit.ApiService;
import com.example.medicalapp.retrofit.RetrofitClient;
import com.example.medicalapp.sharedpreferences.LoginStorage;
import com.example.medicalapp.util.Helper;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.navigation.NavigationView;

import java.io.InputStream;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MenuActivity extends AppCompatActivity {

    //Configuración de la barra superior
    private AppBarConfiguration mAppBarConfiguration;

    //Referencia al binding para enlazar a los controles
    private ActivityMenuBinding binding;

    //Declarar los controles de la cabecera del menú
    MaterialButton btnCerrarSesion;
    de.hdodenhof.circleimageview.CircleImageView profileImage;
    com.google.android.material.textview.MaterialTextView txtNombreUsuario, txtEmailUsuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);

        //Inicializar el binding
        binding = ActivityMenuBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        //Configurar el toolBar
        setSupportActionBar(binding.appBarMenu.toolbar);

        //Referenciar al contenedor del menú y a la vista de navegación
        DrawerLayout drawer = binding.drawerLayout;
        NavigationView navigationView = binding.navView;

        //Configurar la barra de navegación
        mAppBarConfiguration = new AppBarConfiguration.Builder
                (
                        R.id.nav_inicio,
                        R.id.nav_especialidades,
                        R.id.nav_medicos,
                        R.id.nav_registrar_cita,
                        R.id.nav_reprogramar_cita,
                        R.id.nav_calificar_atencion,
                        R.id.nav_acerca_de
                ).setOpenableLayout(drawer).build();

        //Gestionar el intercambio de items del menú: Al cambiar de item, cambia de fragment
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_menu);

        //Vincular el toolbar con el NavController y la configuración del drawer
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);

        //Vincular el menú lateral (navigationView) con el NavControler para que al hacer clic en el item quede seleccionado
        NavigationUI.setupWithNavController(navigationView, navController);

        //Personalizar la cabecera del menú
        View view = navigationView.getHeaderView(0); //Acceder a los controles de la cabecera del menú
        btnCerrarSesion= view.findViewById(R.id.btnCerrarSesion);
        profileImage = view.findViewById(R.id.profile_image);
        txtNombreUsuario = view.findViewById(R.id.txtNombreUsuario);
        txtEmailUsuario = view.findViewById(R.id.txtEmailUsuario);

        //Imprimir los datos del usuario que ha iniciado sesión
        imprimirDatosUsuario();

        //Mostrar la foto del usuario
        mostrarFotoUsuario();

        btnCerrarSesion.setOnClickListener(v -> {
            cerrarSesion();
        });

        //Gestionar la navegación hacia el menú inicio, cuando se navega hacia otras opciones desde algún botón ubicado en un fragment
        navigationView.setNavigationItemSelectedListener(menuItem -> {
            boolean handled = NavigationUI.onNavDestinationSelected(menuItem, navController);
            if (handled) drawer.closeDrawers();

            if (menuItem.getItemId() == R.id.nav_inicio){
                navController.popBackStack(R.id.nav_inicio, false); //Navega hacia el menú inicio
                drawer.closeDrawer(GravityCompat.START);
                return true;
            }
            return handled;
        });

    }
    private void mostrarFotoUsuario(){
        ApiService apiService = RetrofitClient.createService();
        Call<ResponseBody> call = apiService.obtenerFotoUsuario(Login.DATOS_SESION.getUsuarioId());
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful() && response.body() != null) {
                    //Convertir la respuesta del endpoint en un archivo de formato de imagen, para que sea reconocido en android
                    InputStream inputStream = response.body().byteStream();
                    Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                    profileImage.setImageBitmap(bitmap);
                }else{
                    Helper.mensajeError(MenuActivity.this, "Error", "No se ha podido descargar la foto del usuario");
                }
            }
            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                //Manejar el error
                Helper.mensajeError(MenuActivity.this, "Error", "Error al acceder al servicio web para descargar la foto");
            }
        });
    }
    private void imprimirDatosUsuario() {
        txtNombreUsuario.setText(Login.DATOS_SESION.getNombre());
        txtEmailUsuario.setText(Login.DATOS_SESION.getEmail());
    }

    private void cerrarSesion() {
        Helper.mensajeConfirmacion(
                MenuActivity.this,
                "Confirme",
                "Desea cerrar la sesión del usuario",
                "SI",
                "NO",
                new Runnable() {
                    @Override
                    public void run() {
                        LoginStorage.clearCredentials(MenuActivity.this);
                        MenuActivity.this.finish();
                    }
                });

    }

    @Override
    public boolean onSupportNavigateUp() {
        //Este evento se ejecuta cuando el usuario hace click en el menú de la esquina superior izquierda
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_menu);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration) || super.onSupportNavigateUp();
    }
}