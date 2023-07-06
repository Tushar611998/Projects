package com.smart.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class ConfigFile{
	
	@Bean
	public UserDetailsService getUserDetailsService() {
		
		System.out.println("inside getuserdetails");
		
		CustomUserService userService = new CustomUserService();
		return userService;
	}
	
	@Bean
	public BCryptPasswordEncoder getBCryptPasswordEncoder() {
		System.out.println("inside getbcryptpassword");
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	public DaoAuthenticationProvider daoAuthenticationProvider() {
		System.out.println("inside daoauthenticationprovider");
		DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
		daoAuthenticationProvider.setUserDetailsService(getUserDetailsService());
		daoAuthenticationProvider.setPasswordEncoder(getBCryptPasswordEncoder());
		
		return daoAuthenticationProvider;
	}
	
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
		
		System.out.println("inside filterchain");
//		
//		http.csrf().disable()
//		.authorizeRequests()
//		.requestMatchers("/user/**")
//		.hasRole("USER")
//		.requestMatchers("/admin/**")
//		.hasRole("ADMIN")
//		.requestMatchers("/**")
//		.permitAll()
//		.and()
//		.formLogin();
        
        http.authorizeHttpRequests((authorizeHttpRequests) ->
                authorizeHttpRequests
                		
                        .requestMatchers("/user/**").hasRole("USER")
                        .requestMatchers("/**").permitAll()
        )
                .formLogin(form -> form
                		.loginPage("/login")
                		.loginProcessingUrl("/dologin")
                		.defaultSuccessUrl("/user/index")
                		).csrf(csrf -> csrf.disable());
		System.out.println("hello");
		
    return http.build();
	}

}
