package com.prueba.usuarios.model.dao;

import com.prueba.usuarios.model.entity.Admin;
import org.springframework.data.repository.CrudRepository;

public interface IAdminDAO extends CrudRepository<Admin, Long> {
    public Admin findByUsername(String username);
}
