package booking.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Dao {
    protected final String url = "jdbc:postgresql://138.68.52.248/gr1";
    protected final String user = "gruppa1";
    protected final String password = "123qwe";

    protected Connection connect() {
        Connection conn = null;
        try {
            Class.forName("org.postgresql.Driver");
            conn = DriverManager.getConnection(url, user, password);
            System.out.println("Connected to the PostgreSQL server successfully.");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } catch (ClassNotFoundException e){
            e.printStackTrace();
        }
        return conn;
    }
}

//test