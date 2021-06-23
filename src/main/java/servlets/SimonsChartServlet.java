package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import util.ShowChartsLuis;
import util.SimonsGraph;

@WebServlet("/TestSimon")
public class SimonsChartServlet extends HttpServlet {

	    /**Writes the number of processes flagged for differenet reasons separated by coma. the last value is the total number
	     * of files
	     */
	    @Override
	    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException{
	        SimonsGraph chartdata = new SimonsGraph();
	        PrintWriter pw = response.getWriter();
	        response.setContentType("text/plain");
	        List<List<String>> result = new ArrayList<List<String>>();
	        try {
				result = chartdata.getqueryResult();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	        for(int i = 0;i<result.size();i++) {
				 for(int j=0;j<result.get(i).size();j++) {
					 pw.write(result.get(i).get(j) + ",");
			    }
			 }
			   
			pw.close();
	        response.setContentType("text/plain");

	    }


	}
