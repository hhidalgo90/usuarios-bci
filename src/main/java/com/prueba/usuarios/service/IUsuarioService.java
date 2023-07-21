package com.prueba.usuarios.service;


import com.prueba.usuarios.model.entity.Usuario;

import java.util.List;
import java.util.Map;

public interface IUsuarioService {

    List<Usuario> findAll();

    Usuario save(Usuario usuario);

    Usuario findByEmail(String email);
}