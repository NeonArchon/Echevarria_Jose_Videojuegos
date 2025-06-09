package com.example.echevarria_castillo_jose_ignacio_videojuegos.modelo.entities;


import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "clientes")
public class Cliente{

    //atributos
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nombreusuario", nullable = false, unique = true)
    private String nombreusuario;

    @Column(name = "contrasena", nullable = false)
    private String contrasena;

    @OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL)
    private List<Compra> compras = new ArrayList<>();

    //constructores
    public Cliente() {
    }

    public Cliente(String nombreusuario, String contrasena) {
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

    public List<Compra> getCompras() {
        return compras;
    }

    public void setCompras(List<Compra> compras) {
        this.compras = compras;
    }
}
