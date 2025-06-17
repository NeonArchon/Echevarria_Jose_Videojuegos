package com.example.echevarria_castillo_jose_ignacio_videojuegos.modelo.entities;


import jakarta.persistence.Entity;
import jakarta.persistence.*;

@Entity
@Table(name = "categorias")
public class Categoria {

    //atrubutos
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String nombre;

    //contructores




    public Categoria(String nombre) {
        this.nombre = nombre;
    }



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
