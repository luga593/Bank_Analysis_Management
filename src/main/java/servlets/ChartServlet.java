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

    /**Writes the number of processes flagged for different reasons separated by coma. the last value is the total number
     * of files
     */
    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException{
        ShowChartsLuis chartdata = new ShowChartsLuis();
        int reqParam = Integer.parseInt(req.getParameter("uID"));
        PrintWriter pw = res.getWriter();
        res.setContentType("text/plain");
        String response = "";

        try {

            for(String x : chartdata.getStats(reqParam)){
            	response += x+",";
            }
            pw.write(response);
            System.out.println(response);
            pw.close();

        }catch(SQLException e){
            res.sendError(500);
        }
        res.setContentType("text/plain");

    }


}
