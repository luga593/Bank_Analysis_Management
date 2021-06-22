package servlets;

import dao.UserDAO;
import model.User;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
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
        loggedin = null;
    }




    public void doPost (HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        String userName = request.getParameter("uname");
        String password = request.getParameter("password");


        HttpSession session = request.getSession(false);
        if (session != null) {

            session.setAttribute("name", userName);
        }

        RequestDispatcher rd;

        if (UserDAO.validate(userName, password)) {
            loggedin = new User(userName, password);


            rd = request.getRequestDispatcher("mainPage.jsp");
            try {
                rd.forward(request, response);
            } catch (ServletException | IOException e) {

                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        } else {
            rd = request.getRequestDispatcher("failedLogin.jsp");
            try {
                rd.include(request, response);

            } catch (ServletException | IOException e) {
                // TODO Auto-generated catch block

                e.printStackTrace();
            }
        }

    }
}