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

  private static final String ACCESS_CONTROL_ALLOW_ORIGIN = "Access-Control-Allow-Origin";

  private static final String NULL = "null";

  private static final String ORIGIN = "origin";

  @Override
  protected void doFilterInternal(
      HttpServletRequest httpServletRequest,
      HttpServletResponse httpServletResponse,
      FilterChain filterChain)
      throws ServletException, IOException {
    String origin = httpServletRequest.getHeader(ORIGIN);
    origin = origin == null || origin.isEmpty() ? NULL : origin;
    httpServletResponse.addHeader(ACCESS_CONTROL_ALLOW_ORIGIN, origin);
    filterChain.doFilter(httpServletRequest, httpServletResponse);
  }
}
