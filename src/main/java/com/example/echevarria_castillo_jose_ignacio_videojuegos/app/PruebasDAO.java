package com.example.echevarria_castillo_jose_ignacio_videojuegos.app;

import com.example.echevarria_castillo_jose_ignacio_videojuegos.Util.HibernateUtil;
import com.example.echevarria_castillo_jose_ignacio_videojuegos.modelo.DAO.*;
import com.example.echevarria_castillo_jose_ignacio_videojuegos.modelo.entities.*;
import org.hibernate.Session;
import java.time.LocalDateTime;
import java.util.List;

public class PruebasDAO {

    public static void main(String[] args) {

        Session session = HibernateUtil.getSessionFactory().openSession();

        CategoriaDAO categoriaDAO = new CategoriaDAO(session);
        JuegoDAO juegoDAO = new JuegoDAO(session);
        CompraDAO compraDAO = new CompraDAO(session);

        // Crear una categoría
        Categoria categoria = new Categoria();
        categoria.setNombre("Aventura");
        categoriaDAO.guardarcategoria(categoria);

        // Crear un juego
        Juego juego = new Juego();
        juego.setTitulo("Zelda");
        juego.setDescripcion("Aventura épica");
        juego.setPrecio(59.99);
        juego.setCategoria(categoria);
        juego.setRutaImagen("ruta/img.jpg");
        juego.setRutaTrailer("ruta/trailer.mp4");
        juegoDAO.guardarJuego(juego);

        // Crear cliente
        // Crear cliente
        Cliente cliente = new Cliente();
        cliente.setNombreusuario("usuario1");
        cliente.setContrasena("1234");

        // Usar ClienteDAO en lugar de UsuarioDAO
        IntClienteDAO clienteDAO = new ClienteDAO();
        clienteDAO.guardarCliente(cliente);

        // Crear compra
        Compra compra = new Compra();
        compra.setCliente(cliente);
        compra.setJuego(juego);
        compra.setFechaCompra(LocalDateTime.now());
        compraDAO.guardarComra(compra);

        // Buscar compras del cliente
        List<Compra> compras = compraDAO.buscarCompraCliente(cliente);
        System.out.println("Compras del cliente:");
        for (Compra c : compras) {
            System.out.println("Juego: " + c.getJuego().getTitulo() + " en " + c.getFechaCompra());
        }

        session.close();
        HibernateUtil.getSessionFactory().close();
    }

    }
