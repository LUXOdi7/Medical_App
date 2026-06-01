package com.example.medicalapp;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.medicalapp.databinding.FragmentInicioBinding;

public class InicioFragment extends Fragment {
    FragmentInicioBinding binding;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentInicioBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Navegar hacia opción de especialidades
        binding.btnVerEspecialidades.setOnClickListener(v -> {
            NavHostFragment.findNavController(InicioFragment.this).navigate(R.id.nav_especialidades);
        });
        // Navegar hacia opción de médicos
        binding.btnVerMedicos.setOnClickListener(v -> {
            NavHostFragment.findNavController(InicioFragment.this).navigate(R.id.nav_medicos);
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}