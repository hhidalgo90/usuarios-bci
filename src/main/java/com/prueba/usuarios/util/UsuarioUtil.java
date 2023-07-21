package com.prueba.usuarios.util;

import com.prueba.usuarios.model.entity.Usuario;

import java.time.LocalDate;

public class UsuarioUtil {

    public static void setUsuario(Usuario usuario) {
        usuario.setCreated(LocalDate.now());
        usuario.setModified(LocalDate.now());
        usuario.setLast_login(LocalDate.now());
        usuario.setIsActive(true);
    }
}
