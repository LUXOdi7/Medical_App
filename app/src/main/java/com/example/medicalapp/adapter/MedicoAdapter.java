package com.example.medicalapp.adapter;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.model.GlideUrl;
import com.bumptech.glide.load.model.LazyHeaders;
import com.example.medicalapp.data.Medico;
import com.example.medicalapp.databinding.ItemMedicoBinding;
import com.example.medicalapp.retrofit.RetrofitClient;

import java.util.ArrayList;

public class MedicoAdapter extends RecyclerView.Adapter<MedicoAdapter.MedicoViewHolder> {

    private ArrayList<Medico> listaMedicos;

    public MedicoAdapter(ArrayList<Medico> listaMedicos) {
        this.listaMedicos = listaMedicos;
    }

    @NonNull
    @Override
    public MedicoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemMedicoBinding binding = ItemMedicoBinding.inflate(
                LayoutInflater.from(parent.getContext()),
                parent,
                false
        );
        return new MedicoViewHolder(binding);
    }

    public void actualizarLista(ArrayList<Medico> nuevaLista) {
        this.listaMedicos = nuevaLista;
        notifyDataSetChanged();
    }

    @Override
    public void onBindViewHolder(@NonNull MedicoViewHolder holder, int position) {
        Medico medico = listaMedicos.get(position);
        holder.mostrarDatos(medico);
    }

    @Override
    public int getItemCount() {
        return listaMedicos.size();
    }

    public static class MedicoViewHolder extends RecyclerView.ViewHolder {
        //Enlazar los controles del cardview mediante binding
        ItemMedicoBinding binding;

        public MedicoViewHolder(ItemMedicoBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void mostrarDatos(Medico medico){
            binding.tvNombreMedico.setText(medico.getNombres());
            binding.tvApellidosMedico.setText(medico.getApellidos());
            binding.tvDniMedico.setText("DNI: " + medico.getDni());
            binding.tvCmpMedico.setText("CMP: " + medico.getCmp());
            binding.tvTelefonoMedico.setText("Teléfono: " + medico.getTelefono());
            binding.tvEstadoMedico.setText("Estado: " + medico.getEstadoMedicoId());

            Log.e("ESP IMAGE", medico.getImagenUrl());

            GlideUrl glideUrl = new GlideUrl(
                    RetrofitClient.URL_API_SERVICE + medico.getImagenUrl(),
                    new LazyHeaders.Builder()
                            .addHeader("Authorization", "Bearer " + RetrofitClient.API_TOKEN)
                            .build()
            );
            Glide.with(binding.getRoot().getContext())
                    .load(glideUrl)
                    .into(binding.imgMedico);


        }

    }
}