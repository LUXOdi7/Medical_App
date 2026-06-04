package com.example.medicalapp.adapter;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.model.GlideUrl;
import com.bumptech.glide.load.model.LazyHeaders;
import com.example.medicalapp.data.Especialidad;
import com.example.medicalapp.databinding.ItemEspecialidadBinding;
import com.example.medicalapp.retrofit.RetrofitClient;

import java.util.ArrayList;

public class EspecialidadAdapter extends RecyclerView.Adapter<EspecialidadAdapter.ViewHolder>{
    private ArrayList<Especialidad> listaEspecialidad = new ArrayList<>();

    public EspecialidadAdapter(ArrayList<Especialidad> listaEspecialidad){
        this.listaEspecialidad = listaEspecialidad;
    }

    public void actualizarListaEspecialidad(ArrayList<Especialidad> listaEspecialidad){
        this.listaEspecialidad = listaEspecialidad;
        //Con la finalidad de que el RecyclerView se refresque, aplicamos notifyDataSetChanged()
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //Permite vincular el adaptador con el archivo que contiene en la plantilla MaterialCardView
        ItemEspecialidadBinding binding = ItemEspecialidadBinding.inflate
                (
                        LayoutInflater.from(parent.getContext()),
                        parent,
                        false
                );
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        //Permite gestionar la impresión de los datos en la plantilla
        Especialidad especialidad = listaEspecialidad.get(position);
        holder.mostrarDatos(especialidad);
    }

    @Override
    public int getItemCount() {
        //Permite gestionar la cantidad de items a mostrar en el listado
        return listaEspecialidad.size();
    }

    public static  class ViewHolder extends RecyclerView.ViewHolder {
        //Permite vincular la clase viewHolder del Adaptador con los controles de la plantilla MaterialCardView
        ItemEspecialidadBinding binding;

        public ViewHolder(ItemEspecialidadBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void mostrarDatos(Especialidad especialidad) {
            //binding.imgEspecialidad.setImageResource(especialidad.getImagen());
            binding.txtEspecialidad.setText(especialidad.getNombre());
            binding.txtDescripcionEspecialidad.setText(especialidad.getDescripcion());

            Log.e("ESP IMAGE", especialidad.getImagenUrl());

            GlideUrl glideUrl = new GlideUrl(
                    RetrofitClient.URL_API_SERVICE + especialidad.getImagenUrl(),
                    new LazyHeaders.Builder()
                            .addHeader("Authorization", "Bearer " + RetrofitClient.API_TOKEN)
                            .build()
            );
            Glide.with(binding.getRoot().getContext())
                    .load(glideUrl)
                    .into(binding.imgEspecialidad);
        }
    }

}
