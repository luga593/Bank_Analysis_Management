package Parser;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

import com.prowidesoftware.swift.model.mt.mt9xx.MT940;

import dao.FileDAO;
import dao.RequestDao;
import dao.UserDAO;

public class parser {
	

	public boolean uploadToDatabase(String mt,String filename) {
		//mt = content of the file
		// uploads the file content to the respective tables in the database.   ps there is some redundancy in the data

		boolean result = false;
		Long dateTime = System.currentTimeMillis();
		int index = 0;
		while (index != -1) {
			int previndex = index;
			String tmp = null;
			if (mt.contains("-}")) {
				index = mt.indexOf("-}", previndex + 1);
				if (index != -1) {
					tmp = mt.substring(previndex, index);
				}
			} else {
				tmp = "{4:" + mt;
				index = -1;
			}
			if (tmp != null) {
				tmp = tmp.replace(":28:", ":28C:");
				MT940 file = new MT940(tmp);
				FileDAO dao = new FileDAO();
				RequestDao dao1 = new RequestDao();
				dao1.addToDatabase(filename);
				result = dao.addFileDetails(file,tmp,dateTime,filename);
			}
		}
		return result;
	}
}
