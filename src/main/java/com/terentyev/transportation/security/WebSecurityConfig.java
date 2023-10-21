package com.terentyev.transportation.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.terentyev.transportation.services.WorkerDetailsService;


@Configuration
@EnableWebSecurity
public class WebSecurityConfig {
	@Lazy
	private final WorkerDetailsService workerDetailsService;

	@Autowired
	public WebSecurityConfig() {
		this.workerDetailsService = new WorkerDetailsService();}

	@Bean
	public BCryptPasswordEncoder bCryptPasswordEncoder() {
	    return new BCryptPasswordEncoder();
	}
	
	
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http
		
		.cors(Customizer.withDefaults())
		.csrf(Customizer.withDefaults()).authorizeHttpRequests((requests) -> requests
				.requestMatchers("/registration", "/login", "/logout").anonymous()
				//.requestMatchers("/driver/**").hasAnyRole("DRIVER", "BOSS")
				//.requestMatchers("/boss/**").hasRole("BOSS")
				.anyRequest().permitAll()
			)
			.formLogin((form) -> form
				.loginPage("/")
				.usernameParameter("username")
	            .passwordParameter("password")
				.defaultSuccessUrl("/")
				.loginProcessingUrl("/login")
				.permitAll()
			)
			.logout((logout) -> logout.permitAll().logoutSuccessUrl("/login"));

		return http.build();
	}
	
		
	 
	 protected void configure(AuthenticationManagerBuilder auth) throws Exception {
	     auth.userDetailsService(workerDetailsService).passwordEncoder(bCryptPasswordEncoder());
	 }

}
