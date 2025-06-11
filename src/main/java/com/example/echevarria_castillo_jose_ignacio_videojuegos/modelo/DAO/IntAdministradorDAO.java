package com.example.echevarria_castillo_jose_ignacio_videojuegos.modelo.DAO;

import com.example.echevarria_castillo_jose_ignacio_videojuegos.modelo.entities.Administrador;

import java.util.List;

public interface IntAdministradorDAO {

    void guardarAdministrador(Administrador administrador);

    Administrador obtenerAdministradorPorId(Long id);

    Administrador obtenerAdministradorPorNombreUsuario(String nombreusuario);

    List<Administrador> listarAdministradores();

    void actualizarAdministrador(Administrador administrador);

    void eliminarAdministrador(Long id);

    Administrador loginAdministrador(String nombreusuario, String contrasena);

}
