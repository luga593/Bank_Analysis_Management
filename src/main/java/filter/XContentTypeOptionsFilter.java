package filter;

//import com.sun.org.slf4j.internal.Logger;
//import com.sun.org.slf4j.internal.LoggerFactory;

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

@WebFilter(filterName = "XContentTypeOptionsFilter", urlPatterns = {"/upload.jsp", "/UploadFail.jsp", "/UploadInvalidName.jsp"})
public class XContentTypeOptionsFilter implements Filter {
	private static final Logger log = LoggerFactory.getLogger(XContentTypeOptionsFilter.class);
	
	@Override
	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse,
	                 FilterChain filterChain) throws IOException, ServletException {
		log.info("X-Content-Type-Options header added to response");
		
		HttpServletResponse response = (HttpServletResponse) servletResponse;
		response.setContentType("text/plain");
		response.addHeader("X-Content-Type-Options", "nosniff");
		
		filterChain.doFilter(servletRequest, response);
	}
	
	@Override
	public void init(FilterConfig filterConfig) {
	}
	
	@Override
	public void destroy() {
	}
}
