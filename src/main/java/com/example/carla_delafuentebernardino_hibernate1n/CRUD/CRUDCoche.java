package com.example.carla_delafuentebernardino_hibernate1n.CRUD;

import com.example.carla_delafuentebernardino_hibernate1n.classes.Coche;

import java.util.List;

public interface CRUDCoche {
    void insertarCoche(Coche coche);

    void actualizarCoche(Coche coche);

    void eliminarCocheMatricula(String matricula);

    List<Coche> obtenerCoches();

    Coche obtenerCocheMatricula(String matricula);
}