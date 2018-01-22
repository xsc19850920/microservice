package com.genpact.ms.modules.index.controller;

import java.util.Date;
import java.util.UUID;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.genpact.ms.common.config.SiteConfig;
import com.genpact.ms.common.controller.BaseController;
import com.genpact.ms.modules.user.entity.User;
import com.genpact.ms.modules.user.service.UserService;

@Controller
public class IndexController extends BaseController {

	@Autowired
	private UserService userService;
	@Autowired
	private SiteConfig siteConfig;
	

	/**
	 * 进入登录页
	 *
	 * @return
	 */
	@GetMapping("/login")
	public String toLogin(String s, Model model, HttpServletResponse response) {
		if (getUser() != null) {
			return redirect(response, "/");
		}
		model.addAttribute("s", s);
		return render("/login");
	}

	/**
	 * 进入注册页面
	 *
	 * @return
	 */
	@GetMapping("/register")
	public String toRegister(HttpServletResponse response) {
		if (getUser() != null) {
			return redirect(response, "/");
		}
		return render("/register");
	}
	
	
	
	@GetMapping("/index")
	public String toIndex(HttpServletResponse response) {
		if (getUser() != null) {
			return redirect(response, "/");
		}
		return render("/index");
	}
	

	/**
	 * 注册验证
	 *
	 * @param username
	 * @param password
	 * @return
	 */
	@PostMapping("/register")
	public String register(String username, String password, HttpServletResponse response, Model model) {
		User user = userService.findByUsername(username);
		if (user != null) {
			model.addAttribute("errors", "用户名已经被注册");
		} else if (StringUtils.isEmpty(username)) {
			model.addAttribute("errors", "用户名不能为空");
		} else if (StringUtils.isEmpty(password)) {
			model.addAttribute("errors", "密码不能为空");
		} else {
			Date now = new Date();
			String avatarName = UUID.randomUUID().toString();
			user = new User();
			user.setUsername(username);
			user.setPassword(new BCryptPasswordEncoder().encode(password));
			user.setInTime(now);
			user.setBlock(false);
			user.setToken(UUID.randomUUID().toString());
			user.setAvatar(avatarName + ".png");
			userService.save(user);
			return redirect(response, "/login?s=reg");
		}
		return render("/register");
	}

}
