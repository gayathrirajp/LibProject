package businesslayer;

import android.util.Log;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class StudentLayer {

    public static ResultSet getProfile(String Usn){
        String fetchProfile="exec StudentDetails ?";
        ConSql c= new ConSql();
        Connection con=c.conClass();
        try{
            PreparedStatement ps = con.prepareStatement(fetchProfile);
            ps.setEscapeProcessing(true);
            ps.setQueryTimeout(100);
            ps.setString(1, Usn);
            ResultSet rs = ps.executeQuery(); // ResultSet rs = ps.executeUpdate(); in case SP involves DML
            return rs;
        }
        catch(Exception e){
            Log.e("Error: ", e.getMessage());
        }

        return null;
    }

    public static ResultSet getHistory(String Usn){
        String fetchProfile="exec StudentHistory ?";
        ConSql c= new ConSql();
        Connection con=c.conClass();
        try{
            PreparedStatement ps = con.prepareStatement(fetchProfile);
            ps.setEscapeProcessing(true);
            ps.setQueryTimeout(100);
            ps.setString(1, Usn);
            ResultSet rs = ps.executeQuery(); // ResultSet rs = ps.executeUpdate(); in case SP involves DML
            return rs;
        }
        catch(Exception e){
            Log.e("Error: ", e.getMessage());
        }

        return null;
    }
}
