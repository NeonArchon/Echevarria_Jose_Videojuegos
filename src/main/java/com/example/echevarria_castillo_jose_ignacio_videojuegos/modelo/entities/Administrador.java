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

    @Column(name = "email", nullable = false)
    private String email;

    //constructores
    public Administrador() {
    }

    public Administrador(Long id, String nombreusuario, String contrasena, String email) {
        this.id = id;
        this.nombreusuario = nombreusuario;
        this.contrasena = contrasena;
        this.email = email;
    }

    // Getters y setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
