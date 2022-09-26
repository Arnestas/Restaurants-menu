package com.example.restaurant.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;

import javax.sql.DataSource;


@Configuration
@EnableWebSecurity
public class DemoSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private DataSource securityDataSource; // for users security


	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {

		// use db autentication
		auth.jdbcAuthentication().dataSource(securityDataSource);


//		// add our users for in memory authentication
//		User.UserBuilder users = User.withDefaultPasswordEncoder();
//
//		auth.inMemoryAuthentication()
//			.withUser(users.username("john").password("test123").roles("EMPLOYEE"))
//			.withUser(users.username("mary").password("test123").roles("EMPLOYEE", "MANAGER"))
//			.withUser(users.username("susan").password("test123").roles("EMPLOYEE", "MANAGER", "ADMIN"));
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {

		http
			.csrf().disable()
			.authorizeRequests()
			.antMatchers("/").hasRole("EMPLOYEE")
			.antMatchers("/restaurants/**").hasRole("MANAGER")
			.antMatchers("/dishes/**").hasRole("MANAGER")
			.antMatchers("/menus/**").hasRole("ADMIN")
			.and()
			.formLogin()
				.loginPage("/login")
				.defaultSuccessUrl("/home", true)
				.permitAll()
			.and()
			.logout().permitAll()
			.and()
			.exceptionHandling().accessDeniedPage("/access-denied");



/* veikia
		http
				.csrf().disable()
				.authorizeRequests()
				.antMatchers("/", "index", "/css/*", "/js/*" ).permitAll()
				.antMatchers("/").hasRole("EMPLOYEE")
				.antMatchers("/restaurants/**").hasRole("MANAGER")
				.antMatchers("/dishes/**").hasRole("MANAGER")
				.antMatchers("/menus/**").hasRole("ADMIN")
				.anyRequest()
				.authenticated()
				.and()
				.formLogin()
				.loginPage("/login").permitAll()
				.defaultSuccessUrl("/hello", true);
		//.httpBasic();


 */
		
	}
		
}






