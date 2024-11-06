package com.example.carla_delafuentebernardino_hibernate1n.CRUD;

import com.example.carla_delafuentebernardino_hibernate1n.classes.Coche;

import java.util.List;

public interface CRUDCoche {
    boolean insertarCoche(Coche coche);

    boolean actualizarCoche(Coche coche);

    boolean eliminarCoche(Coche coche);

    List<Coche> obtenerCoches();

    boolean existeCoche(String matricula);
}