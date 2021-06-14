package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

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
		String sql ="Select f.fileid, xmlelement(Name transactionFile , xmlattributes(accid , date , startingamount , closingammount , closingdate), xmlagg( \r\n"
				+ "xmlelement(Name process, xmlattributes(p.iban, transactionammount, valuedate, entrydate))))\r\n"
				+ "from transactionfile f, process p \r\n"
				+ "where f.fileid = p.fileid \r\n"
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
	public static void main(String args[]) {
		TableDAO dao =  new TableDAO();
		dao.selectData();
	}
}
