package com.example.echevarria_castillo_jose_ignacio_videojuegos.modelo.DAO;

import com.example.echevarria_castillo_jose_ignacio_videojuegos.modelo.entities.Categoria;
import com.example.echevarria_castillo_jose_ignacio_videojuegos.modelo.entities.Juego;

import java.util.List;

public interface IntJuegoDAO {

    Juego encontrarJuegoID (Long id);
    List<Juego> buscarJuegos();
    List<Juego> buscarJuegosPorCategoria(Categoria categoria);
    List<Juego> buscarJuegosPorTitulo(String titulo);
    void guardarJuego(Juego juego);
    void actualizarJuego(Juego juego);
    void borrarJuego(Juego juego);
    // Para búsqueda de juegos
    List<Juego> buscarJuego(String keyword);

}
