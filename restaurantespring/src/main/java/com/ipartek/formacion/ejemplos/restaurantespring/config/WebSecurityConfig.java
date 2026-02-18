package com.ipartek.formacion.ejemplos.restaurantespring.config;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.LogoutConfigurer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
class WebSecurityConfig {

	// Autenticación
	@Bean
	UserDetailsService userDetailsService(PasswordEncoder encoder, DataSource dataSource) {
		var jdbc = new JdbcUserDetailsManager(dataSource);

		jdbc.setUsersByUsernameQuery("""
				SELECT email, password, 1
				FROM clientes
				WHERE email = ?
				""");
		
		jdbc.setAuthoritiesByUsernameQuery("""
				SELECT email, 
					CASE 
						WHEN email = 'javier@email.net' THEN 'ROLE_ADMINISTRADOR'
						ELSE 'ROLE_USUARIO' 
					END AS rol
				FROM clientes
				WHERE email = ?
				""");
		
		return jdbc;
	}

	@Bean
	PasswordEncoder passwordEncoder() {
		return NoOpPasswordEncoder.getInstance(); // new BCryptPasswordEncoder();
	}

	// Autorización
	@Bean
	SecurityFilterChain securityFilterChain(HttpSecurity http) {
		// @formatter:off
		http
			.authorizeHttpRequests(requests -> requests
				.requestMatchers("/admin/**").hasRole("ADMINISTRADOR")
				.anyRequest().authenticated()
			)
			.formLogin(form -> form
				// .loginPage("/login")
				.permitAll()
			)
			.logout(LogoutConfigurer::permitAll);
		// @formatter:on

		return http.build();
	}

}