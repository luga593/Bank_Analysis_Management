package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import util.RiskAnalysis;

/**
 * Servlet implementation class LoanDecisionServlet
 */
@WebServlet("/LoanDecisionServlet")
public class LoanDecisionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoanDecisionServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		RiskAnalysis risk = new RiskAnalysis();
		risk.setRequestedAmount(300);
		risk.setAverageAmount(700);
		risk.setMinimalAmount(450);
		risk.setNoOfTransactionsToDifferentIbans(1);
		risk.setNoOfTransactionWithNoDescription(1);
		risk.estimateRisk();
		response.getWriter().append(risk.getResponse());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
