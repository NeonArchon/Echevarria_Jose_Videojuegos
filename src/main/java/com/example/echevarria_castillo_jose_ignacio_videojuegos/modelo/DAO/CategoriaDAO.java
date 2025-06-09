package com.example.echevarria_castillo_jose_ignacio_videojuegos.modelo.DAO;

import com.example.echevarria_castillo_jose_ignacio_videojuegos.modelo.entities.Categoria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class CategoriaDAO implements IntCategoriaDAO{

    private final Session session;

    public CategoriaDAO(Session session) {

        this.session = session;
    }

    @Override
    public List<Categoria> buscarCategorias() {

        Query<Categoria> query = session.createQuery(
                "FROM Categoria", Categoria.class);
        return query.getResultList();
    }

    @Override
    public void guardarcategoria(Categoria categoria) {

        Transaction tx = session.beginTransaction();
        session.persist(categoria);
        tx.commit();
    }

    @Override
    public void borrarcategoria(Categoria categoria) {

        Transaction tx = session.beginTransaction();
        session.remove(categoria);
        tx.commit();
    }
}
