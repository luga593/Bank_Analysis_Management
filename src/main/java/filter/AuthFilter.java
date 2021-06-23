package filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebFilter(filterName="AuthFilter", urlPatterns={"*.jsp"})
public class AuthFilter implements Filter{
	
	public AuthFilter() {
    }
	
    public void destroy() {
    }

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
        HttpSession session = req.getSession();
		String uname = null;
		
        if (session != null) {
            uname = (String) session.getAttribute("uname");
        }
		
        boolean isLoggedIn = (uname != null);
        // Check if the user is accessing login page
        if (req.getRequestURI().equals(
                req.getContextPath() + "/testLogin.jsp") || 
        	req.getRequestURI().equals(
                        req.getContextPath() + "/newMainPage.jsp")) {
            if (isLoggedIn) {
                // Redirect to landing or home page
                HttpServletResponse res = (HttpServletResponse) response;
                res.sendRedirect(req.getContextPath()
                        + "/mainPage.jsp");
            } else {
                // Otherwise, nothing to do if he has not logged in
                // pass the request along the filter chain
                chain.doFilter(request, response);
            }
        } else {
            // For all other pages,
            if (isLoggedIn) {
                // Nothing to do
                chain.doFilter(request, response);
            } else {
                // Redirect to login page if he has not logged in
                HttpServletResponse res = (HttpServletResponse) response;
                res.sendRedirect(req.getContextPath() + "/newMainPage.jsp");
                // Nothing to do
            	//chain.doFilter(request, response);
            }
        }
		
	}
	
	public void init(FilterConfig fConfig) throws ServletException {
    }
}

