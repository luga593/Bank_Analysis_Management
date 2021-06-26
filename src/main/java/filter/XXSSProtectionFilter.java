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

@WebFilter(filterName = "XXSSProtectionFilter", urlPatterns = {"/*"})
public class XXSSProtectionFilter implements Filter {
	
	private static final Logger log = LoggerFactory.getLogger(XXSSProtectionFilter.class);

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse,
                         FilterChain filterChain) throws IOException, ServletException {
        log.info("X-XSS-Protection header added to response");

        HttpServletResponse response = (HttpServletResponse) servletResponse;
        response.setHeader("X-XSS-Protection", "1; mode=block");

        filterChain.doFilter(servletRequest, response);
    }

    @Override
    public void init(FilterConfig filterConfig) {
    }

    @Override
    public void destroy() {
    }

}
