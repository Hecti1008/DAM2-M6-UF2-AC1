package AC1;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.Connection;
import java.sql.*;
import javax.swing.JOptionPane;


/**
 *
 * @author Hector Garzon
 */
public class AC1_HGB {
    public static final String URL = "jbdc:mysql://localhost:3306/AC1_UF2_M6";
    public static final String USERNAME = "root";
    public static final String PASSWORD = "";
    
    public static void main(String[] args) {
        
        Connection connection = null;
            connection = connection();
    }
    
    public static Connection connection(){
        Connection connection = null;
        
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            JOptionPane.showMessageDialog(null, "Conexio establerta");
        } catch  (Exception e) {
            System.out.println("e");
        }
        return connection;
    }
    
}
