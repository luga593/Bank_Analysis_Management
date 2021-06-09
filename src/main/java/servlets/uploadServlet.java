package servlets;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.LinkedHashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import Parser.parser;
import dao.FileDAO;

@WebServlet("/upload")
@MultipartConfig
public class uploadServlet extends HttpServlet{

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
            response.getOutputStream().println("<p>" + "Here's " + filePart.getSubmittedFileName() + "you uploaded:</p>");
            //[Debug]------------------------
            response.getOutputStream().println(showInformation());
            //response.getOutputStream().println("<mt940 file src=\"" + fileUrl + "\" />");
            //response.getOutputStream().println("<p>Upload another mt940 file <a href=\"http://localhost:8080/index.html\">here</a>.</p>");
        }
        else{
            //the file was not a mt940 file
            response.getOutputStream().println("<p>Please only upload mt940 files.</p>");
            //response.getOutputStream().println("<p>Upload another file <a href=\"http://localhost:8080/index.html\">here</a>.</p>");
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

    /**
     * Retrieve data directly from uploaded file in String format
     * @param fileName
     * @return content of file in String
     */
    private String getUploadedContent(String fileName) {
        FileInputStream fis;
        String content = null;
        try {
            fis = new FileInputStream(fileName);
            byte[] buffer = new byte[10];
            StringBuilder sb = new StringBuilder();
            while (fis.read(buffer) != -1) {
                sb.append(new String(buffer));
                buffer = new byte[10];
            }
            fis.close();
            content = sb.toString();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return content;
    }

    public String getHistory() {
        File folder = new File(UPLOAD_FOLDER);
        File[] listOfFiles = folder.listFiles();
        String output = "";
        for (int i = 0; i < listOfFiles.length; i++) {
            if (listOfFiles[i].isFile()) {
                // System.out.println("File " + listOfFiles[i].getName());
                output = output + "File " + listOfFiles[i].getName() + "\n";
            } else if (listOfFiles[i].isDirectory()) {
                // System.out.println("Directory " + listOfFiles[i].getName());
                output = output + "Directory " + listOfFiles[i].getName() + "\n";
            }
        }
        return output;
    }

    public String showInformation() {
        String[] files = getHistory().split("File");
        String res = "";
        for (int i = 1; i < files.length; i++) {
            if (files[i] != " C\n") {
                // System.out.println(files[i]);
                files[i] = files[i].replace(" Sample ", "Sample ");
                files[i] = files[i].replace("\n", "");
                if (getUploadedContent(UPLOAD_FOLDER + files[i]) != null) {
                    parser parser = new parser(getUploadedContent(UPLOAD_FOLDER + files[i]));
                    parser.parseFile();
                    FileDAO fileDao =  new FileDAO();
                    fileDao.addFileDetails(parser);
                    for (int j = 0; j < parser.getContentSize(); j++) {
                        res += "SubFile " + String.valueOf(j + 1) + "\n";
                        res += "Reference number: " + parser.get20field(j) + "\n";
                        if (parser.getContent().get(j).containsKey("21")) {
                            res += "Related reference: " + parser.get21Field(j) + "\n";
                        }
                        res += "Account identification number: " + parser.get25field(j) + "\n";
                        // res+="Statement Number/Sequence Number " + parser.get28Field(j) +"\n";
                        res += "Opening Balance: \n";
                        for (String key : parser.get60field(j).keySet()) {
                            res += key + ": " + parser.get60field(j).get(key) + "\n";
                        }
                        for (int k = 0; k < parser.getContent().get(j).get(":61:").size(); k++) {
                            if (parser.get61field(j, k) != null) {
                                res += "Transaction " + String.valueOf(k + 1) + ":\n";
                                for (String key : parser.get61field(j, k).keySet()) {
                                    res += key + ": " + parser.get61field(j, k).get(key) + "\n";
                                }
                                for (String key : parser.get86field(j, k).keySet()) {
                                    res += key + ": " + parser.get86field(j, k).get(key) + "\n";
                                }
                            }
                        }
                        res+="\n";
                        if (parser.get62Ffield(j, 0)!= null) {
                            for (String key : parser.get62Ffield(j, 0).keySet()) {
                                res += key + ": " + parser.get62Ffield(j, 0).get(key) + "\n";
                            }
                        }
                        if (parser.getContent().get(j).containsKey(":64:") && parser.get64field(j, 0)!= null) {
                            for (String key : parser.get64field(j, 0).keySet()) {
                                res += key + ": " + parser.get64field(j, 0).get(key) + "\n";
                            }
                        }
                        if (parser.getContent().get(j).containsKey(":65:") && parser.get65field(j, 0)!= null) {
                            for (String key : parser.get65field(j, 0).keySet()) {
                                res += key + ": " + parser.get65field(j, 0).get(key) + "\n";
                            }
                        }
                        if (parser.get86finfield(j, parser.getContent().get(j).get(":86:").size()-1)!= null) {
                            for (String key : parser.get86finfield(j, parser.getContent().get(j).get(":86:").size()-1).keySet()) {
                                res += key + ": " + parser.get86finfield(j, parser.getContent().get(j).get(":86:").size()-1).get(key) + "\n";
                            }
                        }
                    }
                }
            }
        }
        return res;

    }
}
