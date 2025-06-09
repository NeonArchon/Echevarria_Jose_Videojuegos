package com.example.echevarria_castillo_jose_ignacio_videojuegos.modelo.DAO;

import com.example.echevarria_castillo_jose_ignacio_videojuegos.modelo.entities.Cliente;
import com.example.echevarria_castillo_jose_ignacio_videojuegos.modelo.entities.Compra;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class CompraDAO implements IntCompraDAO{

    private final Session session;

    public CompraDAO(Session session) {
        this.session = session;
    }

    @Override
    public void guardarComra(Compra compra) {
        Transaction tx = session.beginTransaction();
        session.persist(compra);
        tx.commit();
    }

    @Override
    public List<Compra> buscarCompraCliente(Cliente cliente) {
        Query<Compra> query = session.createQuery(
                "FROM Compra WHERE cliente = :cliente ORDER BY fechaCompra DESC",
                Compra.class);
        query.setParameter("cliente", cliente);
        return query.getResultList();
    }

    @Override
    public List<Compra> buscarTodasCompras() {
        Query<Compra> query = session.createQuery(
                "FROM Compra ORDER BY fechaCompra DESC", Compra.class);
        return query.getResultList();
    }

    @Override
    public void borrarCompra(Compra compra) {
        Transaction tx = session.beginTransaction();
        session.remove(compra);
        tx.commit();
    }
}
