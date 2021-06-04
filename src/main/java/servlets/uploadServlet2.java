package servlets;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

@WebServlet("/upload")
public class uploadServlet2 extends HttpServlet{
	
	/** The path to the folder where we want to store the uploaded files */
    private static final String UPLOAD_FOLDER = "c:/Topicus-UploadedFiles/";
    
	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

		//get the file chosen by the user
		Part filePart = request.getPart("fileToUpload");
		
		if(filePart.getSubmittedFileName().endsWith(".940")){
			 try {
		            createFolderIfNotExists(UPLOAD_FOLDER);
		        } catch (SecurityException se) {
		            se.printStackTrace();
		            response.getOutputStream().println("<p>Can not create destination folder on server.</p>");
		        }
		    InputStream fileInputStream = filePart.getInputStream();
		    File fileToSave = new File(UPLOAD_FOLDER + filePart.getSubmittedFileName());
			Files.copy(fileInputStream, fileToSave.toPath(), StandardCopyOption.REPLACE_EXISTING);
			
			String fileUrl = "http://localhost:8080/uploaded-files/" + filePart.getSubmittedFileName();			
			String name = request.getParameter("name");
			
			response.getOutputStream().println("<p>Thanks " + name + "! Here's the image you uploaded:</p>");
			response.getOutputStream().println("<mt940 file src=\"" + fileUrl + "\" />");
			//response.getOutputStream().println("<p>Upload another mt940 file <a href=\"http://localhost:8080/index.html\">here</a>.</p>");	
		}
		else{
			//the file was not a mt940 file
			response.getOutputStream().println("<p>Please only upload mt940 files.</p>");
			response.getOutputStream().println("<p>Upload another file <a href=\"http://localhost:8080/index.html\">here</a>.</p>");	
		}

	}
	
		    
		    /**
		     * Creates a folder to desired location if it not already exists
		     *
		     * @param dirName - full path to the folder
		     * @throws SecurityException - in case you don't have permission to create the
		     *                           folder
		     */
		    private void createFolderIfNotExists(String dirName) throws SecurityException {
		        File theDir = new File(dirName);
		        if (!theDir.exists()) {
		            theDir.mkdir();
		        }
		    }
		    
		    

}
