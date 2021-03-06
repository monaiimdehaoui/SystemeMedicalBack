package briv.solutions.SystemeMedicalBack.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import io.jsonwebtoken.Jwts;

public class AuthorizationFilter extends BasicAuthenticationFilter {

	public AuthorizationFilter(AuthenticationManager authManager) {
		super(authManager);
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		String header = request.getHeader(SecurityCanstants.HEADER_STRING);

		if (header == null || !header.startsWith(SecurityCanstants.TOKEN_PREFIX)) {

			chain.doFilter(request, response);
			return;
		}

		UsernamePasswordAuthenticationToken authentication = getAuthetication(request);
		SecurityContextHolder.getContext().setAuthentication(authentication);
		chain.doFilter(request, response);
	}

	private UsernamePasswordAuthenticationToken getAuthetication(HttpServletRequest request) {

		String token = request.getHeader(SecurityCanstants.HEADER_STRING);
		if (token != null) {

			token = token.replace(SecurityCanstants.TOKEN_PREFIX, "");

			String user = Jwts.parser().setSigningKey(SecurityCanstants.getSecretToken()).parseClaimsJws(token)
					.getBody().getSubject();
			if (user != null) {

				return new UsernamePasswordAuthenticationToken(user, null, null);
			}

		}
		return null;
	}


}
