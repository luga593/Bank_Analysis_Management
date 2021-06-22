package util;
import java.sql.*;
import java.util.ArrayList;

import javax.ws.rs.core.Application;

public class ShowChartsLuis {

    //this is being made static, so we might aswell declare it once
    DbUtil DB = new DbUtil();
    Connection connection =  DB.getConnection();
    //Queries have to be implemented as STORED PROCEDURES
    private static final String QUERY = "Select  COUNT (DISTINCT p.*) FROM transactionFile t, process p\n" +
            "Where t.fileid = p.fileid AND t.accid LIKE ?";

    private static final String QUERY1 = "SELECT  COUNT(DISTINCT p.*) FROM TransactionFile t, process p\n" +
            "Where t.fileid = p.fileid AND t.accid LIKE ? AND p.iban is NULL AND p.description is NOT NULL;\n";

    private static final String QUERY2 = "SELECT  COUNT(DISTINCT p.*) FROM TransactionFile t, process p\n" +
            "Where t.fileid = p.fileid AND t.accid LIKE ? AND p.description is NULL AND iban is NOT NULL;\n";
    //transactions with IBAN AND DESCRIPTION NULL
    public static final String QUERY4 = "SELECT  COUNT(DISTINCT p.*) FROM TransactionFile t, process p\n" +
            "Where t.fileid = p.fileid AND t.accid LIKE ? AND p.iban is NULL And p.description is NULL;";

    private static final String QUERY3 = "SELECT  COUNT(DISTINCT p.*) FROM TransactionFile t, process p\n" +
            "Where p.fileid= t.fileid AND t.accid LIKE ? AND p.processid NOT IN(SELECT DISTINCT p.processid FROM TransactionFile t, process \n" +
            "  WHERE t.fileid = p.fileid AND t.accid LIKE ? AND (p.description is NULL OR p.iban \n" +
            "  is NULL ));\n";


    /**
     * non matching iban query pending
     */
    public String[] getStats(String iban) throws SQLException{
        return new String[]{getNoRecipient(iban), getNoIbanNoDesc(iban),getNoDescription(iban), getGoodProcesses(iban),
        getProcesses(iban)};
    };

    //puts all of the id's into a list and returns it
    public ArrayList<String> getProcesses(int fileid) throws SQLException {
        ArrayList<String> result = new ArrayList<String>();

        PreparedStatement st = connection.prepareStatement(QUERY);
        st.setLong(1,fileid);
        ResultSet resultSet = st.executeQuery();

        while(resultSet.next()){
            result.add(resultSet.getString(12));
        }
        return result;
    };
    public String getProcesses(String iban) throws SQLException {
        String result = null;

        PreparedStatement st = connection.prepareStatement(QUERY);
        st.setString(1,iban + "%");
        ResultSet resultSet = st.executeQuery();

        resultSet.next();
        result = resultSet.getString(1);
        return result;
    };
    //returns the number of processes with no recipient iban. this processes belong to all files of a given account<param>
    public String getNoRecipient(String iban) throws SQLException{
        String result = null;
        PreparedStatement st = connection.prepareStatement(QUERY1);
        st.setString(1,iban + "%");
        ResultSet resultSet = st.executeQuery();

        resultSet.next();
        result = resultSet.getString(1);
        return result;
    }

    public String getNoDescription(String iban) throws SQLException{
        String result = null;
        PreparedStatement st = connection.prepareStatement(QUERY2);
        st.setString(1,iban + "%");
        ResultSet resultSet = st.executeQuery();

        resultSet.next();
        result = resultSet.getString(1);
        return result;
    }
    public String getNoIbanNoDesc(String iban) throws SQLException{
        String result = null;
        PreparedStatement st = connection.prepareStatement(QUERY4);
        st.setString(1,iban + "%");
        ResultSet resultSet = st.executeQuery();
        resultSet.next();

        result = resultSet.getString(1);
        return result;
    };
    public String getGoodProcesses(String iban) throws SQLException{
        String result = null;
        PreparedStatement st = connection.prepareStatement(QUERY3);
        st.setString(1,iban + "%");
        st.setString(2,iban + "%");
        ResultSet resultSet = st.executeQuery();

        resultSet.next();
        result = resultSet.getString(1);
        return result;
    }



    public static void main(String args[]) throws SQLException {
        ShowChartsLuis sh1 = new ShowChartsLuis();

        System.out.println(sh1.getProcesses("NL34RABO0327101691").toString());
        System.out.println(sh1.getGoodProcesses("NL34RABO0327101691").toString());
        System.out.println(sh1.getNoRecipient("NL34RABO0327101691").toString());
        System.out.println(sh1.getNoDescription("NL34RABO0327101691").toString());
        System.out.println(sh1.getNoIbanNoDesc("NL34RABO0327101691").toString());
/*
        DbUtil DB = new DbUtil();

        Connection connection = DB.getConnection(); //this is being made static, so we might aswell declare it once


        String query = "SELECT p.*, t.fileid FROM TransactionFile t, process p\r\n"
                + "WHERE p.fileid = t.fileid AND t.fileid = ?;"; //getting all the processes that match an ID

        String query1 = "SELECT COUNT(p.*) FROM TransactionFile t, process p\r\n" //getting count of processes with null iban (in a day statement mt940)
                + "WHERE p.fileid = t.fileid AND p.iban is NULL AND t.fileid = ?;";

        String query2 = "SELECT COUNT(p.*) FROM TransactionFile t, process p\r\n"
                + "WHERE p.fileid = t.fileid AND p.description is NULL AND t.fileid = ?;";

        //count how many have null description
        //--------

    try {
        PreparedStatement st = connection.prepareStatement(query);
        st.setLong(1, 286);
        ResultSet resultSet = st.executeQuery();

        int processCount = 0; //countss the process for a file ID
        while (resultSet.next()) {
            //System.out.println(resultSet.getString(4));
            processCount++;

        }
        System.out.println(processCount);

        //now check for the process that have a null recipient for your chart
        PreparedStatement st1 = connection.prepareStatement(query1);
        st1.setLong(1, 286);
        ResultSet resultSet1 = st1.executeQuery();
        resultSet1.next();
        System.out.println(resultSet1.getInt(1));


        //check for null description
        PreparedStatement st2 = connection.prepareStatement(query1);
        st2.setLong(1, 286);
        ResultSet resultSet2 = st2.executeQuery();
        resultSet2.next();
        System.out.println(resultSet2.getInt(1));

      }catch(SQLException e){
        System.out.println("something happened");
    }

 */
    }
}
