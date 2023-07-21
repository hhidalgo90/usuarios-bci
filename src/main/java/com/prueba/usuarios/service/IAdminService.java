package com.prueba.usuarios.service;

import com.prueba.usuarios.model.entity.Admin;

public interface IAdminService {
    Admin findByUsername(String username);
}
