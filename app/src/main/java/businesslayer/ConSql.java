package businesslayer;

import android.annotation.SuppressLint;
import android.os.StrictMode;
import android.util.Log;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConSql {
    Connection con;
    @SuppressLint("NewApi")
    public Connection conClass(){
        String ip="192.168.127.15",port="1433",db="librarymanagement",username="sa",password="changeme";
        StrictMode.ThreadPolicy a =new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(a);
        String ConnectURL=null;
        try{
            Class.forName("net.sourceforge.jtds.jdbc.Driver");
            ConnectURL="jdbc:jtds:sqlserver://"+ip+":"+port+";"+
                    "databasename="+db+";user="+username+";"+"password="+password+";";
            con= DriverManager.getConnection(ConnectURL);
        } catch (Exception e) {
            Log.e("Error is ",e.getMessage());
        }
        return con;
    }
}
