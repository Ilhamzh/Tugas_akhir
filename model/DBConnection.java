// File: model/DBConnection.java (Pastikan ini sesuai)

package model;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane; 

public class DBConnection {

    private static final String URL = "jdbc:mysql://localhost:3306/sistem_akademik";
    private static final String USER = "root";
    private static final String PASS = "";

    public static Connection getConnection() {
        try {
           
            Class.forName("com.mysql.cj.jdbc.Driver"); 
            return DriverManager.getConnection(URL, USER, PASS);
        } catch (ClassNotFoundException e) {
             JOptionPane.showMessageDialog(null, "JDBC Driver tidak ditemukan! Pastikan library MySQL Connector J terpasang.", "Fatal Error Koneksi", JOptionPane.ERROR_MESSAGE);
             return null;
        } catch (SQLException e) {
            
            JOptionPane.showMessageDialog(null, "Koneksi database gagal! Pastikan XAMPP/MySQL running. \nDetail: " + e.getMessage(), "Fatal Error Koneksi", JOptionPane.ERROR_MESSAGE);
            return null;
        }
    }

    public static void closeConnection(Connection conn) {
        try {
            if (conn != null) conn.close();
        } catch (SQLException ignored) {}
    }
}