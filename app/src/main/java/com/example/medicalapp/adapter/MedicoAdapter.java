package com.example.medicalapp.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.medicalapp.databinding.ItemMedicoBinding;
import com.example.medicalapp.model.Medico;

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
            binding.tvNombreMedico.setText(medico.getNombre());
            binding.tvEspecialidadMedico.setText("Especialidad: " + medico.getEspecialidad());
            binding.tvHorarioMedico.setText("Horario: " + medico.getHorario());
            binding.tvConsultorioMedico.setText("Consultorio: " + medico.getConsultorio());
            binding.imgMedico.setImageResource(medico.getImagen());
        }

    }
}