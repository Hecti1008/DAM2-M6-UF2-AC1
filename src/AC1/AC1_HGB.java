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
        System.out.println("Tria una opcio, per poder introduir un alumne"
                + " has d'introduir una poblacio amb un codipostal.");
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
        boolean valor;
        int id;
        ResultSet resSet;
        Statement stmt;
        
            menu();
            opcio = scan.nextInt();
                    
                    switch(opcio){
                        //inserim els alumnes per teclat
                        case 1:
                            
                        System.out.println("Introdueix el nom de l'alumne");
                        String nom = scan.next();
                        System.out.println("Introdueix el DNI");
                        String dni = scan.next();
                        System.out.println("Introdueix la data de naixement (en String)");
                        String datanaixement = scan.next();
                        System.out.println("Introdueix la adreça");
                        String adreça = scan.next();
                        System.out.println("Introdueix el sexe");
                        String sexe  = scan.next();
                        System.out.println("Introdueix codiPostal");
                        int codipostal = scan.nextInt();
                        
                        
                        //fem els inserts a la base de dades
                      stmt = (Statement) conn.createStatement();
                    stmt.executeUpdate("INSERT INTO alumnes(nom, dni, datanaixement, adreça, sexe, codipostal)"
                            + "VALUES ('" 
                            + nom
                            + "', '"
                            + dni
                            + "', '"
                            + datanaixement
                            + "', '"
                            + adreça
                            + "', '"
                            + sexe
                            + "', '"
                            + codipostal
                            + "')");
                    
                    System.out.println("alumne introduit");
                    
                    break;
                           //modificar Alumnes 
                        case 2:
                            
                          System.out.println("Introdueix el DNI de l'alumne a modificar");
                            dni = scan.next();
                            boolean modificar = true;
                            //triem el dni per buscar l'alumne
                            stmt = (Statement) conn.createStatement();
                            resSet = stmt.executeQuery("SELECT * FROM alumnes WHERE dni = '" + dni + "'");
                            
                            valor =resSet.next();
                            
                            //si falla l'alunme
                            if (valor == false) {
                                System.out.println("L'alumne introduit no existeix");
                            }else{
                                while (modificar) {
                                    resSet = stmt.executeQuery("SELECT * FROM alumnes WHERE dni = '" + dni + "'");
                                
                            System.out.println("Nom---DNI---Data Naixement---Adreça---Sexe---Codi Postal");
                            while (resSet.next())
                                System.out.println(resSet.getString(1) + " " + resSet.getString(2)+ " " + 
                                        resSet.getString(3) + " " + resSet.getString(4) + " " + 
                                        resSet.getString(5) + " " + resSet.getInt(6));
                            
                            //seleccionem el camp a modificar
                            System.out.println("Escriu el camp a modificar: ");
                            System.out.println("Nom---DNI---Data Naixement---Adreça---Sexe---Codi Postal");
                            String campM = scan.next().toLowerCase();
                            System.out.println("Introdueix el nou camp");
                            
                            if (campM.equals("campo")) {
                                int codipostUpdate = scan.nextInt();
                                stmt.executeUpdate("UPDATE alumnes set " + campM + 
                                        "= '" + valor + "'WHERE dni = '" + 
                                        dni + "'");
                            }else{
                                String valor2 = scan.next();
                                stmt.executeUpdate("UPDATE alumnes set " + campM + 
                                        "= '" + valor + "'WHERE dni = '" + 
                                        dni + "'");
                            }
                            
                                    System.out.println("Vols modificar mes camps?");
                                    System.out.println("Si o no?");
                                    String altraM = scan.next();
                                    if (altraM.equalsIgnoreCase("no")) {
                                        modificar = false;
                                    }
                            
                            }       
                    }
                        break;
                            
                            //eliminar alumne
                            
                        case 3:
                            
                            System.out.println("Introdueix el DNI del alumne que vols eliminar");
                    dni = scan.next();
                    
                    stmt = (Statement) conn.createStatement();
                    
                    resSet = stmt.executeQuery("SELECT * FROM alumnes WHERE dni = '" + dni
                                    + "'");
                    valor = resSet.next();
                    
                    if (valor == false) {
                        System.out.println("Alumne no existeix");
                    }else{
                        stmt = (Statement) conn.createStatement();
                        stmt.executeUpdate("DELETE FROM alumnes WHERE dni = '" + dni
                                        + "'");
                        
                        System.out.println("Alumne eliminat correctament");
                    }
                    
                    break;
                            
                    //ensenyar l'alumne
                            
                        case 4:
                            
                            stmt = (Statement) conn.createStatement();
                            resSet = stmt.executeQuery("SELECT * FROM alumnes");
                            System.out.println("Nom---DNI---Data Naixement---Adreça---Sexe---Codi Postal");
                            while (resSet.next())
                                
                                System.out.println(resSet.getString(1) + " " + resSet.getString(2)+ " " + 
                                        resSet.getString(3) + " " + resSet.getString(4) + " " + 
                                        resSet.getString(5) + " " + resSet.getInt(6));
                            break;
                            
                    //sortir del programa i tancar la conexio
                        case 5:
                            System.out.println("Conexio tancada");
                            conn.close();
                            programa = false;
                            
                        default:
                            break;
                    }
}
}
    
   
        

    

