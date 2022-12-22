package businesslayer;

import android.util.Log;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class LoginLayer {
    static Connection con;

    public static ResultSet verifyLogin(String uName, String passwd, String flag){
        String SPsql;
        if(flag.equals("Student")){
            SPsql = "EXEC VerifyLogin ?,?,?";
        }
        else {
            SPsql = "EXEC VerifyLogin ?,?,?";
        }
        ConSql c= new ConSql();
        con=c.conClass();
        try{
            PreparedStatement ps = con.prepareStatement(SPsql);
            ps.setEscapeProcessing(true);
            ps.setQueryTimeout(100);
            ps.setString(1, uName);
            ps.setString(2, passwd);
            ps.setString(3, flag);
            ResultSet rs = ps.executeQuery(); // ResultSet rs = ps.executeUpdate(); in case SP involves DML
            return rs;
        }
        catch(Exception e){
            Log.e("Error: ", e.getMessage());
        }

        return null;
    }
}
