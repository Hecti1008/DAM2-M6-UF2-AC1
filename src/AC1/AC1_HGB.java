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
   
    public static void main(String[]args) throws SQLException{
    
       Scanner scan = new Scanner(System.in);
       
       int opcio;
        Statement selectStmt = null;
        Statement stmt;
        
    //conexio a la bdoo
        Connection conn = null;
        
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = (Connection) DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/ac1_uf2_m6", "root","");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        
        try {
         
            //imprimim el menu per seleccionar la opcio
        System.out.println("Tria una opcio, per poder introduir un alumne"
                + " has d'introduir una poblacio amb un codipostal.");
        System.out.println("1. Inserir registre (alumne)");
        System.out.println("2. Modificar registre");
        System.out.println("3. Eliminar registre");
        System.out.println("4. Ensenyar registres");
        System.out.println("5. Inserir registre (poblacio)");
        System.out.println("6. Sortir");
        opcio = scan.nextInt();
        while(opcio != 6){
            if(opcio == 1){
                        
                        //introduim les dades del alumne
                        System.out.println("Introdueix el nom de l'alumne");
                        String nom = scan.next();
                        System.out.println("Introdueix el DNI");
                        String dni = scan.next();
                        System.out.println("Introdueix la data de naixement (any-mes-dia)");
                        String datanaixement = scan.next();
                        System.out.println("Introdueix la adreça");
                        String adreça = scan.next();
                        System.out.println("Introdueix el sexe");
                        String sexe  = scan.next();
                        System.out.println("Introdueix codiPostal");
                        int codipostal = scan.nextInt();
                        
                        try{
                            //pasem les dades a la base de dades
                            stmt = (Statement) conn.createStatement();
                            stmt.execute("INSERT INTO alumnes(nom, dni, datanaixement, adreça, sexe, codipostal)"
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
                        } catch(Exception e) {
                            System.out.println("Afegeix poblacio i codi postal");
                        }
            } else if (opcio == 2){
                //seleccionem el camp i l'alumne a modificar segons el dni
                System.out.println("Tria una taula a modificar");
		String mTaula = scan.next();
                System.out.println("tria una columna a modificar");
		String triaColumna = scan.next();
		System.out.println("Afegeix el valor");
		String vColumna = scan.next();
                                //modifiquem el camp alumnes amb un UPdate
                                if(mTaula.equalsIgnoreCase("alumnes")){
                                    
                                    System.out.println("Modificar per DNI");
                                    String vAnterior = scan.next();
                                    
                                    selectStmt = conn.createStatement();
                                
                                    PreparedStatement pps = conn.prepareStatement("UPDATE " + mTaula + " SET " + triaColumna + " = '" + vColumna + "' WHERE dni = '" + vAnterior +"' ");
                                    pps.executeUpdate();
                                    
                                    //sino modifiquem el camp poblacio
                                } else {
                                    System.out.println("Modificar per codi Postal");
                                    int vAnterior = scan.nextInt();
                                    
                                    selectStmt = conn.createStatement();
                                
                                    System.out.println("Esats segur? Alguns alumnes poden ser modificats 1. SI / 2. NO");
                                    int cas = scan.nextInt();
                                        if(cas == 1){
                                            
                                            PreparedStatement ps = conn.prepareStatement("UPDATE " + mTaula + " SET " + triaColumna + " = '" + vColumna + "' WHERE codipostal = " + vAnterior +" ");
                                            ps.executeUpdate();
                                            
                                        } else {
                                            
                                            System.out.println("Operacio canccelada");
                                        }
                                    
                                }
             } else if (opcio == 3) {
                  int contar = 0;
                  ResultSet rs = null;
   
                            //demanem la taula, columna i el valor a eliminar
				System.out.println("Tria una taula");
				String triaTaula = scan.next();
				System.out.println("Tria una columna");
				String eColumna = scan.next();
				System.out.println("1. VARCHAR | 2. INT");
				int eValor = scan.nextInt();
                                
                                

				if(eValor == 1){
                                    
					System.out.println("Afegeix el valor");
					String vColumna = scan.next();
                                        
                                              if(triaTaula.equalsIgnoreCase("alumnes")){

                                                  PreparedStatement ps = conn.prepareStatement("DELETE FROM " + triaTaula + " WHERE " + eColumna + " = '" + vColumna + "'");
                                                  ps.executeUpdate();

                                              } else {

                                              System.out.println("No pots eliminar una columna per el nom. ");
                                              
                                              }  
                                	
				} else if (eValor == 2){
                                        System.out.println("Afegeix el valor");
					int vColumna = scan.nextInt();
                                        
                                        if(triaTaula.equalsIgnoreCase("poblacio")){
                                            
                                            try{
                                            selectStmt = conn.createStatement();
                                            
                                            rs = selectStmt.executeQuery("SELECT * FROM alumnes WHERE " + eColumna + " = '" + vColumna + "'");
                                            while(rs.next()){
                                                contar++;
                                            }
                                            
                                            System.out.println("Es borraran " + contar + " alumnes vols continuar 1.SI / 2.NO");
                                            int cas = scan.nextInt();
                                            if(cas == 1){
                                            
                                                PreparedStatement ps = conn.prepareStatement("DELETE FROM " + triaTaula + " WHERE " + eColumna + " = " + vColumna +" ");
                                                ps.executeUpdate();
                                            
                                            } else {
                                            
                                            System.out.println("Operacio cancelada");
                                        }
                                            }catch(SQLException ex){
                                            }
                                        
                                        }
                                }
                                //ensenyem tots els registres dels alumnes
             } else if (opcio == 4){
                  ResultSet rs = null;
                 stmt = (Statement) conn.createStatement();
                            rs = stmt.executeQuery("SELECT * FROM alumnes");
                            System.out.println("Nom  DNI   Data Naixement   Adreça   Sexe   Codi Postal");
                            while (rs.next())
                                
                                System.out.println(rs.getString(1) + " " + rs.getString(2)+ " " + 
                                        rs.getString(3) + " " + rs.getString(4) + " " + 
                                        rs.getString(5) + " " + rs.getInt(6));
                            break;
                            //afegim poblacio
             } else if (opcio == 5){
                 System.out.println("Afegeix una poblacio");
				String nPoblacio = scan.next();
				System.out.println("Afegeix un codi postal");
				int cpPoblacio = scan.nextInt();
                                
                                try{
                                    stmt = conn.createStatement();
                                    stmt.execute("INSERT INTO poblacio VALUES (" + cpPoblacio + ",'" + nPoblacio + "')");
                                } catch (Exception e) {
                                    System.out.println("No repeteixis codi postal");
                                }
             }
        }
        } catch (Exception e) {
			e.printStackTrace();
        } finally {
            //finalment tanquem la conexio al servidor
            try{
                System.out.println("Conexio tancada");
                conn.close();
            }catch(Exception e){
                System.out.println("No hem tancat la conexio");
                }
                            
        } 
                    
                            
                      
                    
}
}

    
   
        

    

