package com.example.echevarria_castillo_jose_ignacio_videojuegos.modelo.DAO;

import com.example.echevarria_castillo_jose_ignacio_videojuegos.modelo.entities.Categoria;
import com.example.echevarria_castillo_jose_ignacio_videojuegos.modelo.entities.Juego;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class JuegoDAO implements IntJuegoDAO{

    private final Session session;

    public JuegoDAO(Session session) {
        this.session = session;
    }

    @Override
    public Juego encontrarJuegoID(Long id) {
        return session.find(Juego.class, id);
    }

    @Override
    public List<Juego> buscarJuegos() {
        Query<Juego> query = session.createQuery("FROM Juego", Juego.class);
        return query.getResultList();
    }

    @Override
    public List<Juego> buscarJuegosPorCategoria(Categoria categoria) {
        Query<Juego> query = session.createQuery(
            "FROM Juego WHERE categoria = :categoria", Juego.class);
        query.setParameter("categoria", categoria);
        return query.getResultList();
    }

    @Override
    public List<Juego> buscarJuegosPorTitulo(String titulo) {
        Query<Juego> query = session.createQuery(
            "FROM Juego WHERE titulo LIKE :titulo", Juego.class);
        query.setParameter("titulo", "%" + titulo + "%");
        return query.getResultList();
    }

    @Override
    public void guardarJuego(Juego juego) {
        Transaction tx = session.beginTransaction();
        session.persist(juego);
        tx.commit();
    }

    @Override
    public void actualizarJuego(Juego juego) {
        Transaction tx = session.beginTransaction();
        session.merge(juego);
        tx.commit();
    }

    @Override
    public void borrarJuego(Juego juego) {
        Transaction tx = session.beginTransaction();
        session.remove(juego);
        tx.commit();
    }

    @Override
    public List<Juego> buscarJuego(String keyword) {
        Query<Juego> query = session.createQuery(
            "FROM Juego WHERE LOWER(titulo) LIKE LOWER(:keyword) OR " +
                    "LOWER(descripcion) LIKE LOWER(:keyword)", Juego.class);
        query.setParameter("keyword", "%" + keyword + "%");
        return query.getResultList();
    }
}
