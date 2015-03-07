//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package main;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;

public class Bd {
    static Connection connection;
    static Statement stmt = null;
    String root;
    String pass;
    String serverName;
    String baseName;

    public Bd(String root, String pass, String serverName, String baseName) {
        this.root = root;
        this.pass = pass;
        this.serverName = serverName;
        this.baseName = baseName;
    }

    public void connect() {
        String driverName = "com.mysql.jdbc.Driver";

        try {
            Class.forName(driverName);
        } catch (ClassNotFoundException var7) {
            var7.printStackTrace();
        }

        String url = "jdbc:mysql://" + this.serverName + "/" + this.baseName;

        try {
            connection = DriverManager.getConnection(url, this.root, this.pass);
            stmt = connection.createStatement();
            System.out.println("MySQL connect");
        } catch (SQLException var6) {
            System.out.println("MySQL err connect " + var6);

            try {
                Thread.sleep(10000L);
            } catch (InterruptedException var5) {
                var5.printStackTrace();
            }

            this.connect();
        }
    }

    public void save(String line) throws SQLException, ClassNotFoundException {
        if(connection == null || connection.isClosed()) {
            this.connect();
        }

        try {
            Statement e = stmt;
            synchronized(stmt) {
                System.out.println(line);
                stmt.executeUpdate(line);
            }
        } catch (Exception var4) {
            System.out.println("Error save " + var4);
        }

    }

    public Map<String, String> getData(String imei) {
        HashMap map = new HashMap();

        try {
            if(connection == null || connection.isClosed()) {
                this.connect();
            }
        } catch (SQLException var6) {
            var6.printStackTrace();
        }

        System.out.println("1111111");
        String query = "SELECT * FROM log WHERE imei = \'" + imei + "\' AND lat != \'null\' ORDER BY datetime DESC LIMIT 1";

        try {
            stmt.executeQuery(query);
            ResultSet e = stmt.executeQuery(query);

            while(e.next()) {
                System.out.println(e.getString("lat"));
                map.put("lat", e.getString("lat"));
                map.put("lng", e.getString("lng"));
            }
        } catch (SQLException var5) {
            var5.printStackTrace();
        }

        return map;
    }
}
