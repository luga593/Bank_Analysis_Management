package servlets;

import util.ShowChartsLuis;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;


@WebServlet("/ChartTest")
public class ChartServlet extends HttpServlet {

    //writes the number of transactions associated with a file
    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException{
        ShowChartsLuis chartdata = new ShowChartsLuis();
        String reqParam = req.getParameter("iban");
        PrintWriter pw = res.getWriter();
        res.setContentType("text/plain");
        try {
            for(String x : chartdata.getStats(reqParam)){
                pw.write(x + ",");
            }
            pw.close();

        }catch(SQLException e){
            res.sendError(500);
        }
        res.setContentType("text/plain");

    }


}