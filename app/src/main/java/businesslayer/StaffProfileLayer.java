package businesslayer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class StaffProfileLayer {
    static ConSql c=new ConSql();
    static Connection connection=c.conClass();
    public static ResultSet staffDetails(String staffId) throws SQLException {
        if(connection!=null){
            String issueBook="exec spstaffDetails ?";
            PreparedStatement ps=connection.prepareStatement(issueBook);
            ps.setEscapeProcessing(true);
            ps.setQueryTimeout(100);
            ps.setString(1,staffId);
            ResultSet rs=ps.executeQuery();
            return rs;}
        else
        { //toast
        }
        return null;
    }

    public static ResultSet staffChangePassword(String staffId,String staffPassword) throws SQLException {
        if(connection!=null){
            String issueBook="exec spStaffChangePassword ?,?";
            PreparedStatement ps=connection.prepareStatement(issueBook);
            ps.setEscapeProcessing(true);
            ps.setQueryTimeout(100);
            ps.setString(1,staffId);
            ps.setString(2,staffPassword);
            ResultSet rs=ps.executeQuery();
            return rs;
        }
        else
        { //toast
        }
        return null;
    }
}
