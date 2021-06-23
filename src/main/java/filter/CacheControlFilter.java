package filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@WebFilter(filterName = "CacheControlFilter", urlPatterns = {"/*"})
public class CacheControlFilter implements Filter {
    private static final Logger log = LoggerFactory.getLogger(CacheControlFilter.class);

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse,
                         FilterChain filterChain) throws IOException, ServletException {
        log.info("Cache-Control header added to response");

        HttpServletResponse response = (HttpServletResponse) servletResponse;
        response.addHeader("Cache-Control", "no-cache, must-revalidate, max-age=0, no-store");
        response.addDateHeader("Expires", -1);

        filterChain.doFilter(servletRequest, response);
    }

    @Override
    public void init(FilterConfig filterConfig) {
    }

    @Override
    public void destroy() {
    }
}