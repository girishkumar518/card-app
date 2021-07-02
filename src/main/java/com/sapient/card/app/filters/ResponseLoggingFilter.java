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
import java.util.Collection;

@Configuration
@Slf4j
public class ResponseLoggingFilter  extends OncePerRequestFilter {
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

        filterChain.doFilter(request, response);
        logResponse(request, response);

    }

    private void logResponse(HttpServletRequest request, HttpServletResponse response) throws IOException {
        StringBuilder msg = new StringBuilder();
        msg.append(" duration=").append(System.currentTimeMillis() - new Long(request.getAttribute("STARTTIME").toString()));
        try {
            // Output HTTP status code
            msg.append(" ResponseCode=").append(response.getStatus());
            msg.append(" httpMethod=").append(request.getMethod());
            msg.append(" httpUrl=").append(request.getRequestURL());

            if (null!= request.getQueryString() && !"".equals(request.getQueryString().trim())) {
                msg.append("?").append(request.getQueryString());
            }
            msg.append("\"");
            msg.append(" locale=").append(response.getLocale());
            // Output response Headers
            msg.append(" Headers[");
            Collection<String> headerNames = response.getHeaderNames();
            headerNames.stream().forEach(header ->
                    msg.append(header).append("=\"").append(response.getHeader(header)).append("\","));
            msg.replace(msg.length() - 1, msg.length(), "").append("]");

        } catch (Exception e) {
            msg.append(" \"Unable to log response due to the following error. ").append(e.getMessage()).append("\"");
        }
        log.info(msg.toString());
    }
}
