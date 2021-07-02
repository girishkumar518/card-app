package com.sapient.card.app.filters;
/*
 *  Created by Girish Kumar CH on 7/2/21
 */

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Enumeration;

@Configuration
@Slf4j
public class RequestLoggingFilter  extends OncePerRequestFilter {
    /**
     * Same contract as for {@code doFilter}, but guaranteed to be
     * just invoked once per request within a single request thread.
     * See {@link #shouldNotFilterAsyncDispatch()} for details.
     * <p>Provides HttpServletRequest and HttpServletResponse arguments instead of the
     * default ServletRequest and ServletResponse ones.
     *
     * @param request
     * @param response
     * @param filterChain
     */
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        // Keep track of the original request start time for logging.
        LocalDateTime startTime = LocalDateTime.now();
        logRequest(startTime, request);

        filterChain.doFilter(request, response);
    }

    private void logRequest(LocalDateTime date, HttpServletRequest request) {

        StringBuilder msg = new StringBuilder();
        // Output the request time.
        request.setAttribute("STARTTIME", System.currentTimeMillis());
        msg.append(" httpMethod=").append(request.getMethod());
        msg.append(" httpUrl=").append(request.getRequestURL());
        msg.append(" locale=").append(request.getLocale());
        msg.append(" Requested ipAddress=").append(request.getRemoteAddr());

        // Output request URL and query parameters
        if (null != request.getQueryString() && !"".equals(request.getQueryString().trim())) {
            msg.append("?").append(request.getQueryString());
        }
        msg.append("\"");
        // Output request header information.
        msg.append(" Headers[");
        Enumeration<String> headerNames = request.getHeaderNames();
        while (headerNames.hasMoreElements()) {
            String header = headerNames.nextElement();
            msg.append(header).append("=\"").append(request.getHeader(header)).append("\"");
            msg.append(headerNames.hasMoreElements() ? " " : "]");
        }
        log.info(msg.toString());
    }

}
