
package javafx_db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Sanchit
 */
public class ConnectionClass {
    
    Connection conn = null;
    
    public Connection getConnection() throws ClassNotFoundException, SQLException {
        
        Class.forName("com.mysql.jdbc.Driver");
        conn = DriverManager.getConnection("jdbc:mysql:///javaconnect","root","sanchit");
        
        return conn;
    }
    
    
}
