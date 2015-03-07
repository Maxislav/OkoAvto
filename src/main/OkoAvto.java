//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package main;

import java.io.FileReader;
import java.io.IOException;
import java.sql.SQLException;
import main.Bd;
import main.Server;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class OkoAvto {
    public OkoAvto() {
    }

    public static void main(String[] args) throws ClassNotFoundException, SQLException, IOException {
        String root = null;
        String url = null;
        String table = null;
        String pass = null;
        JSONParser parser = new JSONParser();

        try {
            Object obj = parser.parse(new FileReader("config.txt"));
            JSONObject bd = (JSONObject)obj;
            root = (String)bd.get("root");
            url = (String)bd.get("url");
            table = (String)bd.get("table");
            pass = (String)bd.get("pass");
        } catch (ParseException var8) {
            var8.printStackTrace();
        }

        System.out.println("Version 31.01.15");
        Bd bd1 = new Bd(root, pass, url, table);
        bd1.connect();
        new Server(10002, bd1);
    }
}
