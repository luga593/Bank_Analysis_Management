package util;
import java.sql.*;
import java.util.ArrayList;

import javax.ws.rs.core.Application;

/**
 * @author Luis Hinojosa
 */
public class ShowChartsLuis {

    //this is being made static, so we might aswell declare it once
    DbUtil DB = new DbUtil();
    Connection connection =  DB.getConnection();


    private static final String QUERY = "SELECT COUNT(DISTINCT p.*) FROM request p, transactionfile t, \n" +
            "(Select t.filename as filename, max(t.time) as time from transactionfile t, \n" +
            "(Select r.filename as filename, r.time as time from request r ) as lastRequest ,  \n" +
            "(Select max(r.time) as time from request r) as l \n" +
            "where lastrequest.time = l.time and t.filename = lastRequest.filename group by t.filename) as lastFile\n" +
            "WHERE p.fileid = t.fileid AND t.userid = ? and  t.filename = lastFile.filename and t.time = lastFile.time";


    //only null iban
    private static final String QUERY1 = "SELECT COUNT(DISTINCT p.*) FROM request p, transactionfile t,\n" +
        "(Select t.filename as filename, max(t.time) as time from transactionfile t, \n" +
        "(Select r.filename as filename, r.time as time from request r ) as lastRequest,\n" +
        "(Select max(r.time) as time from request r) as l \n" +
        "where lastrequest.time = l.time and t.filename = lastRequest.filename group by t.filename) as lastFile\n" +
        "WHERE p.fileid = t.fileid AND t.userid = ? and  t.filename = lastFile.filename and t.time = lastFile.time \n" +
        "AND (p.iban <> '' IS NOT TRUE) AND NOT (p.description <> '' IS NOT TRUE);";


    //only null description
    private static final String QUERY2 = "SELECT COUNT(DISTINCT p.*) FROM request p, transactionfile t, (Select t.filename as filename, max(t.time) as time from transactionfile t, (Select r.filename as filename, r.time as time from request r ) as lastRequest ,  (Select max(r.time) as time from request r) as l \n" +
            "where lastrequest.time = l.time and t.filename = lastRequest.filename\n" +
            "group by t.filename) as lastFile\n" +
            "WHERE p.fileid = t.fileid AND t.userid = ? and  t.filename = lastFile.filename and t.time = lastFile.time \n" +
            "AND NOT (p.iban <> '' IS NOT TRUE) AND  (p.description <> '_' IS NOT TRUE);";

    //good processes for the last
    private static final String QUERY3 = "SELECT COUNT (DISTINCT p.*) FROM request p, transactionfile t, (Select t.filename as filename, max(t.time) as time from transactionfile t, (Select r.filename as filename, r.time as time from request r ) as lastRequest ,  (Select max(r.time) as time from request r) as l \n" +
            "where lastrequest.time = l.time and t.filename = lastRequest.filename\n" +
            "group by t.filename) as lastFile\n" +
            "WHERE p.fileid = t.fileid AND t.userid = ? and  t.filename = lastFile.filename and t.time = lastFile.time \n" +
            "AND p.requestid NOT IN\n" +
            "(SELECT  p.requestid FROM request p, transactionfile t,\n" +
            "(Select t.filename as filename, max(t.time) as time from transactionfile t, \n" +
            "(Select r.filename as filename, r.time as time from request r ) as lastRequest , \n" +
            "(Select max(r.time) as time from request r) as l \n" +
            "where lastrequest.time = l.time and t.filename = lastRequest.filename group by t.filename) as lastFile\n" +
            "WHERE p.fileid = t.fileid AND t.userid = ? and  t.filename = lastFile.filename and t.time = lastFile.time \n" +
            "AND ((p.iban <> '' IS NOT TRUE) OR (p.description <> '' IS NOT TRUE)))";

    //no iban no description
    public static final String QUERY4 = "SELECT COUNT(DISTINCT p.*) FROM request p, transactionfile t,\n" +
            "(Select t.filename as filename, max(t.time) as time from transactionfile t, \n" +
            "(Select r.filename as filename, r.time as time from request r ) as lastRequest,\n" +
            "(Select max(r.time) as time from request r) as l \n" +
            "where lastrequest.time = l.time and t.filename = lastRequest.filename group by t.filename) as lastFile\n" +
            "WHERE p.fileid = t.fileid AND t.userid = ? and  t.filename = lastFile.filename and t.time = lastFile.time \n" +
            "AND (p.iban <> '' IS NOT TRUE) AND (p.description <> '_' IS NOT TRUE);";


    public String[] getStats(int UserID) throws SQLException{
/*        String[] Array1 = {getNoRecipient(UserID), getNoIbanNoDesc(UserID),getNoDescription(UserID), getGoodProcesses(UserID),
                getProcesses(UserID)};
        for (String value : Array1) {
            System.out.println(value);
        }*/
       return new String[]{getNoRecipient(UserID), getNoIbanNoDesc(UserID),getNoDescription(UserID), getGoodProcesses(UserID),
        getProcesses(UserID)};

    };

    //puts all of the id's into a list and returns it
    /*public ArrayList<String> getProcesses(int fileid) throws SQLException {
        ArrayList<String> result = new ArrayList<String>();

        PreparedStatement st = connection.prepareStatement(QUERY);
        st.setLong(1,fileid);
        ResultSet resultSet = st.executeQuery();

        while(resultSet.next()){
            result.add(resultSet.getString(12));
        }
        return result;
    }
*/
    public String getProcesses(int userID) throws SQLException {
        String result = null;

        PreparedStatement st = connection.prepareStatement(QUERY);
        st.setInt(1,userID);
        ResultSet resultSet = st.executeQuery();

        resultSet.next();
        result = resultSet.getString(1);
        return result;
    }
    //returns the number of processes with no recipient iban. this processes belong to all files of a given account<param>
    public String getNoRecipient(int userID) throws SQLException{
        String result = null;
        PreparedStatement st = connection.prepareStatement(QUERY1);
        st.setInt(1,userID);
        ResultSet resultSet = st.executeQuery();

        resultSet.next();
        result = resultSet.getString(1);
        return result;
    }

    public String getNoDescription(int userID) throws SQLException{
        String result = null;
        PreparedStatement st = connection.prepareStatement(QUERY2);
        st.setInt(1,userID);
        ResultSet resultSet = st.executeQuery();

        resultSet.next();
        result = resultSet.getString(1);
        return result;
    }
    public String getNoIbanNoDesc(int iban) throws SQLException{
        String result = null;
        PreparedStatement st = connection.prepareStatement(QUERY4);
        st.setInt(1,iban );
        ResultSet resultSet = st.executeQuery();
        resultSet.next();

        result = resultSet.getString(1);
        return result;
    }
    public String getGoodProcesses(int iban) throws SQLException{
        String result = null;
        PreparedStatement st = connection.prepareStatement(QUERY3);
        st.setInt(1,iban );
        st.setInt(2,iban );
        ResultSet resultSet = st.executeQuery();

        resultSet.next();
        result = resultSet.getString(1);
        return result;
    }


    public static void main(int args[]) throws SQLException {
        ShowChartsLuis sh1 = new ShowChartsLuis();
        System.out.println(sh1.getProcesses(2));
        System.out.println(sh1.getGoodProcesses(2));
        System.out.println(sh1.getNoRecipient(2));
        System.out.println(sh1.getNoDescription(2));
        System.out.println(sh1.getNoIbanNoDesc(2));
        System.out.println(sh1.getStats(2));


    }
}
