package com.example.restaurant.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.sql.DataSource;


@SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
@Configuration
@EnableWebSecurity
public class DemoSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private DataSource securityDataSource; // for users security


	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {

		// use db authentication
		auth.jdbcAuthentication().dataSource(securityDataSource);


		// add our users for in memory authentication
		User.UserBuilder users = User.withDefaultPasswordEncoder();

		auth.inMemoryAuthentication()
			.withUser(users.username("john").password("123").roles("EMPLOYEE"))
			.withUser(users.username("mary").password("123").roles("EMPLOYEE", "MANAGER"))
			.withUser(users.username("susan").password("123").roles("EMPLOYEE", "MANAGER", "ADMIN"));
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {

		http
			.csrf().disable()
			.authorizeRequests()
			.antMatchers("/").hasRole("EMPLOYEE")
			.antMatchers("/restaurants/**").hasRole("ADMIN")
			.antMatchers("/restaurants_employee/**").hasRole("EMPLOYEE")
			.antMatchers("/dishes/**").hasRole("ADMIN")
			.antMatchers("/menus/**").hasRole("ADMIN")
			.and()
			.formLogin()
				.loginPage("/login")
				.defaultSuccessUrl("/home", true)
				.permitAll()
			.and()
			.logout()
				.logoutUrl("/logout")
				.logoutRequestMatcher(new AntPathRequestMatcher("/logout","GET"))
				.clearAuthentication(true)
				.invalidateHttpSession(true)
				.deleteCookies("JSESSIONID")
				.logoutSuccessUrl("/login")
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