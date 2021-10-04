package com.backend.tienda.controladores;

import com.backend.tienda.entidades.Usuarios;
import com.backend.tienda.servicios.UsuariosServicios;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins ="http://127.0.0.1:5500")
@RestController
@RequestMapping("/usuarios")
public class UsuariosControlador {
    @Autowired
    private UsuariosServicios usuariosServicios;

    @GetMapping("buscar/{cedula_usuario}")
    public ResponseEntity<Usuarios> buscarUsuario(@PathVariable("cedula_usuario") Long cedula_usuario){
        Usuarios usuarios =usuariosServicios.buscarUsuario(cedula_usuario);
        if(usuarios==null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(usuarios);
    }

    @GetMapping("/listar")
    public ResponseEntity<List<Usuarios>> listaUsuarios(){
        List<Usuarios> usuarios = usuariosServicios.listarUsuarios();
        if (usuarios.isEmpty()) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.ok(usuarios);
        }
    }

    @PostMapping("/guardar")
    public ResponseEntity<Usuarios> guardarUsuarios(@RequestBody Usuarios usuarios){
        Usuarios nuevoUsuario=usuariosServicios.guardarUsuarios(usuarios);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevoUsuario);
    }

    @PutMapping("/actualizar/{cedula_usuario}")
    public ResponseEntity<Usuarios> actualizarUsuario(@PathVariable("cedula_usuario") Long cedula_usuario, @RequestBody Usuarios usuarios){
        usuarios.setCedula_usuario(cedula_usuario);
        Usuarios usuarioDB=usuariosServicios.actualizarUsuarios(usuarios);
        if(usuarioDB==null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(usuarioDB);
    }

    @DeleteMapping("/eliminar/{cedula_cliente}")
    public void eliminarUsuarios(@PathVariable("cedula_cliente") Long cedula_cliente){
        usuariosServicios.eliminarUsuarios(cedula_cliente);
    }
}
