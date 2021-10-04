package com.backend.tienda.controladores;

import com.backend.tienda.entidades.Clientes;
import com.backend.tienda.servicios.ClientesServicios;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@CrossOrigin(origins ="http://127.0.0.1:5500")
@RestController
@RequestMapping("/clientes")
public class ClientesControlador {

    @Autowired
    private ClientesServicios clientesServicios;

    @GetMapping("buscar/{cedula_cliente}")
    public ResponseEntity<Clientes> buscarCliente(@PathVariable("cedula_cliente") Long cedula_cliente){
        Clientes clientes=clientesServicios.buscarClientes(cedula_cliente);
        if(clientes==null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(clientes);
    }

    @GetMapping("/listar")
    public ResponseEntity<List<Clientes>> listaClientes(){
        List<Clientes> clientes = clientesServicios.listarClientes();
        if (clientes.isEmpty()) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.ok(clientes);
        }
    }

    @PostMapping("/guardar")
    public ResponseEntity<Clientes> guardarCliente(@RequestBody Clientes clientes){
        Clientes nuevoCliente=clientesServicios.guardarClientes(clientes);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevoCliente);
    }

    @PutMapping("/actualizar/{cedula_cliente}")
    public ResponseEntity<Clientes> actualizarCliente(@PathVariable("cedula_cliente") Long cedula_cliente, @RequestBody Clientes clientes){
      clientes.setCedula_cliente(cedula_cliente);
      Clientes clienteDB=clientesServicios.actualizarClientes(clientes);
      if(clienteDB==null){
          return ResponseEntity.notFound().build();
      }
      return ResponseEntity.ok(clienteDB);
    }

    @DeleteMapping("/eliminar/{cedula_cliente}")
    public void eliminarCliente(@PathVariable("cedula_cliente") Long cedula_cliente){
        clientesServicios.eliminarClientes(cedula_cliente);
    }



}
