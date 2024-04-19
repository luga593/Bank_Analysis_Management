package servlets;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

//Class not being used at the moment
@WebServlet("/userInfo")
public class UserInfoServlet extends HttpServlet {

    public UserInfoServlet(){
        super();
    }
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int response = logInServlet.getUser().getUserID();
        PrintWriter pw = resp.getWriter();
        pw.println(response);

    }
}
