package com.genpact.ms.modules.security.dao;

import java.util.List;

import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.genpact.ms.modules.security.entity.Role;

@Repository
@CacheConfig(cacheNames = "roles")
public interface RoleDao extends JpaRepository<Role, Integer> {

    @Cacheable
    Role findOne(int id);

    @Cacheable
    List<Role> findAll();

    void delete(int id);
}
