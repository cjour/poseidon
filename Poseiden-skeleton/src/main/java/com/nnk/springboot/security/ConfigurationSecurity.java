package com.nnk.springboot.security;

import com.nnk.springboot.services.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class ConfigurationSecurity extends WebSecurityConfigurerAdapter{

	@Autowired
	private UserDetailsService userDetailsService;

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService).passwordEncoder(new BCryptPasswordEncoder());
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.
				authorizeRequests()
				//Permit All to / and /login
				.antMatchers("/").permitAll()
				.antMatchers("/login").permitAll()

				//Restrain access to /user/ to role ADMIN only
				.antMatchers("/user/**").hasAuthority("ADMIN").anyRequest().authenticated()

				//Configuration login behavior
				.and()
				.formLogin()
				.failureUrl("/login?error")
				.defaultSuccessUrl("/bidList/list")

				//Configure logout behavior
				.and().logout()
				.logoutRequestMatcher(new AntPathRequestMatcher("/app-logout"))
				.logoutSuccessUrl("/login").and()

				//Configure denied page
				.exceptionHandling()
				.accessDeniedPage("/app/error");
	}
}
