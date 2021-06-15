package briv.solutions.SystemeMedicalBack.security;

import javax.annotation.Resource;

import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.cors.CorsConfigurationSource;

import briv.solutions.SystemeMedicalBack.services.IUtilisateurService;

@EnableWebSecurity
public class WebSecurity extends WebSecurityConfigurerAdapter {

	private final IUtilisateurService userDetailsService;

	private final BCryptPasswordEncoder bCryptPasswordEncoder;

	@Resource(name = "corsConfigurationSource")
	CorsConfigurationSource corsConfig;

	public WebSecurity(IUtilisateurService userDetailsService, BCryptPasswordEncoder bCryptPasswordEncoder) {
		super();
		this.userDetailsService = userDetailsService;
		this.bCryptPasswordEncoder = bCryptPasswordEncoder;
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {

		auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder);
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.cors().configurationSource(corsConfig);
		http.csrf().disable().authorizeRequests()
				.antMatchers(SecurityCanstants.AUTORIZED_URLS, "/v2/api-docs", "/configuration/**", "/swagger*/**",
						"/webjars/**")
				.permitAll()

				.anyRequest().authenticated().and().addFilter(getAuthenticationFilter())
				.addFilter(new AuthorizationFilter(authenticationManager()));

	}

	public AuthenticationFilter getAuthenticationFilter() throws Exception {

		final AuthenticationFilter authenticationFilter = new AuthenticationFilter(authenticationManager());
		authenticationFilter.setFilterProcessesUrl("/api/public/login");

		return authenticationFilter;
	}

}
