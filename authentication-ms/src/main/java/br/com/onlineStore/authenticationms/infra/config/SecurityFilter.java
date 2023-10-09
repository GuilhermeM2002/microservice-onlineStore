package br.com.onlineStore.authenticationms.infra.config;

import br.com.onlineStore.authenticationms.adapters.repository.UserRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
@Component
public class SecurityFilter extends OncePerRequestFilter {
    @Autowired
    private UserRepository repository;
    @Autowired
    private TokenService tokenService;
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        var tokenRecovered = recoverToken(request);

        if (tokenRecovered != null){
            var subject = tokenService.TokenVerifier(tokenRecovered);
            var user = repository.findUserByEmail(String.valueOf(subject));

            var authentication = new UsernamePasswordAuthenticationToken(
                    user, null, user.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }
        filterChain.doFilter(request, response);
    }
    private String recoverToken(HttpServletRequest request){
        var authorizationHeader = request.getHeader("Authorization");

        if (authorizationHeader != null){
            return authorizationHeader.replace("Bearer", "");
        }
        return null;
    }
}
