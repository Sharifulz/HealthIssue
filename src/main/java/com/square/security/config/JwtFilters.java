package com.square.security.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.square.security.service.MyUserDetailsService;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class JwtFilters extends OncePerRequestFilter {
	@Autowired
    MyUserDetailsService myUserDetailService;
    @Autowired
    JwtUtil jwtUtil;
    
    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException{
        String authHeader=httpServletRequest.getHeader("Authorization");
        if(authHeader!=null && authHeader.startsWith("Bearer ")){
            String jwt=authHeader.substring(7);
            String userName=jwtUtil.getUsernameFromToken(jwt);
           SecurityContext ss= SecurityContextHolder.getContext();

            if(userName !=null && SecurityContextHolder.getContext().getAuthentication()==null){
                UserDetails userDetails=myUserDetailService.loadUserByUsername(userName);

                if(jwtUtil.validateToken(jwt,userDetails)){
                    UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken=new UsernamePasswordAuthenticationToken(
                            userDetails,null,userDetails.getAuthorities()
                    );
                    usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(httpServletRequest));
                    SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
                }


            }
        }
        filterChain.doFilter(httpServletRequest,httpServletResponse);
    }
}
