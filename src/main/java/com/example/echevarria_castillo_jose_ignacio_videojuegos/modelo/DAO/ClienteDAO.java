package com.example.echevarria_castillo_jose_ignacio_videojuegos.modelo.DAO;

import com.example.echevarria_castillo_jose_ignacio_videojuegos.Util.HibernateUtil;
import com.example.echevarria_castillo_jose_ignacio_videojuegos.modelo.entities.Cliente;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class ClienteDAO implements IntClienteDAO {

    @Override
    public void guardarCliente(Cliente cliente) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();
        session.persist(cliente);
        tx.commit();
        session.close();
    }

    @Override
    public Cliente obtenerClientePorId(Long id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Cliente cliente = session.get(Cliente.class, id);
        session.close();
        return cliente;
    }

    @Override
    public Cliente obtenerClientePorNombreUsuario(String nombreusuario) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Query<Cliente> query = session.createQuery("FROM Cliente WHERE nombreusuario = :nombre", Cliente.class);
        query.setParameter("nombre", nombreusuario);
        Cliente cliente = query.uniqueResult();
        session.close();
        return cliente;
    }

    @Override
    public List<Cliente> listarClientes() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        List<Cliente> clientes = session.createQuery("FROM Cliente", Cliente.class).list();
        session.close();
        return clientes;
    }

    @Override
    public void actualizarCliente(Cliente cliente) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();
        session.merge(cliente);
        tx.commit();
        session.close();
    }

    @Override
    public void eliminarCliente(Long id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();
        Cliente cliente = session.get(Cliente.class, id);
        if (cliente != null) {
            session.remove(cliente);
        }
        tx.commit();
        session.close();
    }

    @Override
    public Cliente loginCliente(String nombreusuario, String contrasena) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Query<Cliente> query = session.createQuery("FROM Cliente WHERE nombreusuario = :nombre AND contrasena = :clave", Cliente.class);
        query.setParameter("nombre", nombreusuario);
        query.setParameter("clave", contrasena);
        Cliente cliente = query.uniqueResult();
        session.close();
        return cliente;
    }
}
