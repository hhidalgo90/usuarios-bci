package com.prueba.usuarios.controller;

import com.prueba.usuarios.model.entity.Usuario;
import com.prueba.usuarios.service.IUsuarioService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@RestController
@Tag(name = "API Usuarios")
@Slf4j
@RequestMapping("/usuarios")
public class UsuarioController {
    private static final Logger logger = LoggerFactory.getLogger(UsuarioController.class);

    @Autowired
    private IUsuarioService usuarioService;

    @Operation(summary = "Crea un objeto del tipo Usuario", description = "Retorna el objeto creado.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Usuario creado"),
            @ApiResponse(responseCode = "404", description = "Bad Request - Se requiere modificar el request para consumir el servicio.")
    })
    @Secured({"ROLE_ADMIN"})
    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE )
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<?> guardar(@Valid @RequestBody Usuario usuario, @RequestHeader(HttpHeaders.AUTHORIZATION) String token) {
        logger.info("[UsuarioController] [guardar] Token: " + token);
        usuario.setToken(token.substring(7, token.length()));
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
