package servlets;

import javax.servlet.RequestDispatcher;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.ws.rs.GET;
import java.io.IOException;

@WebServlet("/logout")
public class logOutServlet extends HttpServlet {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public logOutServlet() {
        super();
    }

    @GET
    public void doGet(HttpServletRequest req, HttpServletResponse res) {
    	HttpSession session = req.getSession(false);
                   
        // -- Reset current user
        logInServlet.resetUser();
        
        if (session != null) {      	
            
        	// -- Destroy cookie
            Cookie[] cookies = req.getCookies();
            Cookie cookie = null;
            for ( int i=0; i<cookies.length; i++) {

                  cookie = cookies[i];
                  cookie.setMaxAge(0);                  
            }
        	
            // -- Remove userName in session
            session.removeAttribute("uname");
            
        	// -- Remove session
         	session.invalidate();      	
        }
        
        // -- Redirect to login page
        String redirectURL = "testLogin.jsp";
        try {
            res.sendRedirect(redirectURL);
        } catch (IOException e) {
            e.printStackTrace();
        }        
    }
}
