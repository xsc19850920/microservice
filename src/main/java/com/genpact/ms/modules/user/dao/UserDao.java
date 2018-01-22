package com.genpact.ms.modules.user.dao;

import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.genpact.ms.modules.user.entity.User;


@Repository
@CacheConfig(cacheNames = "users")
public interface UserDao extends JpaRepository<User, Integer> {

    @Cacheable
    User findOne(int id);

    @Cacheable
    User findByUsername(String username);

    @CacheEvict
    void delete(int id);

    @Cacheable
    User findByToken(String token);

}
