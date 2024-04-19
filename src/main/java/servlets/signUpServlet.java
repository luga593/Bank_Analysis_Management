
package servlets;

import dao.UserDAO;
import util.DbUtil;
import util.PasswordUtil;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.POST;
import javax.ws.rs.GET;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

/**
 * Servlet class to register new users to the website.
 * As it is, the class has just been updated so it can provide the minimum functionality.
 * Hence it does not use modularity as the LoginServlet does
 *
 * @author Group 7 of the Data & information project.
 * @author Luis Hinojosa (maintainer)
 */

@WebServlet("/signup")
public class signUpServlet extends HttpServlet {

    public signUpServlet() {
        super();
    }

    /**
     * Handles the HTTP GET request to display the login form.
     * This method forwards the request to the JSP page containing the login form.
     *
     * @param req the HTTP servlet request
     * @param resp the HTTP servlet response
     * @throws ServletException if the servlet encounters a servlet-specific problem
     * @throws IOException if an I/O error occurs
     */
    @GET
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String destinationPath = "testLogin.jsp";
        RequestDispatcher rd = req.getRequestDispatcher(destinationPath);
        rd.forward(req, resp);
    }

    /**
     * Handles the HTTP POST request for registering a new user.
     * This method receives the sign up (register) form, validates it and writes it in the database if valid.
     * When there is a register error or the data is invalid, the user is simply redirected to failiedLogin.jsp
     *
     * @param req the HTTP servlet request containing user signup data
     * @param res the HTTP servlet response used to send responses back to the client
     * @throws IOException if an I/O error occurs while handling the request or response
     * @throws ServletException if the servlet encounters a servlet-specific problem
     */
    @POST
    public void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
        String host = "ep-quiet-forest-a2nrydr0.eu-central-1.aws.neon.tech";
        String dbName = "bank backEnd";
        String dbUser = "bank backEnd_owner";
        String dbPass = "6og2ihEuNeVY";
        String sslMode = "require";
        String jdbcURL = "jdbc:postgresql://" + host + "/" + dbName + "?user=" + dbUser + "&password=" + dbPass + "&sslmode=" + sslMode;

        //login table attributes for the database
        String username = req.getParameter("signup-username"); // Username placeholder in text field
        String password = req.getParameter("signup-password"); // Password placeholder in text field
        String rPassword = req.getParameter("signup-password-confirm"); // Repeated Password placeholder in text field
        String email = req.getParameter("signup-email"); // Email placeholder in text field

// PersonalInfo table attributes for the database
        String fname = req.getParameter("signup-fname"); // First name placeholder in text field
        String lname = req.getParameter("signup-lname"); // Last name placeholder in text field

        System.out.println("Password: " + password);
        System.out.println("Repeated Password: " + rPassword);

        PrintWriter out = res.getWriter();

        //fails the log in if passwords do not match or the user & password already exist
        if (!password.equals(rPassword) || (UserDAO.validate(username, password))) {
            res.sendRedirect("failedLogin.jsp");
            return;
        }

        int i;
        int j;
        //securing password
        password = PasswordUtil.encrypt(password);
        try {
            Class.forName("org.postgresql.Driver");
            Connection connection = new DbUtil().getConnection();
            //Connection connection = DriverManager.getConnection(jdbcURL, dbUser, dbPass);
            //try to write the new user's data into the login table
            PreparedStatement ps = connection.prepareStatement("INSERT INTO login(email, username, password) VALUES (?,?,?)");
            ps.setString(1, email);
            ps.setString(2, username);
            ps.setString(3, password);
            j = ps.executeUpdate();

               if (j == 1){
                    //retrieven personid (auto)
                   PreparedStatement ps2 = connection.prepareStatement("SELECT personid FROM login WHERE username = (?) AND password = (?) ");

                   ps2.setString(1, username);
                   ps2.setString(2, password);
                   ResultSet rs2 = ps2.executeQuery();

                   // upload the user's personal info to the PersonalInfo table, using personID to link the 2 tables
                   if (rs2.next()) {
                       int personId = rs2.getInt("personid");
                       //if first insert was sucessful, then we try the second one in the personalinfo table
                       PreparedStatement ps3 = connection.prepareStatement("INSERT INTO PersonalInfo(firstname, lastname, personid) VALUES (?,?,?)");
                       ps3.setString(1, fname);
                       ps3.setString(2, lname);
                       ps3.setInt(3, personId);
                       i = ps3.executeUpdate();
                       // Send appropriate response based on the result of PersonalInfo insertion
                       if (i == 1) {
                           out.print("Successful, your information has been stored in our database");
                       } else {
                           out.print("Failed to store personal information, please try again later");
                       }
                   } else {
                       out.print("Failed to retrieve personid, please try again later");
                   }
               } else {
                   out.print("Failed to register user, please try again later");
                   res.sendRedirect("failedLogin.jsp");
               }

            String destinationPath = "newMainPage.jsp"; //redirects user to the login page with their new credentials
            RequestDispatcher rd2= req.getRequestDispatcher(destinationPath);
            rd2.forward(req, res);

            connection.close();
        } catch (ClassNotFoundException | SQLException | ServletException e) {
            e.printStackTrace();
            out.print("an error ocurred, please try again later");
            res.sendRedirect("failedLogin.jsp");
        }
        out.close();
    }
}
