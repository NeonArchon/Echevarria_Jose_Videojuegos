package com.example.echevarria_castillo_jose_ignacio_videojuegos.modelo.entities;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "compras")
public class Compra {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;

    @ManyToOne
    @JoinColumn(name = "juego_id")
    private Juego juego;

    @Column(name = "fecha_compra")
    private LocalDateTime fechaCompra;

    //constructores

    public Compra() {
    }

    public Compra(Long id, Cliente cliente, Juego juego, LocalDateTime fechaCompra) {
        this.id = id;
        this.cliente = cliente;
        this.juego = juego;
        this.fechaCompra = fechaCompra;
    }

    //gegtters y setetrs


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Juego getJuego() {
        return juego;
    }

    public void setJuego(Juego juego) {
        this.juego = juego;
    }

    public LocalDateTime getFechaCompra() {
        return fechaCompra;
    }

    public void setFechaCompra(LocalDateTime fechaCompra) {
        this.fechaCompra = fechaCompra;
    }
}
