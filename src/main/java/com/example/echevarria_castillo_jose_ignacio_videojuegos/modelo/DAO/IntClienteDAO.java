package com.example.echevarria_castillo_jose_ignacio_videojuegos.modelo.DAO;

import com.example.echevarria_castillo_jose_ignacio_videojuegos.modelo.entities.Cliente;

import java.util.List;

public interface IntClienteDAO {

    void guardarCliente(Cliente cliente);
    Cliente obtenerClientePorId(Long id);
    Cliente obtenerClientePorNombreUsuario(String nombreusuario);
    List<Cliente> listarClientes();
    void actualizarCliente(Cliente cliente);
    void eliminarCliente(Long id);

}
