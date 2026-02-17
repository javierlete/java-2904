package com.ipartek.formacion.ejemplos.restaurantespring.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.LogoutConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
class WebSecurityConfig {

	// Autenticación
	@Bean
	UserDetailsService userDetailsService(PasswordEncoder encoder) {
		// @formatter:off
		UserDetails javier = User
				.withUsername("javier@email.net")
				.password(encoder.encode("Javier1!"))
				.roles("ADMINISTRADOR")
			.build();
			
		UserDetails pepe = User
				.withUsername("pepe@email.net")
				.password(encoder.encode("Pepe1!"))
				.roles("USUARIO")
			.build();
		// @formatter:on

		return new InMemoryUserDetailsManager(javier, pepe);
	}

	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
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