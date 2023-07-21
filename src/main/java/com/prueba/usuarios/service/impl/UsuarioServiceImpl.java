package com.prueba.usuarios.service.impl;

import com.prueba.usuarios.model.dao.ITelefonoDao;
import com.prueba.usuarios.model.dao.IUsuarioDao;
import com.prueba.usuarios.model.entity.Usuario;
import com.prueba.usuarios.service.IUsuarioService;
import com.prueba.usuarios.util.UsuarioUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Service
public class UsuarioServiceImpl implements IUsuarioService {
    private static final Logger logger = LoggerFactory.getLogger(UsuarioServiceImpl.class);

    @Autowired
    private IUsuarioDao usuarioDao;

    @Autowired
    private ITelefonoDao telefonoDao;

    Map<String, Object> mensaje = null;

    @Value("${usuario.pass.regx}")
    private String regexPassword;

    @Override
    public Usuario save(Usuario usuario) {
        if(!validarPrerequisitos(usuario)){
            usuario.setTieneErrores(true);
            return usuario;
        } else {
            UsuarioUtil.setUsuario(usuario);
            usuario.getPhones().stream().collect(Collectors.toList()).forEach(telefonoDao::save);
            usuario = usuarioDao.save(usuario);
        }
        return usuario;
    }

    private Usuario findByEmail(String email) {
        return usuarioDao.findByemail(email);
    }

    private boolean validarPrerequisitos(Usuario usuario) {
        boolean respuesta = true;
            if (findByEmail(usuario.getEmail()) != null) {
                mensaje = new HashMap<>();
                mensaje.put("mensaje", "El correo ya esta registrado");
                usuario.setMensaje(mensaje);
                respuesta = false;
            }
            if (!validarPassword(usuario.getPassword())) {
                mensaje = new HashMap<>();
                mensaje.put("mensaje", "Password debe tener al menos una mayuscula, letras minusculas y dos numeros");
                usuario.setMensaje(mensaje);
                respuesta =  false;
            }
            return respuesta;
    }

    private boolean validarPassword(String password) {
        Pattern regex = Pattern.compile(regexPassword);
        Matcher matcher = regex.matcher(password);
        if(matcher.matches()){
            return true;
        }
        return false;
    }
}
