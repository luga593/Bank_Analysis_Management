package servlets;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/getGraph")
public class getGraphServlet extends HttpServlet {
    private double currentBalance;
    private String month;
    private String dayMonth;

    public double getCurrentbalance(){
        return currentBalance;
    }

    public void setCurrentbalance(double currentBalance) {
        this.currentBalance = currentBalance;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public String getDayMonth() {
        return dayMonth;
    }

    public void setDayMonth(String dayMonth) {
        this.dayMonth = dayMonth;
    }

    public void doPost (HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        response.setContentType("text/html");

        double currentbalance = Double.parseDouble(request.getParameter("balance"));
        String Month = request.getParameter("month");
        String dayMonth = request.getParameter("day-month");



    }
}
