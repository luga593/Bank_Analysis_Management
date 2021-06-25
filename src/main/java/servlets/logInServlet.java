package servlets;

import dao.UserDAO;
import model.User;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/login")
public class logInServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private static User loggedin;



    public logInServlet() {
        super();
    }


    public static User getUser() {
        return loggedin;
    }

    public static void resetUser() {
    	loggedin.setUserID(-1);
    	loggedin.setUsername(null);
    	loggedin.setPassword(null);
    	loggedin.setEmail(null);
        loggedin = null;
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
    	response.setContentType("text/html; charset = UTF-8");
    	PrintWriter out = response.getWriter();
    	HttpSession session = request.getSession();
        String userName = request.getParameter("uname");
        String password = request.getParameter("password"); 
        
    	if (UserDAO.validate(userName, password)) {
        	loggedin = new User(userName, password);
        	loggedin.setUserID(UserDAO.verifyCookie(userName));
        	request.getSession().setAttribute("uname", userName);            	
        	//Create session Cookie    			
			Cookie cookie = new Cookie(userName, session.getId());
			cookie.setMaxAge(60 * 30);
			cookie.setPath("/");
			cookie.setSecure(true);
			cookie.setHttpOnly(true);
			response.addCookie(cookie);
			response.sendRedirect("mainPage.jsp");
        } else {
        	response.sendRedirect("failedLogin.jsp");
        }

    }
    
    public void doPost (HttpServletRequest request, HttpServletResponse response)
            throws IOException {
    	HttpSession session = request.getSession();
    	UserDAO userDao = new UserDAO();
        String uname = (String) session.getAttribute("uname");
    	if (userDao.verifyCookie(uname) == -1) {
    		doGet(request, response);
    	} else {
        	response.sendRedirect("mainPage.jsp");
        }   
    }
}