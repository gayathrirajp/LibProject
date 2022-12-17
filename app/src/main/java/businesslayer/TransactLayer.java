package businesslayer;

import android.util.Log;
import android.widget.Toast;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class TransactLayer {
   static ConSql c=new ConSql();
    static Connection connection=c.conClass();
    public static ResultSet studentInfo(String usn){
        if(connection!=null){
        try{
            String getStudentInfo="select USN, nAME, batch, branchid from student where usn='"+usn+"'";

            Statement smt=connection.createStatement();
            ResultSet set=smt.executeQuery(getStudentInfo);
            return set;
        } catch (SQLException e) {
            Log.e("error",e.getMessage());
        }}
        else{
           //put a toast;
        }
        return null;
    }

    public static ResultSet issueBook(String usn,String bookId,String staffId) throws SQLException {
        if(c!=null){
            String issueBook="exec spIssueBook ?, ? ,?";
            PreparedStatement ps=connection.prepareStatement(issueBook);
            ps.setEscapeProcessing(true);
            ps.setQueryTimeout(100);
            ps.setString(1,usn);
            ps.setString(2,bookId);
            ps.setString(3,staffId);
            ResultSet rs=ps.executeQuery();
            return rs;



        }
        else
        {
            //toast
        }
return null;
    }

}
