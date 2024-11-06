package com.example.carla_delafuentebernardino_hibernate1n.classes;

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

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

    @OneToMany(mappedBy = "coche", cascade = CascadeType.ALL)
    private List<Multas> multas;

    public Coche() {
    }

    public Coche(int id, String matricula, String marca, String modelo, String tipo, List<Multas> multas) {
        this.id = id;
        this.matricula = matricula;
        this.marca = marca;
        this.modelo = modelo;
        this.tipo = tipo;
        this.multas = multas;
    }

    public Coche(String matricula, String marca, String modelo, String tipo) {
        this.matricula = matricula;
        this.marca = marca;
        this.modelo = modelo;
        this.tipo = tipo;
    }

    public Coche(Coche coche) {
        this.matricula = coche.matricula;
        this.marca = coche.marca;
        this.modelo = coche.modelo;
        this.tipo = coche.tipo;
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

    public List<Multas> getMultas() {
        return multas;
    }

    public void setMultas(List<Multas> multas) {
        this.multas = multas;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Coche coche = (Coche) o;
        return matricula.equals(coche.matricula) && marca.equals(coche.marca) && modelo.equals(coche.modelo) && tipo.equals(coche.tipo);
    }
    @Override
    public String toString() {
        return matricula+" "+marca + " " + modelo+" "+tipo;
    }
}