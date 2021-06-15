package briv.solutions.SystemeMedicalBack.security;

import java.io.IOException;
import java.util.Date;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import briv.solutions.SystemeMedicalBack.SpringApplicationContext;
import briv.solutions.SystemeMedicalBack.models.Utilisateur;
import briv.solutions.SystemeMedicalBack.services.IUtilisateurService;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

public class AuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private final AuthenticationManager authenticationManager;

    public AuthenticationFilter(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
            throws AuthenticationException {
        try {
            Utilisateur UserInfo = new ObjectMapper().readValue(request.getInputStream(), Utilisateur.class);
            return authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(UserInfo.getEmail(), UserInfo.getEncryptePassword()));
        } catch (IOException e) {
            throw new RuntimeException();
        }
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
            Authentication authResult) throws IOException, ServletException {

        String userName = ((User) authResult.getPrincipal()).getUsername();

        IUtilisateurService userService = (IUtilisateurService) SpringApplicationContext
                .getBean("utilisateurServiceImpl");
        Utilisateur user = userService.getUser(userName);

        String token = Jwts.builder().setSubject(userName)
                .setExpiration(new Date(System.currentTimeMillis() + SecurityCanstants.EXPIRATION_TIME))
                .signWith(SignatureAlgorithm.HS512, SecurityCanstants.getSecretToken()).compact();

        response.addHeader(SecurityCanstants.HEADER_STRING, SecurityCanstants.TOKEN_PREFIX + token);
        response.addHeader("UserID", user.getId());
    }

}
