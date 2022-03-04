package com.nrjb.security;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@RequiredArgsConstructor
public class TokenFilter extends GenericFilter {

    private final TokenProvider tokenProvider;

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
      String token = tokenProvider.resolveToken((HttpServletRequest) request);

      if(token != null && tokenProvider.validateToken(token)) {
          Authentication authentication = tokenProvider.getAuthentication(token);
          SecurityContextHolder.getContext().setAuthentication(authentication);
      }
      chain.doFilter(request, response);
    }
}
