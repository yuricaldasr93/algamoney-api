package com.example.algamoney.api.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.expression.method.MethodSecurityExpressionHandler;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.expression.OAuth2MethodSecurityExpressionHandler;

@Configuration
//@EnableWebSecurity
@EnableResourceServer
@EnableGlobalMethodSecurity(prePostEnabled = true) //-> Habilita a segurança nos métodos
public class ResourceServerConfig extends ResourceServerConfigurerAdapter{

//	@Autowired
//	private UserDetailsService userDetailsService;
	
//	@Autowired
//	public void configure(AuthenticationManagerBuilder auth) throws Exception {
//		//Autenticação
//		auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
//	}
	
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

	@Bean
	public MethodSecurityExpressionHandler createExpressionHandler(){//-> Permite que a segurança dos métodos funcione com OAuth2
		return new OAuth2MethodSecurityExpressionHandler();
	}
}
