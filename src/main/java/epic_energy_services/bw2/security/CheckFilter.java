package epic_energy_services.bw2.security;

import epic_energy_services.bw2.entities.User;
import epic_energy_services.bw2.exception.UnauthorizedException;
import epic_energy_services.bw2.services.UserService;
import epic_energy_services.bw2.tools.JWTTools;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.couchbase.CouchbaseProperties;
import org.springframework.boot.autoconfigure.neo4j.Neo4jProperties;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class CheckFilter extends OncePerRequestFilter {

    @Autowired
    private JWTTools jwtTools;

    @Autowired
    private UserService userService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        // ************** AUTENTICAZIONE **********

        String authHeader = request.getHeader("Authorization");

        if (authHeader == null || !authHeader.startsWith("Bearer "))
            throw new UnauthorizedException("Manca il token.");

        String accessToken = authHeader.replace("Bearer ", "");

        jwtTools.checkTokenValidity(accessToken);

        // ************ AUTORIZZAZIONE ************

        String userId = jwtTools.getIdFromToken(accessToken);

        User currentUser = this.userService.findById(Long.parseLong(userId));

        Authentication authentication = new UsernamePasswordAuthenticationToken(
                currentUser,
                null,
                currentUser.getAuthorities()
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);

        filterChain.doFilter(request, response);
    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) {
        return new AntPathMatcher().match("/auth/**", request.getServletPath());
    }
}
