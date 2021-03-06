package com.genpact.ms.modules.security.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.genpact.ms.modules.security.dao.RoleDao;
import com.genpact.ms.modules.security.entity.Role;


@Service
@Transactional
public class RoleService {

    @Autowired
    private RoleDao roleDao;

    /**
     * 查询所有的角色
     *
     * @return
     */
    public List<Role> findAll() {
        return roleDao.findAll();
    }

    /**
     * 删除角色
     *
     * @param id
     */
    public void deleteById(Integer id) {
        roleDao.delete(id);
    }

    /**
     * 根据id查找角色
     *
     * @param id
     * @return
     */
    public Role findById(int id) {
        return roleDao.findOne(id);
    }

    public void save(Role role) {
        roleDao.save(role);
    }

}
