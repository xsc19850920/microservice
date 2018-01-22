package com.genpact.ms.modules.user.controller;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.genpact.ms.common.config.SiteConfig;
import com.genpact.ms.common.controller.BaseController;
import com.genpact.ms.common.utils.JsonResult;
import com.genpact.ms.modules.security.entity.Role;
import com.genpact.ms.modules.security.service.RoleService;
import com.genpact.ms.modules.user.entity.User;
import com.genpact.ms.modules.user.service.UserService;


@RestController
@RequestMapping("/admin/user")
public class UserAdminController extends BaseController {

    @Autowired
    private SiteConfig siteConfig;
    @Autowired
    private UserService userService;
    @Autowired
    private RoleService roleService;

    /**
     * 用户列表
     */
    @GetMapping("/list")
    public JsonResult list(Integer p) {
    	return JsonResult.success(userService.pageUser(p == null ? 1 : p, siteConfig.getPageSize()));
    }

    /**
     * 删除用户
     */
//    @GetMapping(value = "/{id}/delete")
//    public String delete(@PathVariable Integer id, HttpServletResponse response) {
//        userService.deleteById(id);
//        return redirect(response, "/admin/user/list");
//    }

    /**
     * 禁用用户
     */
    @GetMapping("/{id}/block")
    public JsonResult block(@PathVariable Integer id, HttpServletResponse response) {
        userService.blockUser(id);
        return JsonResult.success(null);
    }

    /**
     * 解禁用户
     */
    @GetMapping("/{id}/unblock")
    public JsonResult unblock(@PathVariable Integer id, HttpServletResponse response) {
        userService.unBlockUser(id);
        return JsonResult.success(null);
    }

    /**
     * 配置用户的角色
     */
    @GetMapping("/{id}/role")
    public JsonResult role(@PathVariable Integer id) {
    	User user = userService.findById(id);
    	List<Role> roles = roleService.findAll();
    	Map<String, Object> map = new HashMap<String, Object>();
    	map.put("user", user);
    	map.put("roles", roles);
    	return JsonResult.success(map);
    }

    /**
     * 保存配置用户的角色
     */
    @PostMapping("/{id}/role")
    public JsonResult saveRole(@PathVariable Integer id, Integer[] roleIds, HttpServletResponse response) {
        User user = userService.findById(id);
        Set<Role> roles = new HashSet<Role>();
        for (int i : roleIds) {
            Role role = roleService.findById(i);
            roles.add(role);
        }
        user.setRoles(roles);
        userService.save(user);
        return JsonResult.success(null);
    }

}
