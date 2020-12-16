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
        //conexio a la bdoo
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
        ResultSet resSet;
        Statement stmt;
        //boolean programa = true
        
            menu();
            opcio = scan.nextInt();
                    
                    switch(opcio){
                        //inserim els alumnes per teclat
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
                        
                        //fem els inserts a la base de dades
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
                           //modificar Alumnes 
                        case 2:
                            
                          System.out.println("Introdueix el DNI de l'alumne a modificar");
                            DNI = scan.next();
                            boolean modificar = true;
                            //triem el dni per buscar l'alumne
                            stmt = (Statement) conn.createStatement();
                            resSet = stmt.executeQuery("SELECT * FROM alumne WHERE DNI = '" + DNI + "'");
                            
                            System.out.println("Nom---DNI---DataNaixement---Adreça---Sexe---CodiPostal");
                            while (resSet.next())
                                System.out.println(resSet.getString(1) + " " + resSet.getString(2)+ " " + 
                                        resSet.getString(3) + " " + resSet.getString(4) + " " + 
                                        resSet.getString(5) + " " + resSet.getString(6));
                            
                            //seleccionem el camp a modificar
                            System.out.println("Escriu el camp a modificar: ");
                            System.out.println("Nom---DNI---DataNaixement---Adreça---Sexe---CodiPostal");
                            
                    }
        
        
        
        
    }
}
    
   
        

    

