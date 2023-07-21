package com.prueba.usuarios.controller;

import com.prueba.usuarios.model.entity.Usuario;
import com.prueba.usuarios.service.IUsuarioService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@Slf4j
@RequestMapping("/usuarios")
public class UsuarioController {
    private static final Logger logger = LoggerFactory.getLogger(UsuarioController.class);

    @Autowired
    private IUsuarioService usuarioService;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE )
    @ResponseStatus(HttpStatus.OK)
    public List<Usuario> findAll(){
        return usuarioService.findAll();
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE )
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<?> guardar(@Valid @RequestBody Usuario usuario) {
        Map<String, Object> respuesta = new HashMap<>();
        Usuario nuevoUsuario = usuarioService.save(usuario);

        if(nuevoUsuario.isTieneErrores()){
            return new ResponseEntity<Map<String, Object>>(nuevoUsuario.getMensaje(), HttpStatus.BAD_REQUEST);
        }
        respuesta.put("mensaje" , "usuario creado con exito");
        respuesta.put("usuario" , nuevoUsuario);
        return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.CREATED);
    }
}
