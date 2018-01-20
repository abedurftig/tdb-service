package org.tdb.security.jwt;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.tdb.model.User;
import org.tdb.model.UserDTO;
import org.tdb.model.UserRepository;
import org.tdb.security.AccountCredentials;
import org.tdb.service.UserService;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collections;

public class JWTLoginFilter extends AbstractAuthenticationProcessingFilter {

    private UserService userService;

    public JWTLoginFilter(String url, AuthenticationManager authManager, UserService userService) {
        super(new AntPathRequestMatcher(url));
        setAuthenticationManager(authManager);
        this.userService = userService;
    }

    @Override
    public Authentication attemptAuthentication(
            HttpServletRequest req, HttpServletResponse res)
            throws AuthenticationException, IOException, ServletException {

        AccountCredentials creds = new ObjectMapper()
                .readValue(req.getInputStream(), AccountCredentials.class);

        res.setHeader("Access-Control-Expose-Headers", "Access-Control-Allow-Headers, Access-Control-Allow-Origin, Authorization");

        return getAuthenticationManager().authenticate(
                new UsernamePasswordAuthenticationToken(
                        creds.getUsername(),
                        creds.getPassword(),
                        Collections.emptyList()
                )
        );
    }

    @Override
    protected void successfulAuthentication(
            HttpServletRequest req,
            HttpServletResponse res, FilterChain chain,
            Authentication auth) throws IOException, ServletException {

        TokenAuthenticationService
                .addAuthentication(res, auth.getName());

        UserDTO userDTO = userService.findUserByPrincipal(auth.getPrincipal().toString());

        ObjectMapper mapper = new ObjectMapper();

        res.setContentType("application/json;charset=UTF-8");
        res.getWriter().print(mapper.writeValueAsString(userDTO));
        res.getWriter().flush();

    }
}
