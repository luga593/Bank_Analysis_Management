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

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;



	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
		System.out.println("hello");
		SimonsGraph newggraph = new SimonsGraph();
		List<List<String>> result = new ArrayList<List<String>>();
		String result2 = "";
		try {
			result = newggraph.getqueryResult();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		PrintWriter pw = response.getWriter();
		response.setContentType("text/plain");

		for (int i = 0; i < result.size(); i++) {
			for (int j = 0; j < result.get(i).size(); j++) {
				if (j == result.get(i).size() - 1) {
					result2 = result2 + "/";
				} else {
					result2 = result2 + result.get(i).get(j)+",";
				}

			}
		}
		pw.write(result2);

		pw.close();
		response.setContentType("text/plain");

	}

}