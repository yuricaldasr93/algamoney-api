package com.example.algamoney.api.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;

@Configuration
@EnableWebSecurity
@EnableResourceServer
public class ResourceServerConfig extends ResourceServerConfigurerAdapter{

	@Autowired
	public void configure(AuthenticationManagerBuilder auth) throws Exception {
		//Autenticação
		auth.inMemoryAuthentication()
		.withUser("admin")
		.password("admin")
		.roles("ROLE");
	}
	
	@Override
	public void configure(HttpSecurity http) throws Exception {
		//Autorização
		http.authorizeRequests()
		.antMatchers("/categorias").permitAll() //-> Autoriza o acesso de todos ao recurso /categorias
		.anyRequest().authenticated() //-> Todas as requisições precisam estar autenticadas(exceto acima)
		//.and().httpBasic()//-> Tipo de autenticação
		.and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)//-> Desabilita criação de sessão(Sem estado)
		.and().csrf().disable();//-> Desabilita o CSRF
	}
	
	@Override
	public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
		resources.stateless(true);
	}	
}
