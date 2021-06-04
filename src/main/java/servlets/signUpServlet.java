package servlets;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.GET;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

@WebServlet("/signup")
public class signUpServlet extends HttpServlet {

    public signUpServlet() {
        super();
    }

    @GET
    public void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
        String host = "bronto.ewi.utwente.nl";
        String dbname = "dab_di20212b_7";
        String jdbcURL = "jdbc:postgresql://" + host + ":5432/" + dbname + "?dab_di20212b_7";
        String dbUser = "dab_di20212b_7";
        String dbPass = "WHT7j8rVmsTZfH70";


        res.setContentType("text/html");
        String destinationPath = "signUp.jsp"; //redirects user initially to the sign up page.
        RequestDispatcher rd = req.getRequestDispatcher(destinationPath);
        rd.forward(req, res);


        /////////////////////////////////////////////////////////
        //login table attributes for the database
        String username = req.getParameter(""); //Username placeholder in text field
        String password = req.getParameter(""); //Password placeholder in text field
        String rPassword = req.getParameter("" ); //Repeated Password placeholder in text field
        String email = req.getParameter(""); //Email placeholder in text field

        //PersonalInfo table attributes for the database
        String fname = req.getParameter(""); //first name placeholder in text field
        String sname = req.getParameter(""); //second name placeholder in text field
        String lname = req.getParameter(""); //last name placeholder in text field
        //personid in login will be referenced here

        PrintWriter out = res.getWriter();
        int i;
        int j;

        try {
            Class.forName("com.postgresql.jdbc.Driver");
            Connection connection = DriverManager.getConnection(jdbcURL, dbUser, dbPass);

            PreparedStatement ps = connection.prepareStatement("INSERT INTO login(email, username, password) VALUES (?,?,?)");

            ps.setString(1, email);
            ps.setString(2, username);
            ps.setString(3, password);

            while (password != rPassword) {
                out.print("Passwords do not match, please try again ");
            }

            j = ps.executeUpdate();
            if (j == 1){
                out.print("Successful, your information has been stored in our database");
            } else if (j == 0){
                out.print("Something went wrong, please try again later");
            }




            PreparedStatement ps2 = connection.prepareStatement("SELECT personid FROM login WHERE username = (?) AND password = (?) ");
            ps2.setString(1, username);
            ps2.setString(2, password);
            ResultSet rs2 = ps2.executeQuery();

            PreparedStatement ps3 = connection.prepareStatement("INSERT INTO PersonalInfo(firstname, secondname, lastname, fk_personid) VALUES (?,?,?,?)");
            ps3.setString(1, fname);
            ps3.setString(2, sname);
            ps3.setString(3, lname);
            ps3.setString(4, String.valueOf(rs2));


            i = ps3.executeUpdate();
            if (i == 1){
                out.print("Successful, your information has been stored in our database");
            } else if (i == 0){
                out.print("Something went wrong, please try again later");
            }
            destinationPath = ""; //redirects user to the login page with their new credentials
            RequestDispatcher rd2= req.getRequestDispatcher(destinationPath);
            rd2.forward(req, res);



        } catch (ClassNotFoundException | SQLException | ServletException e) {
            e.printStackTrace();
        }

        out.close();


    }
}
