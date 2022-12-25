package businesslayer;

import android.accounts.NetworkErrorException;
import android.annotation.SuppressLint;
import android.os.StrictMode;
import android.util.Log;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;

public class ConSql {
    Connection con;
    @SuppressLint("NewApi")
    public Connection conClass() {
        String ip="192.168.112.36",port="1433",db="seeshelvesdb",username="sa",password="a1236700";
        StrictMode.ThreadPolicy a =new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(a);
        String ConnectURL=null;
        try{
            Class.forName("net.sourceforge.jtds.jdbc.Driver");
            ConnectURL="jdbc:jtds:sqlserver://"+ip+":"+port+";"+
                    "databasename="+db+";user="+username+";"+"password="+password+";";
            DriverManager.setLoginTimeout(1);
            con= DriverManager.getConnection(ConnectURL);
        } catch (Exception e) {
            Log.e("Error",e.getMessage());
        }
        return con;
    }
}
