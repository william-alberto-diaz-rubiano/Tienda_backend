package com.backend.tienda.servicios;

import com.backend.tienda.entidades.Ventas;

import java.util.List;

public interface VentasServicios {
    public Ventas actualizarVentas(Ventas ventas);
    public void eliminarVentas(Long cedula_cliente);
    public Ventas guardarVentas(Ventas ventas);
    public List<Ventas> listarVentas();
    public Ventas buscarVenta(Long cedula_cliente);
    public List<Ventas> buscarXCliente(String cedula_cliente);
}
