package com.example.carla_delafuentebernardino_hibernate1n.util;

public class Validar {
    public static boolean validarMatricula(String matricula) {
        return matricula.matches("^[0-9]{4}[A-Z]{3}$");
    }
}
