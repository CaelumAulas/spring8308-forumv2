package br.com.alura.forum.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import br.com.alura.forum.security.controller.jwt.TokenManager;
import br.com.alura.forum.security.service.UserService;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {
	@Autowired
	private TokenManager tokenManager;
	@Autowired
	private UserService userService;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {		
		
		String token = getTokenFromRequest(request);
		
		if (tokenManager.isValid(token)) {
			Long id = tokenManager.getUserId(token);
			
			UserDetails userDetails = userService.loadUserById(id);
			
			Authentication authentication = 
					new UsernamePasswordAuthenticationToken(userDetails,
							null, userDetails.getAuthorities());
			SecurityContextHolder.getContext().setAuthentication(authentication);
		}
		
		filterChain.doFilter(request, response);
		
	}

	private String getTokenFromRequest(HttpServletRequest request) {
		String header = request.getHeader("Authorization");

		if (StringUtils.hasText(header) && header.startsWith("Bearer ")) {
			return header.substring(7);
		}
		
		return null;
	}

}
