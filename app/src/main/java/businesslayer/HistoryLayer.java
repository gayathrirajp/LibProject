package businesslayer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class HistoryLayer {
    static ConSql c=new ConSql();
    static Connection connection=c.conClass();
    public static ResultSet issueHistory() throws SQLException {
        if(connection!=null){
            String issueBook="exec spIssueHistory";
            PreparedStatement ps=connection.prepareStatement(issueBook);
            ps.setEscapeProcessing(true);
            ps.setQueryTimeout(100);
            ResultSet rs=ps.executeQuery();
            return rs;
        }
        else
        { //toast
        }
        return null;
    }
    public static ResultSet returnHistory() throws SQLException {
        if(connection!=null){
            String issueBook="exec spReturnHistory";
            PreparedStatement ps=connection.prepareStatement(issueBook);
            ps.setEscapeProcessing(true);
            ps.setQueryTimeout(100);
            ResultSet rs=ps.executeQuery();
            return rs;
        }
        else
        { //toast
        }
        return null;
    }
    public static ResultSet dueHistory() throws SQLException {
        if(connection!=null){
            String issueBook="exec spDueHistory";
            PreparedStatement ps=connection.prepareStatement(issueBook);
            ps.setEscapeProcessing(true);
            ps.setQueryTimeout(100);
            ResultSet rs=ps.executeQuery();
            return rs;
        }
        else
        { //toast
        }
        return null;
    }

}
