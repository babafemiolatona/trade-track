package com.springboot.tradetrack.Dao;


import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.springboot.tradetrack.Models.Role;

public interface RoleDao extends CrudRepository<Role, Integer> {

    boolean existsByName(String name);
    Optional<Role> findByName(String name);

}
