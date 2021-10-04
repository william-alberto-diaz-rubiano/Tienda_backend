package com.backend.tienda.servicios;

import com.backend.tienda.entidades.Productos;

import java.util.List;

public interface ProductosServicios {
    public Productos actualizarProductos(Productos productos);
    public void eliminarProductos(Long codigo_producto);
    public Productos guardarProductos(Productos productos);
    public List<Productos> listarProductos();
    public Productos buscarProducto(Long codigo_producto);

}
