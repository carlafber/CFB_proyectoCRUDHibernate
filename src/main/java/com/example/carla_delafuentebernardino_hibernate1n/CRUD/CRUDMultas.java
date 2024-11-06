package com.example.carla_delafuentebernardino_hibernate1n.CRUD;

import com.example.carla_delafuentebernardino_hibernate1n.classes.Coche;
import com.example.carla_delafuentebernardino_hibernate1n.classes.Multas;

import java.util.List;

public interface CRUDMultas {
    boolean insertarMulta(Multas multa);

    boolean actualizarMulta(Multas multa);

    boolean eliminarMulta(Multas multa);

    List<Multas> obtenerMultasCoche(Coche coche);
}
