package com.nnk.springboot.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
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
				.antMatchers("/login").permitAll()
				.antMatchers("/bidList/**").hasAuthority("USER").anyRequest().authenticated()
				.antMatchers("/ruleName/**").hasAuthority("USER").anyRequest().authenticated()
				.antMatchers("/curve/**").hasAuthority("USER").anyRequest().authenticated()
				.antMatchers("/trade/**").hasAuthority("USER").anyRequest().authenticated()
				.antMatchers("/user/**").hasAuthority("ADMIN").anyRequest().authenticated()
				.and()
				.formLogin()
				.failureUrl("/login?error")
				.defaultSuccessUrl("/bidList/list")
				.and().logout()
				.logoutRequestMatcher(new AntPathRequestMatcher("/app-logout"))
				.logoutSuccessUrl("/login").and()
				.exceptionHandling()
				.accessDeniedPage("/app/error")
				.and().csrf().disable();
	}
}
