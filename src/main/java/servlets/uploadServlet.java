package servlets;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import Parser.parser;

import org.apache.commons.io.IOUtils;

@WebServlet("/upload")
@MultipartConfig
public class uploadServlet extends HttpServlet{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

		//get the file chosen by the user
		Part filePart = request.getPart("fileToUpload");		
		if(filePart.getSubmittedFileName().endsWith(".940")){
			String fileName = filePart.getSubmittedFileName();
			InputStream fileContent = filePart.getInputStream();
			String result = IOUtils.toString(fileContent, StandardCharsets.UTF_8);
			parser parser = new parser(fileName);
	        parser.uploadToDatabase(result, fileName);
	     // [Debug]: should redirect to the table
	        // response.getOutputStream().println(result);
	        if (response.getStatus() == 200) {
	        	response.sendRedirect("Table.jsp");
	        }
		} else{
			//the file was not a mt940 file
			response.getOutputStream().println("<p>Please only upload mt940 files.</p>");
			}

	}		    
}
