package servlets;

import javax.servlet.RequestDispatcher;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.ws.rs.GET;
import java.io.IOException;

@WebServlet("/logout")
public class logOutServlet extends HttpServlet {

    public logOutServlet() {
        super();
    }

    @GET
    public void doGet(HttpServletRequest req, HttpServletResponse res) {

        HttpSession session = req.getSession(false);
        if (session != null) {
            session.removeAttribute("user");

            logInServlet.resetUser();

            String redirectURL = "testLogin.jsp";
            try {
                res.sendRedirect(redirectURL);
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }
}
