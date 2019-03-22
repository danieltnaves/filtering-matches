package net.spark.filteringservice.configuration;

import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class CORSConfiguration extends OncePerRequestFilter {
  @Override
  protected void doFilterInternal(
      HttpServletRequest httpServletRequest,
      HttpServletResponse httpServletResponse,
      FilterChain filterChain)
      throws ServletException, IOException {
    String origin = httpServletRequest.getHeader("origin");
    origin = (origin == null || origin.equals("")) ? "null" : origin;
    httpServletResponse.addHeader("Access-Control-Allow-Origin", origin);
    httpServletResponse.addHeader(
        "Access-Control-Allow-Methods", "POST, GET, PUT, UPDATE, DELETE, PATCH, HEAD, OPTIONS");
    httpServletResponse.addHeader("Access-Control-Allow-Credentials", "true");
    httpServletResponse.addHeader(
        "Access-Control-Allow-Headers",
        "Authorization, origin, client_id, access_token, content-type, accept, apikey, x-requested-with");
    filterChain.doFilter(httpServletRequest, httpServletResponse);
  }
}
