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

/**
 *Servlet class to authenticate users to the website.
 * @author Mohammed Walee
 * @author Luis Hinojosa (maintainer)
 */
@WebServlet("/login")
public class logInServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private static User loggedin;


    /**
     * Constructs a new instance of the {@code logInServlet} class.
     * This constructor initializes the servlet by calling the constructor of its superclass, {@link HttpServlet}.
     */
    public logInServlet() {
        super();
    }

    /**
     * exposes the loggedin attribute.
     * @return the User object contained in said attribute.
     */
    public static User getUser() {
        return loggedin;
    }

    /**
     * Resets the currently logged-in user by setting their user ID, username, password, and email to null,
     * effectively logging them out.
     */
    public static void resetUser() {
        loggedin.setUserID(-1);
        loggedin.setUsername(null);
        loggedin.setPassword(null);
        loggedin.setEmail(null);
        loggedin = null;
    }

    /**
     * Handles HTTP GET requests. It manages the content of the page to be displayed to the user when
     * opening the login page. This method is mostly to be used for as part of the POST
     * form authentication.
     * if the validation fails, the user is displayed the content of the failedLogin file.
     * @param request  the HttpServletRequest object containing the request parameters
     * @param response the HttpServletResponse object used to send the response to the client
     * @throws IOException if an input or output exception occurs while handling the request
     */
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html; charset = UTF-8");

        HttpSession session = request.getSession();
        String userName = request.getParameter("uname");
        String password = request.getParameter("password");
        System.out.println("\u001B[username: " + userName + ", password: " + password + "\u001B[0m");

        //if user data is validated successfully, a User object is created and assigned to the DAO's properties
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
        } else { //if validation fails, redirects to the failedLogin file
            response.sendRedirect("failedLogin.jsp");
        }

    }

    /**
     * This method is responsible for authenticating user login attempts, when the form is submmitted.
     * It validates the data through the GET method, and creating session cookies upon successful login.
     * If the login credentials are valid, the user is redirected to the mainPage.jsp; otherwise,
     * the user is redirected to the failedLogin.jsp page.
     *
     * @param request
     * @param response
     * @throws IOException
     */
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