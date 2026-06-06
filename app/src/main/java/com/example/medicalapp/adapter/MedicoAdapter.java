package com.example.medicalapp.adapter;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
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
    private String rolUsuario;
    public MedicoAdapter(ArrayList<Medico> listaMedicos, String rolUsuario) {
        this.listaMedicos = listaMedicos;
        this.rolUsuario = rolUsuario;
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
        holder.mostrarDatos(medico, rolUsuario);
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

        public void mostrarDatos(Medico medico, String rolU){
            binding.tvNombreMedico.setText(medico.getmedico());
            binding.tvEspecialidadMedico.setText("Especialidad: " + medico.getEspecialidadNombre());
            binding.tvConsultorioMedico.setText("Consultorio: " + medico.getConsultorio());
            if(!"ADMINISTRATIVO".equals(rolU)) {
                binding.tvDNIMedico.setVisibility(View.GONE);
                binding.tvTelefonoMedico.setVisibility(View.GONE);
                binding.tvCMPMedico.setVisibility(View.GONE);

            }else {
                binding.tvTelefonoMedico.setVisibility(View.VISIBLE);
                binding.tvTelefonoMedico.setText("Telefono: " + medico.getTelefono());
                binding.tvDNIMedico.setVisibility(View.VISIBLE);
                binding.tvDNIMedico.setText("DNI :" + medico.getDni());
                binding.tvCMPMedico.setVisibility(View.VISIBLE);
                binding.tvCMPMedico.setText("CMP: " + medico.getCmp());
            }

        }

    }
}