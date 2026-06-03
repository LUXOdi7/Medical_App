package com.example.medicalapp.data;

import com.example.medicalapp.model.Cita;
import com.example.medicalapp.model.EspecialidadOLD;

import java.util.ArrayList;

import com.example.medicalapp.R;
import com.example.medicalapp.model.Medico;

public class DatosClinica {
    //Lista para gestionar las especialidades médicas
    public static ArrayList<EspecialidadOLD> listaEspecialidades = new ArrayList<>();
    //Lista para gestionar los médicos
    public static ArrayList<Medico> listaMedicos = new ArrayList<>();
    //Lista para gestionar las citas médicas
    public static ArrayList<Cita> listaCita = new ArrayList<>();

    //APURATE PS HIJITO
    public static void cargarDatosInicialesEspecialidad() {
        if (listaEspecialidades.isEmpty()) {
            listaEspecialidades.add(
                    new EspecialidadOLD(
                            "Pediatria",
                            "Atención médica especializada para niños y adolescentes",
                            R.drawable.pediatria)
            );
            listaEspecialidades.add(
                    new EspecialidadOLD(
                            "Cardiologia",
                            "Diagnóstico y trantamiento de enfermedades del corazón",
                            R.drawable.cardiologia)
            );
            listaEspecialidades.add(
                    new EspecialidadOLD(
                            "Odontologia",
                            "Atención integral en tratamientos y enfermedades bucales",
                            R.drawable.odontologia)
            );
        }
    }

    public static void cargarDatosInicialesMedicos(){
        listaMedicos.add(new Medico(
                "Dra. María López",
                "Pediatria",
                "Lunes a Viernes - 8:00 a.m. a 1:00 p.m.",
                "101",
                R.drawable.account_circle_24
        ));

        listaMedicos.add(new Medico(
                "Dr. José Rivera",
                "Pediatria",
                "Lunes, Miércoles y Viernes - 2:00 p.m. a 6:00 p.m.",
                "102",
                R.drawable.account_circle_24
        ));

        listaMedicos.add(new Medico(
                "Dr. Carlos Ramírez",
                "Cardiologia",
                "Lunes, Miércoles y Viernes - 3:00 p.m. a 7:00 p.m.",
                "202",
                R.drawable.account_circle_24
        ));

        listaMedicos.add(new Medico(
                "Dra. Andrea Castillo",
                "Odontologia",
                "Martes y Jueves - 9:00 a.m. a 2:00 p.m.",
                "203",
                R.drawable.account_circle_24
        ));
    }

    public static ArrayList<Medico> obtenerListaMedicosPorEspecialidad(String especialidad){
        ArrayList<Medico> lista = new ArrayList<>();
        for (Medico m: listaMedicos){
            if (m.getEspecialidad().equals(especialidad)){
                lista.add(m);
            }
        }
        return lista;
    }
}
