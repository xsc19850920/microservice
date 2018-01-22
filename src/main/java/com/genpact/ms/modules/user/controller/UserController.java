package com.genpact.ms.modules.user.controller;

import java.util.UUID;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.genpact.ms.common.config.SiteConfig;
import com.genpact.ms.common.controller.BaseController;
import com.genpact.ms.common.utils.JsonResult;
import com.genpact.ms.modules.user.entity.User;
import com.genpact.ms.modules.user.service.UserService;

@Controller
@RequestMapping("/user")
public class UserController extends BaseController {

	@Autowired
	private SiteConfig siteConfig;
	@Autowired
	private UserService userService;
	

	/**
	 * 修改密码
	 * 
	 * @param oldPassword
	 * @param newPassword
	 * @param model
	 * @return
	 */
	@PostMapping("/changePassword")
	public JsonResult changePassword(String oldPassword, String newPassword, HttpServletResponse response) {
		User user = getUser();
		JsonResult result = null;
		if (user.isBlock())
			return JsonResult.error("你的帐户已经被禁用，不能进行此项操作");
		if (new BCryptPasswordEncoder().matches(oldPassword, user.getPassword())) {
			user.setPassword(new BCryptPasswordEncoder().encode(newPassword));
			userService.updateUser(user);
			result = JsonResult.success("修改成功，请重新登录");
		} else {
			result = JsonResult.error("旧密码不正确");
		}
		return result;
	}

	/**
	 * 刷新token
	 * 
	 * @param response
	 * @return
	 */
	@GetMapping("/refreshToken")
	public JsonResult refreshToken(HttpServletResponse response) {
		User user = getUser();
		user.setToken(UUID.randomUUID().toString());
		userService.save(user);
		return JsonResult.success(user);
	}

}
