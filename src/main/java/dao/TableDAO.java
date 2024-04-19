package dao;

import java.sql.*;

import servlets.logInServlet;
import util.DbUtil;

public class TableDAO {
	private Connection connection;

	public TableDAO() {
		try {
            if (connection == null || connection.isClosed()) {
                connection = DbUtil.getConnection();
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
		
	}
	public String selectData() {
		String sql ="Select f.fileid, xmlelement(Name transactionFile , xmlattributes(accid , date , startingamount , closingammount , closingdate,filename,time), xmlagg( \r\n"
				+ "xmlelement(Name process, xmlattributes(p.iban, transactionammount, valuedate, entrydate))))\r\n"
				+ "from transactionfile f, request p \r\n"
				+ "where f.fileid = p.fileid and \r\n"
				+ "f.filename is not null \r\n"
				+ "group by f.fileid\r\n"
				+ "order by f.fileid";
		String xml = "";
		try {
			Statement st = connection.createStatement();
			ResultSet res = st.executeQuery(sql);
			while(res.next()) {
				xml  = res.getSQLXML(2).getString();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return xml;
		
	}
	
	public String selectData2() {
		String sql ="Select XMLELEMENT(NAME transactionFiles, XMLAGG(xml)) "
                + "FROM (SELECT xmlelement(Name transactionFile , "
                + "                xmlattributes(accid , date , ROUND(startingamount::numeric, 2) as startingAmount , ROUND(closingammount::numeric, 2) as closingammount , closingdate), "
                + "                xmlagg(xmlelement(Name process, "
                + "                        xmlattributes(p.iban, description, ROUND(transactionammount::numeric, 2) as transactionammount, valuedate, entrydate, p.creditdebit, p.partyname) "
                + "                                 ) "
                + "                       ) "
                + "                ) AS xml "
                + "      from transactionfile f, request p "
                + "      where f.fileid = p.fileid "
                + "      group by f.fileid "
                + "      order by f.fileid "
                + ") AS transactions;";
		String xml = null;
		try {
			Statement st = connection.createStatement();
			ResultSet res = st.executeQuery(sql);
			while(res.next()) {
				xml  = res.getSQLXML(1).getString();
				//System.out.println(xml);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return xml;		
	}
	public String selectData3() {
		//getting userid
		int currentUID = logInServlet.getUser().getUserID();

		String sql = "SELECT XMLELEMENT(NAME transactionFiles, XMLAGG(xml)) \r\n"
				+ "FROM (SELECT xmlelement(Name transactionFile ,  xmlattributes(accid , date , ROUND(startingamount::numeric, 2) as startingAmount , ROUND(closingammount::numeric, 2) as closingammount , closingdate), xmlagg(xmlelement(Name process, xmlattributes(p.iban, description, ROUND(transactionammount::numeric, 2) as transactionammount, valuedate, entrydate, p.creditdebit, p.partyname)  )  ) )"
				+ " AS xml FROM transactionfile f, request p, (SELECT t.filename as filename, max(t.time) as time FROM transactionfile t, (SELECT r.filename as filename, r.time as time FROM request r ) as lastRequest ,  (SELECT max(r.time) as time FROM request r) as l  \r\n"
				+ "WHERE lastrequest.time = l.time and t.filename = lastRequest.filename\r\n"
				+ "GROUP BY t.filename) as lastFile \r\n"
				+ "WHERE f.fileid = p.fileid and f.filename = lastFile.filename and f.time = lastFile.time AND f.userid = ?\r\n"
				+ "GROUP BY f.fileid)\r\n"
				+ " AS transactions;";

		String xml = null;
		try {
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setInt(1, currentUID);

			ResultSet res = ps.executeQuery();
			while (res.next()) {
				xml = res.getSQLXML(1).getString();
				// System.out.println(xml);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return xml;
	}
	public static void main(String args[]) {
		TableDAO dao =  new TableDAO();
		dao.selectData();
	}
}
