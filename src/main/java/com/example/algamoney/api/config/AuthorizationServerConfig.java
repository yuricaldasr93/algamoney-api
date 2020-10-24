package com.example.algamoney.api.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter{

	@Autowired
	private AuthenticationManager authenticationManager;
	@Autowired
	private UserDetailsService userDetailsService;
	
	@Override
	public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
		clients.inMemory()
			.withClient("angular")//-> Identificação do cliente
			.secret("$2a$10$bhBy4S/E5pxUoeHHLFGQ1O/SdNdV.6Bc.5qK8BxlHoCg9tzrCggDK")//-> senha
			.scopes("read", "write")//-> o que o cliente pode fazer
			.authorizedGrantTypes("password", "refresh_token") //->  Utilizando o granType Password flow e refresh token
			.accessTokenValiditySeconds(1800) //-> Quanto tempo o token será válido
			.refreshTokenValiditySeconds(3600 * 24)
		.and()
			.withClient("mobile")//-> Identificação do cliente
			.secret("$2a$10$Txyy8Cb2Foxc2g.O9EJ6GOg/pyJRafNNRWJ9BWHNQbijLpPpoK5Zy")//-> senha
			.scopes("read")//-> o que o cliente pode fazer(escopo do cliente)
			.authorizedGrantTypes("password", "refresh_token") //->  Utilizando o granType Password flow e refresh token
			.accessTokenValiditySeconds(1800) //-> Quanto tempo o token será válido
			.refreshTokenValiditySeconds(3600 * 24);
	}
	
	@Override
	public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
		endpoints.tokenStore(tokenStore())
		.accessTokenConverter(accessTokenConverter())
		.reuseRefreshTokens(false)
		.userDetailsService(userDetailsService)
		.authenticationManager(authenticationManager);
	}
	
	@Bean
	public JwtAccessTokenConverter accessTokenConverter() {
		JwtAccessTokenConverter accessTokenConverter = new JwtAccessTokenConverter();
		accessTokenConverter.setSigningKey("algaworks");
		return accessTokenConverter;
	}
	
	@Bean
	public TokenStore tokenStore() {
		return new JwtTokenStore(accessTokenConverter());
	}
}
