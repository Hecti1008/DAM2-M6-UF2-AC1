package AC1;
// http://localhost:8080/phpmyadmin/
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

import com.mysql.cj.jdbc.Driver;



/**
 *
 * @author Hector Garzon
 */
public class AC1_HGB {
    
    static Scanner scan = new Scanner(System.in);
    
    public static void menu(){
        System.out.println("Tria una opcio");
        System.out.println("1. Inserir registre");
        System.out.println("2. Modificar registre");
        System.out.println("3. Eliminar registre");
        System.out.println("4. Ensenyar registres");
        System.out.println("5. Sortir");
    }
    
    public static void main(String[]args) throws SQLException{
        
        Connection conn = null;
        
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = (Connection) DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/ac1_uf2_m6", "root","");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        
        int opcio;
        boolean programa;
        boolean valor;
        int id;
        Statement stmt;
        //boolean programa = true
        
       // while (programa){
            menu();
            opcio = scan.nextInt();
                    
                    switch(opcio){
                        case 1:
                            
                        System.out.println("Introdueix el nom de l'alumne");
                         String nom = scan.next();
                        System.out.println("Introdueix el DNI");
                        String DNI = scan.next();
                        System.out.println("Introdueix la data de naixement (en String)");
                        String dataNaixement = scan.next();
                        System.out.println("Introdueix la adreça");
                        String adreça = scan.next();
                        System.out.println("Introdueix el sexe");
                        String sexe  = scan.next();
                        System.out.println("Introdueix codiPostal");
                        int codiPostal = scan.nextInt();
        
                      stmt = (Statement) conn.createStatement();
                    stmt.executeUpdate("INSERT INTO alumne(nom, DNI, dataNaixement, adreça, sexe, codiPostal)"
                            + "VALUES ('" 
                            + nom
                            + "', '"
                            + DNI
                            + "', '"
                            + dataNaixement
                            + "', '"
                            + adreça
                            + "', '"
                            + sexe
                            + "', '"
                            + codiPostal
                            + "')");
                    
                    System.out.println("alumne introduit");
                    
                    break;
                            
                        case 2:
                            
                          System.out.println();
                    }
        
        
        
        
    }
}
    
   
        

    

