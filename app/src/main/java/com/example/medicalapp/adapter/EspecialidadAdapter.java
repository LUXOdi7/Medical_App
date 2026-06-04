package com.example.medicalapp.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.medicalapp.data.Especialidad;
import com.example.medicalapp.databinding.ItemEspecialidadBinding;

import java.util.ArrayList;

public class EspecialidadAdapter extends RecyclerView.Adapter<EspecialidadAdapter.ViewHolder>{
    private ArrayList<Especialidad> listaEspecialidad;

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
        }
    }

}
