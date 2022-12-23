package businesslayer;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.example.libraryproject.TransactActivity;

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
            }
        }
        else{
        }
        return null;
    }

    public static ResultSet issueBookDetails(String usn,String bookId) throws SQLException {
        if(connection!=null){
            String issueBook="exec spIssueBookDetails ?, ?";
            PreparedStatement ps=connection.prepareStatement(issueBook);
            ps.setEscapeProcessing(true);
            ps.setQueryTimeout(100);
            ps.setString(1,usn);
            ps.setString(2,bookId);
            ResultSet rs=ps.executeQuery();
            return rs;
        }
        else
        {
        }
        return null;
    }
    public static ResultSet issueBookConfirm(String usn,String bookId,String staffId) throws SQLException {
        if(connection!=null){
            String issueBook="exec spIssueBookConfirm ?, ?,?";
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
        }
        return null;
    }

    public static ResultSet collectBookDetails(String usn,String bookId) throws SQLException {
        if(connection!=null){
            String issueBook="exec spcollectBookDetails ?, ?";
            PreparedStatement ps=connection.prepareStatement(issueBook);
            ps.setEscapeProcessing(true);
            ps.setQueryTimeout(100);
            ps.setString(2,usn);
            ps.setString(1,bookId);
            ResultSet rs=ps.executeQuery();
            return rs;
        }
        else{
        }
        return null;
    }

    public static ResultSet collectBookConfirm(String usn,String bookId,String staffId) throws SQLException {
        if(connection!=null){
            String issueBook="exec spcollectBookConfirm ?, ?,?";
            PreparedStatement ps=connection.prepareStatement(issueBook);
            ps.setEscapeProcessing(true);
            ps.setQueryTimeout(100);
            ps.setString(2,usn);
            ps.setString(1,bookId);
            ps.setString(3,staffId);
            ResultSet rs=ps.executeQuery();
            return rs;
        }
        else
        {
        }
        return null;
    }
    public static ResultSet getStudentBookDetails(String usn) throws SQLException {
        if(connection!=null){
            String issueBook="exec spgetStudentBookDetails ?";
            PreparedStatement ps=connection.prepareStatement(issueBook);
            ps.setEscapeProcessing(true);
            ps.setQueryTimeout(100);
            ps.setString(1,usn);
            ResultSet rs=ps.executeQuery();
            return rs;
        }
        else
        {
        }
        return null;
    }
}
