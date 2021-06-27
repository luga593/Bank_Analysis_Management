package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import util.SimonsGraph;

/**
 * Servlet implementation class AmountDateServlet
 */
@WebServlet("/AmountDateServlet")
public class AmountDateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AmountDateServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("hello");
		SimonsGraph newggraph = new SimonsGraph();
		List<List<String>> result = new ArrayList<List<String>>();
		String result2 = "";
		//try {
			//result = newggraph.getqueryResult();
	//	} catch (SQLException e) {
			// TODO Auto-generated catch block
	//		e.printStackTrace();
	//	}
		PrintWriter pw = response.getWriter();
		response.setContentType("text/plain");

		for (int i = 0; i < result.size(); i++) {
			for (int j = 0; j < result.get(i).size(); j++) {
				if (j == result.get(i).size() - 1) {
					result2 = result2 + "/";
				} else {
					result2 = result2 + ",";
				}

			}
		}
		pw.write(result2);

		pw.close();
		response.setContentType("text/plain");

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
