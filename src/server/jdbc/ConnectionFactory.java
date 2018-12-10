package server.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {
	
	private static final String URL = "jdbc:mysql://localhost:3306/chatlog";
    private static final String USER = "root";
    private static final String PASS = "";
    
    public static Connection getConexao() {
        try {
        	Class.forName("com.mysql.jdbc.Driver");
            return DriverManager.getConnection(URL, USER, PASS);
        } catch (SQLException | ClassNotFoundException ex) {
        	ex.printStackTrace();
        }
        return null;
    }
    
    public static void close(Connection conn){
        
        try {
            if(conn!=null)
            	conn.close();
        } catch (SQLException ex) {
        	ex.printStackTrace();
        }  
    }
    

}
