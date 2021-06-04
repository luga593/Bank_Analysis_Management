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
import javax.ws.rs.POST;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/login")
public class logInServlet extends HttpServlet {
    public logInServlet() {
        super();
    }

    @POST
    public void doPost(HttpServletRequest req, HttpServletResponse res) {
        String username = req.getParameter("Enter username");
        String psw = req.getParameter("Enter password");

        UserDAO userDao = new UserDAO();

        try{
            User user = userDao.loginCheck(username, psw);
            String destinationPath = "logInPage.jsp";

            if (user != null) {
                HttpSession sessionCreation = req.getSession();
                sessionCreation.setAttribute("user", user);
                destinationPath = "mainPage.jsp";
            } else {
                String error = "Entered Credentials are invalid";
                req.setAttribute("Error: ", error);
            }

            RequestDispatcher rd = req.getRequestDispatcher(destinationPath);
            rd.forward(req, res);


        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
