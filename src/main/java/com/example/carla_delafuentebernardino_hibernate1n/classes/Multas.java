package com.example.carla_delafuentebernardino_hibernate1n.classes;

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.*;
import java.time.*;
import java.util.Objects;


@Entity
@Table(name = "Multas")
public class Multas {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name="id_multa")
    private int id_multa;

    @Column(name="precio")
    private double precio;

    @Column(name="fecha")
    private LocalDate fecha;

    @ManyToOne
    @JoinColumn(name = "matricula", referencedColumnName = "matricula")
    private Coche coche;

    public Multas() {
    }

    public Multas(int id_multa, double precio, LocalDate fecha, Coche coche) {
        this.id_multa = id_multa;
        this.precio = precio;
        this.fecha = fecha;
        this.coche = coche;
    }

    public Multas(double precio, LocalDate fecha) {
        this.precio = precio;
        this.fecha = fecha;
    }

    public Multas(Multas multas) {
        this.precio = multas.precio;
        this.fecha = multas.fecha;
        this.coche = multas.coche;
    }


    public int getId_multa() {
        return id_multa;
    }

    public void setId_multa(int id_multa) {
        this.id_multa = id_multa;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public Coche getCoche() {
        return coche;
    }

    public void setCoche(Coche coche) {
        this.coche = coche;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Multas multas = (Multas) o;
        return Double.compare(precio, multas.precio) == 0 && fecha.equals(multas.fecha) && coche.equals(multas.coche);
    }

    @Override
    public String toString() {
        return "Multas{" +
                "id_multa=" + id_multa +
                ", precio=" + precio +
                ", fecha=" + fecha +
                ", coche=" + coche +
                '}';
    }
}
