package com.example.carla_delafuentebernardino_hibernate1n.classes;

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.*;
import java.io.Serializable;


@Entity
@Table(name = "Coche")
public class Coche implements Serializable {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name="id")
    private int id;

    @Column(name="matricula")
    private String matricula;

    @Column(name="marca")
    private String marca;

    @Column(name="modelo")
    private String modelo;

    @Column(name="tipo")
    private String tipo;

    /*@OneToMany(mappedBy = "coche", cascade = CascadeType.ALL)
    private List<Multas> multas;*/

    public Coche() {
    }

    public Coche(int id, String matricula, String marca, String modelo, String tipo) {
        this.id = id;
        this.matricula = matricula;
        this.marca = marca;
        this.modelo = modelo;
        this.tipo = tipo;
    }

    public Coche(String matricula, String marca, String modelo, String tipo) {
        this.matricula = matricula;
        this.marca = marca;
        this.modelo = modelo;
        this.tipo = tipo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    /*public List<Multas> getMultas() {
        return multas;
    }

    public void setMultas(List<Multas> multas) {
        this.multas = multas;
    }*/

    @Override
    public String toString() {
        return matricula+" "+marca + " " + modelo+" "+tipo;
    }
}