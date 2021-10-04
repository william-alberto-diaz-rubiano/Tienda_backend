package com.backend.tienda.controladores;

import com.backend.tienda.entidades.Clientes;
import com.backend.tienda.entidades.Productos;
import com.backend.tienda.servicios.ClientesServicios;
import com.backend.tienda.servicios.ProductosServicios;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins ="http://127.0.0.1:5500")
@RestController
@RequestMapping("/productos")
public class ProductosControlador {

        @Autowired
        private ProductosServicios productosServicios;

        @GetMapping("buscar/{codigo_producto}")
        public ResponseEntity<Productos> buscarProducto(@PathVariable("codigo_producto") Long codigo_producto){
            Productos producto=productosServicios.buscarProducto(codigo_producto);
            if(producto==null){
                return ResponseEntity.notFound().build();
            }
            return ResponseEntity.ok(producto);
        }

        @GetMapping("/listar")
        public ResponseEntity<List<Productos>> listaProductos(){
            List<Productos> producto = productosServicios.listarProductos();
            if (producto.isEmpty()) {
                return ResponseEntity.noContent().build();
            } else {
                return ResponseEntity.ok(producto);
            }
        }

        @PostMapping("/guardar")
        public ResponseEntity<Productos> guardarProducto(@RequestBody Productos productos){
            Productos nuevoproducto=productosServicios.guardarProductos(productos);
            return ResponseEntity.status(HttpStatus.CREATED).body(nuevoproducto);
        }

        @PutMapping("/actualizar/{codigo_producto}")
        public ResponseEntity<Productos> actualizarProducto(@PathVariable("codigo_producto") Long codigo_producto, @RequestBody Productos productos){
            productos.setCodigo_producto(codigo_producto);
            Productos productoDB=productosServicios.actualizarProductos(productos);
            if(productoDB==null){
                return ResponseEntity.notFound().build();
            }
            return ResponseEntity.ok(productoDB);
        }

        @DeleteMapping("/eliminar/{codigo_producto}")
        public void eliminarProducto(@PathVariable("codigo_producto") Long codigo_producto){
            productosServicios.eliminarProductos(codigo_producto);
        }

    }
