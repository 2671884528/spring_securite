package com.gyg.user.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import java.io.IOException;

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

	@Autowired
	DataSource dataSource;

	@Qualifier("userDetailsService")
	@Autowired
	UserDetailsService detailsService;

	/**
	 * 选择记住我
	 * @return
	 */
	@Bean
	public PersistentTokenRepository persistentTokenRepository(){
		JdbcTokenRepositoryImpl jdbcTokenRepository = new JdbcTokenRepositoryImpl();
		jdbcTokenRepository.setDataSource(dataSource);
		// 自动建表（表已存在会报异常）
//		jdbcTokenRepository.setCreateTableOnStartup(true);
		return jdbcTokenRepository;
	}


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
		// 退出登录
		http
				.logout()
				.logoutUrl("/user/logout")
				.logoutSuccessUrl("/user/index").permitAll();

		// 权限不足异常处理,可以直接跳转页面，也可以交给访问异常处理器 AccessDeniedHandler
		http.exceptionHandling()
				.accessDeniedPage("/error/403.html");

		http.formLogin()        //自定义界面
				.loginPage("/login.html")       //界面
				.loginProcessingUrl("/user/login")      //请求地址
				.successForwardUrl("/user/success").permitAll()
				.and()
				.authorizeRequests()
				.antMatchers("/", "/user/index", "/user/login")//设置不需要认证
				.permitAll()
				// 但权限，多对一无效
//				.antMatchers("/user/index")
//				.hasAuthority("admins")
				// 多权限，可以多对一
//				.hasAnyAuthority("admin","admins")
				// 角色检验不要加ROLE_,会封装一个ROLE_admin
//				.hasRole("admin")
				.anyRequest().authenticated()
				.and()
				.rememberMe()
				.tokenRepository(persistentTokenRepository())
				.tokenValiditySeconds(600)
				.userDetailsService(userDetailsService())
				.and().csrf().disable();//关闭防护

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
