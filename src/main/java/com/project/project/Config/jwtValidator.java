package com.project.project.Config;
import java.io.IOException;
import org.springframework.security.authentication.*;
import org.springframework.security.core.*;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import java.util.*;


public class jwtValidator extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request,HttpServletResponse response,FilterChain filterChain) throws ServletException, IOException {

        String jwt = request.getHeader(Constants.jwt_header);

        if(jwt!=null){
            try{
                String email = JWTProvider.getEmailFromJWTToken(jwt);

                List<GrantedAuthority> authorities = new ArrayList<>();

                Authentication authentication = new UsernamePasswordAuthenticationToken(email , null , authorities);

                SecurityContextHolder.getContext().setAuthentication(authentication);
            }

            catch(Exception e){
                throw new BadCredentialsException("Invalid Token");
            }
        }

        filterChain.doFilter(request, response);
    }
}