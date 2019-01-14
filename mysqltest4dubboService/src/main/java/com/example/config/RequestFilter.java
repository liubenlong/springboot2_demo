package com.example.config;

import org.slf4j.MDC;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import java.io.IOException;
import java.util.UUID;

@Component
public class RequestFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        try {
            // Setup MDC data:
            String mdcData = String.format("[requestId:%s] ", UUID.randomUUID().toString());
            MDC.put("mdcData", mdcData); //Variable 'mdcData' is referenced in Spring Boot's logging.pattern.level property
            chain.doFilter(request, response);
        } finally {
            // Tear down MDC data:
            // ( Important! Cleans up the ThreadLocal data again )
            MDC.clear();
        }
    }
}