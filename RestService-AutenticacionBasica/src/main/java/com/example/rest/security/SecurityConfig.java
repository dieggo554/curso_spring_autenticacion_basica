package com.example.rest.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	//una forma de registrar usuarios
	//los datos se envia codificados en base 64
	@Autowired
	public void confireGlobalSecurity(AuthenticationManagerBuilder auth) throws Exception{
		auth.inMemoryAuthentication().withUser("alberto").password("alberto").roles("ADMIN");
		auth.inMemoryAuthentication().withUser("user").password("user").roles("USER");
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		//evita acceso malicioso para suplantar, lo desabilitamos por que trabajamos en local
		http.csrf().disable().authorizeRequests()
		.antMatchers("/todos_profesores_public").permitAll()
		.antMatchers("/todos_profesores_admin").hasRole("ADMIN")
		.antMatchers("/todos_profesores_user").hasRole("USER")
		//da acceso al rol admin a todos los endpoint a partir de escribirBD
		.antMatchers("/*/escribirBD/**").hasRole("ADMIN")
		.and()
		.httpBasic();
	}
	
	//no hacer nunca, desactiva el requerimiento de contraseña encriptada
	@SuppressWarnings("deprecation")
	@Bean
	public static NoOpPasswordEncoder passwordEncoder() {
		return (NoOpPasswordEncoder) NoOpPasswordEncoder.getInstance();
	}
}
