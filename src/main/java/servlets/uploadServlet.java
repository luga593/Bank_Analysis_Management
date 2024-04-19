package servlets;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.regex.Pattern;

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
			boolean isValidated = false;
			String fileName = filePart.getSubmittedFileName();
			isValidated = validateFileName(fileName);
			if (isValidated == false) {
				response.sendRedirect("UploadInvalidName.jsp");
			} else {
				InputStream fileContent = filePart.getInputStream();
				String result = IOUtils.toString(fileContent, StandardCharsets.UTF_8);
				parser parser = new parser();
				isValidated = parser.uploadToDatabase(result, fileName);
		        if (isValidated == true) {
		        	response.sendRedirect("TableSingular.jsp");
		        } else {
		        	response.sendRedirect("UploadFail.jsp");
		        }
		        // [Debug]: should redirect to the table
		        // response.getOutputStream().println(result);
			}				        
		} else{
			//the file was not a mt940 file
			response.sendRedirect("UploadFail.jsp");
			}

	}	

	public static boolean validateFileName(String fileName) {
		// Validate filename: prevent SQL Injection
		String pattern = "^[^'\"\\-#;|=]+$";
		boolean isMatch = Pattern.matches(pattern, fileName);
		System.out.println(isMatch);
		return isMatch;
	}
}
