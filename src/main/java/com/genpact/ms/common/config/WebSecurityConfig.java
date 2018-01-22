package com.genpact.ms.common.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Configuration
@EnableWebMvc
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
// 开启security注解
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Override
	public void configure(WebSecurity webSecurity) throws Exception {
		webSecurity.ignoring().antMatchers("/bootstrap/**");
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests().antMatchers("/index", "/login", "/register").permitAll().anyRequest().authenticated().and().formLogin().loginPage("/login").defaultSuccessUrl("/index")// 登录成功后默认跳转到"/index"
				.permitAll().and().logout().logoutSuccessUrl("/index")// 退出登录后的默认url是"/index"
				.permitAll();

	}
	
	/**
	 * 自定义UserDetailsService，从数据库中读取用户信息
	 */
//	@Bean
//	public MyUserDetailService myUserDetailService() {
//		return new MyUserDetailService();
//	}
//
//	@Autowired
//	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
//		auth.userDetailsService(myUserDetailService()).passwordEncoder(passwordEncoder());
//	}

	/**
	 * 设置用户密码的加密方式为MD5加密
	 */
//	@Bean
//	public Md5PasswordEncoder passwordEncoder() {
//		return new Md5PasswordEncoder();
//
//	}

}