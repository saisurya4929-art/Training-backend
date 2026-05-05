package com.Trainingbackend.security;

import java.io.IOException;
import java.util.Collections;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

@Component
public class JwtFilter extends OncePerRequestFilter {

	@Autowired
	private JwtUtil jwtUtil;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {

		String path = request.getRequestURI();

		if (request.getMethod().equalsIgnoreCase("OPTIONS")) {
			filterChain.doFilter(request, response);
			return;
		}

		if (path.equals("/api/login") || path.equals("/api/register") || path.startsWith("/api/password/")
				|| path.startsWith("/api/courses") || path.startsWith("/api/blogs")
				|| path.startsWith("/api/placements") || path.startsWith("/api/gallery")
				|| path.startsWith("/api/enrollments") || path.startsWith("/api/certificates")
				|| path.startsWith("/api/reviews") || path.startsWith("/api/upload") || path.startsWith("/uploads/")
				|| path.startsWith("/student-uploads/")) {

			filterChain.doFilter(request, response);
			return;
		}

		String authHeader = request.getHeader("Authorization");

		if (authHeader == null || !authHeader.startsWith("Bearer ")) {
			response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
			response.getWriter().write("Missing token");
			return;
		}

		String token = authHeader.substring(7);

		try {
			jwtUtil.isTokenValid(token);

			String email = jwtUtil.extractEmail(token);
			String role = jwtUtil.extractRole(token);

			UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(email, null,
					Collections.singletonList(new SimpleGrantedAuthority(role)));

			SecurityContextHolder.getContext().setAuthentication(authentication);

			filterChain.doFilter(request, response);

		} catch (Exception e) {
			response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
			response.getWriter().write("Invalid token");
		}
	}
}