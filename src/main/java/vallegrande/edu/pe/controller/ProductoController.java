package vallegrande.edu.pe.controller;

import vallegrande.edu.pe.model.Producto;
import vallegrande.edu.pe.model.ProductoDAO;

import java.util.ArrayList;

public class ProductoController {

    private ProductoDAO dao;

    public ProductoController() {
        dao = new ProductoDAO();
    }

    // INSERTAR
    public boolean insertar(Producto producto) {
        return dao.insertar(producto);
    }

    // LISTAR
    public ArrayList<Producto> listar() {
        return dao.listar();
    }

    // ACTUALIZAR
    public boolean actualizar(Producto producto) {
        return dao.actualizar(producto);
    }

    // ELIMINAR
    public boolean eliminar(int id) {
        return dao.eliminar(id);
    }
}