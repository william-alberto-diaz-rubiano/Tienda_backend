package com.backend.tienda.controladores;

import com.backend.tienda.entidades.DetalleVentas;
import com.backend.tienda.servicios.DetalleVentasServicios;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins ="http://127.0.0.1:5500")
@RestController
@RequestMapping("/detalleventas")
public class DetalleVentasControlador {
    @Autowired
    private DetalleVentasServicios detalleVentasServicios;

    @GetMapping("buscar/{codigo_detalle_venta}")
    public ResponseEntity<DetalleVentas> buscarDetalleVenta(@PathVariable("codigo_detalle_venta") Long codigo_detalle_venta){
        DetalleVentas detalleVenta= detalleVentasServicios.buscarDetalleVenta(codigo_detalle_venta);
        if(detalleVenta==null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(detalleVenta);
    }

    @GetMapping("/listar")
    public ResponseEntity<List<DetalleVentas>> listaDetalleVenta(){
        List<DetalleVentas> detalleVentas = detalleVentasServicios.listarDetalleVentas();
        if (detalleVentas.isEmpty()) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.ok(detalleVentas);
        }
    }
    @PostMapping("/guardar")
    public ResponseEntity<DetalleVentas> guardarDetalleVenta(@RequestBody DetalleVentas detalleVentas){
        DetalleVentas nuevoDetalleVentas=detalleVentasServicios.guardarDetalleVentas(detalleVentas);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevoDetalleVentas);
    }

    @PutMapping("/actualizar/{codigo_detalle_venta}")
    public ResponseEntity<DetalleVentas> actualizarDetalleVenta(@PathVariable("codigo_detalle_venta") Long codigo_detalle_venta, @RequestBody DetalleVentas detalleVentas){
        detalleVentas.setCodigo_detalle_venta(codigo_detalle_venta);
        DetalleVentas detalleVentasDB=detalleVentasServicios.actualizarDetalleVentas(detalleVentas);
        if(detalleVentasDB==null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(detalleVentasDB);
    }

    @DeleteMapping("/eliminar/{codigo_detalle_venta}")
    public void eliminarDetalleVenta(@PathVariable("codigo_detalle_venta") Long codigo_detalle_venta){
        detalleVentasServicios.eliminarDetalleVentas(codigo_detalle_venta);
    }
}
