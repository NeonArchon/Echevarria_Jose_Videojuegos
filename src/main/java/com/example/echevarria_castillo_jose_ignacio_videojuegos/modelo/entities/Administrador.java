package com.example.echevarria_castillo_jose_ignacio_videojuegos.modelo.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "administradores")
public class Administrador {

    //atributios
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nombreusuario", nullable = false, unique = true)
    private String nombreusuario;

    @Column(name = "contrasena", nullable = false)
    private String contrasena;

    //cinstructores
    public Administrador() {
    }

    public Administrador(String nombreusuario, String contrasena) {
        this.nombreusuario = nombreusuario;
        this.contrasena = contrasena;
    }

    // Getters y setters
    public Long getId() {
        return id;
    }

    public String getNombreusuario() {
        return nombreusuario;
    }

    public void setNombreusuario(String nombreusuario) {
        this.nombreusuario = nombreusuario;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }


}
