package com.example.echevarria_castillo_jose_ignacio_videojuegos.modelo.DAO;

import com.example.echevarria_castillo_jose_ignacio_videojuegos.modelo.entities.Cliente;
import com.example.echevarria_castillo_jose_ignacio_videojuegos.modelo.entities.Compra;

import java.util.List;

public interface IntCompraDAO {

    void guardarComra(Compra compra);
    List<Compra> buscarCompraCliente(Cliente cliente);
    List<Compra> buscarTodasCompras();
    void borrarCompra(Compra compra);

}
