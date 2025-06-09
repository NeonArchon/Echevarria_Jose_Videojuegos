package com.example.echevarria_castillo_jose_ignacio_videojuegos.modelo.DAO;

import com.example.echevarria_castillo_jose_ignacio_videojuegos.modelo.entities.Categoria;

import java.util.List;

public interface IntCategoriaDAO {

    List<Categoria> buscarCategorias();
    void guardarcategoria(Categoria categoria);
    void borrarcategoria(Categoria categoria);

}
