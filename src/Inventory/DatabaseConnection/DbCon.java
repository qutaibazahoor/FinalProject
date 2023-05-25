package Inventory.DatabaseConnection;

import java.sql.Connection;
import java.sql.DriverManager;

public class DbCon {
    static Connection connect;
    public static Connection createConnection()
    {
        try {
//loading the drivers
            Class.forName("com.mysql.cj.jdbc.Driver");
// now making connection
            String user = "root";
            String password="1234";
            String url="jdbc:mysql://127.0.0.1:3306/inventory";
            connect= DriverManager.getConnection(url, user, password);
        } catch (Exception e) { // TODO: handle exception e.printStackTrace();
        }
        return connect;
    }
}
