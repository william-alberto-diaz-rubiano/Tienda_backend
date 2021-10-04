package com.backend.tienda.controladores;

import com.backend.tienda.entidades.Proveedores;
import com.backend.tienda.servicios.ProveedoresServicios;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins ="http://127.0.0.1:5500")
@RestController
@RequestMapping("/proveedores")
public class ProveedoresControlador {

    @Autowired
    private ProveedoresServicios proveedoresServicios;

    @GetMapping("buscar/{nitproveedor}")
    public ResponseEntity<Proveedores> buscarProveedor(@PathVariable("nitproveedor") Long nitproveedor){
        Proveedores proveedores=proveedoresServicios.buscarProveedor(nitproveedor);
        if(proveedores==null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(proveedores);
    }

    @GetMapping("/listar")
    public ResponseEntity<List<Proveedores>> listaProveedores(){
        List<Proveedores> proveedores = proveedoresServicios.listarProveedores();
        if (proveedores.isEmpty()) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.ok(proveedores);
        }
    }

    @PostMapping("/guardar")
    public ResponseEntity<Proveedores> guardarProveedores(@RequestBody Proveedores proveedores){
        Proveedores nuevoProveedor=proveedoresServicios.guardarProveedores(proveedores);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevoProveedor);
    }

    @PutMapping("/actualizar/{nitproveedor}")
    public ResponseEntity<Proveedores> actualizarProveedores(@PathVariable("nitproveedor") Long nitproveedor, @RequestBody Proveedores proveedores){
        proveedores.setNitproveedor(nitproveedor);
        Proveedores proveedoresDB=proveedoresServicios.actualizarProveedores(proveedores);
        if(proveedoresDB==null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(proveedoresDB);
    }

    @DeleteMapping("/eliminar/{nitproveedor}")
    public void eliminarProveedores(@PathVariable("nitproveedor") Long nitproveedor){
        proveedoresServicios.eliminarProveedores(nitproveedor);
    }
}

