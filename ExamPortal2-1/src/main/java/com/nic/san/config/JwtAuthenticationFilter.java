
package com.nic.san.config;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.nic.san.model.CustomUserDetail;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

	@Autowired
	UserDetailsService userDetailsService;

	@Autowired
	JwtUtils jwtutil;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {

		// 1. Get Authorisation Header from Request

		final String requestTokenHeader = request.getHeader("Authorization");
		System.out.println("JwtAuthenticationFilter.doFilterInternal()" + requestTokenHeader);

		String username = null;
		String jwtToken = null;
		if (requestTokenHeader != null) {
			jwtToken = requestTokenHeader.substring(7);
			System.out.println("::" + jwtToken);
			username = jwtutil.extractUsername(jwtToken);

		} else { // invalid token
			System.out.println("Invalid Token not start with bearer");
		}
		if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
			final CustomUserDetail userDetails = (CustomUserDetail) this.userDetailsService
					.loadUserByUsername(username);
			if (this.jwtutil.validateToken(jwtToken, userDetails)) {
				UsernamePasswordAuthenticationToken usernamePasswordAuthenticationton = new UsernamePasswordAuthenticationToken(
						userDetails, null, userDetails.getAuthorities());
				usernamePasswordAuthenticationton.setDetails(new WebAuthenticationDetails(request));
				SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationton);
				// token is valid
			} else {
				System.out.println("JwtAuthenticationFilter.doFilterInternal()::invalid token");

			}

		}
		filterChain.doFilter(request, response);

	}
}
