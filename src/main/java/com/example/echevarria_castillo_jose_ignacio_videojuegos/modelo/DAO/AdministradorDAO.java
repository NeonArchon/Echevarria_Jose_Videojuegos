package com.example.echevarria_castillo_jose_ignacio_videojuegos.modelo.DAO;

import com.example.echevarria_castillo_jose_ignacio_videojuegos.Util.HibernateUtil;
import com.example.echevarria_castillo_jose_ignacio_videojuegos.modelo.entities.Administrador;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class AdministradorDAO implements IntAdministradorDAO{


    @Override
    public void guardarAdministrador(Administrador administrador) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();
        session.persist(administrador);
        tx.commit();
        session.close();
    }

    @Override
    public Administrador obtenerAdministradorPorId(Long id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Administrador admin = session.get(Administrador.class, id);
        session.close();
        return admin;
    }

    @Override
    public Administrador obtenerAdministradorPorNombreUsuario(String nombreusuario) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Query<Administrador> query = session.createQuery("FROM Administrador WHERE nombreusuario = :nombre", Administrador.class);
        query.setParameter("nombre", nombreusuario);
        Administrador admin = query.uniqueResult();
        session.close();
        return admin;
    }

    @Override
    public List<Administrador> listarAdministradores() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        List<Administrador> admins = session.createQuery("FROM Administrador", Administrador.class).list();
        session.close();
        return admins;
    }

    @Override
    public void actualizarAdministrador(Administrador administrador) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();
        session.merge(administrador);
        tx.commit();
        session.close();
    }

    @Override
    public void eliminarAdministrador(Long id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();
        Administrador admin = session.get(Administrador.class, id);
        if (admin != null) {
            session.remove(admin);
        }
        tx.commit();
        session.close();
    }

}
