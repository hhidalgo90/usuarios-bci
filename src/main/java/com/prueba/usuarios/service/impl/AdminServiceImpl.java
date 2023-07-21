package com.prueba.usuarios.service.impl;

import com.prueba.usuarios.model.dao.IAdminDAO;
import com.prueba.usuarios.model.entity.Admin;
import com.prueba.usuarios.service.IAdminService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AdminServiceImpl implements IAdminService, UserDetailsService {

    private Logger logger = LoggerFactory.getLogger(AdminServiceImpl.class);

    @Autowired
    private IAdminDAO adminDAO;

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Admin admin = adminDAO.findByUsername(username);
        if (admin == null) {
            logger.error("Error en el login: no existe admin: " + username + " en el sistema.");
            throw new UsernameNotFoundException("Error en el login: no existe admin: " + username + " en el sistema.");
        }
        List<GrantedAuthority> authorities = admin.getRoles().stream()
                .map(role -> new SimpleGrantedAuthority(role.getNombre()))
                .peek(simpleGrantedAuthority -> logger.info(simpleGrantedAuthority.getAuthority()))//sirve para mostrar por consola
                .collect(Collectors.toList());//convertimos la clase Role a GrantedAuthority

        return new User(admin.getUsername(), admin.getPassword(), admin.getEnabled(), true, true, true, authorities);
    }

    @Override
    @Transactional(readOnly = true)
    public Admin findByUsername(String username) {
        return adminDAO.findByUsername(username);
    }
}
