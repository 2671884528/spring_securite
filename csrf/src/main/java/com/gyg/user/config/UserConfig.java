package com.gyg.user.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * @author 郭永钢
 */
@Configuration
public class UserConfig extends WebSecurityConfigurerAdapter {
//	@Override
//	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//		PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
//		String admin = passwordEncoder.encode("admin");
//		auth.inMemoryAuthentication().withUser("admin").password(admin).roles("admin");
//	}


	@Qualifier("userDetailsService")
	@Autowired
	UserDetailsService detailsService;

	/**
	 * 采取默认的界面
	 *
	 * @param auth
	 * @throws Exception
	 */
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(detailsService).passwordEncoder(passwordEncoder());
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.formLogin()        //自定义界面
				.loginPage("/login.html")       //界面
				.loginProcessingUrl("/user/login")      //请求地址
				.successForwardUrl("/user/success").permitAll()
				.and()
				.authorizeRequests()
				.antMatchers("/", "/user/hello","/user/index")//设置不需要认证
				.permitAll()
				.anyRequest().authenticated();
//				.and().csrf().disable();//关闭防护

	}

	/**
	 * 自定义
	 *
	 * @return
	 */


	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
}
