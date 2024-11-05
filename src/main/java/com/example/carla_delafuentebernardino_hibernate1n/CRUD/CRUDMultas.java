package com.example.carla_delafuentebernardino_hibernate1n.CRUD;

import com.example.carla_delafuentebernardino_hibernate1n.classes.Coche;
import com.example.carla_delafuentebernardino_hibernate1n.classes.Multas;

import java.util.List;

public interface CRUDMultas {
    void insertarMulta(Multas multa);

    void actualizarMulta(Multas multa);

    void eliminarMultaID(int id);

    List<Multas> obtenerMultasCoche(Coche coche);
}
