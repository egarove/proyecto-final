/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.gestion.alumnos;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Eloy Garofano Velazquez/Sebastian Rodriguez Rodriguez
 */
public class GestionAlumnos {

    public static void main(String[] args) {
        
        String url= "jdbc:mysql://localhost:3306/colegio";
        String user= "root";
        String password= "";
        
        ArrayList<Student> myStudents = new ArrayList<>();
        
        try{
            //cargamos el driver
            Class.forName("com.mysql.cj.jdbc.Driver");
        
            //establecemos conexion
            Connection conexion= DriverManager.getConnection(url,user,password);
        
                //si ha salido bien (NO SALTA AL CATCH)
                System.out.println("ABLE TO CONNECT DATABASE");
            
                //crear un Statement y ejecutar una consulta
                Statement consulta= (Statement) conexion.createStatement();
                
                //obtenemos el resultado de la consulta
                ResultSet resultado = consulta.executeQuery("SELECT * FROM alumnos");
                
                //recorremos las filas devueltas por la db
                while(resultado.next()){
                
                    int id= resultado.getInt("id");
                    String name= resultado.getString("nombre");
                    String surname= resultado.getString("apellidos");
                    char studentClass= resultado.getString("clase").charAt(0);
                    String dni= resultado.getString("dni");
                    int age= resultado.getInt("edad");
                    char gender= resultado.getString("genero").charAt(0);
                    String phoneNumber= resultado.getString("telefono");
                    Date entryDate= resultado.getDate("fecha_ingreso");
                    double score1= resultado.getDouble("nota1trim");
                    double score2= resultado.getDouble("nota2trim");
                    double score3= resultado.getDouble("nota3trim");
                    
                    System.out.println(score1);
                    
                    Student alumno = new Student (id,name,surname,studentClass,
                    dni,age,gender,phoneNumber,entryDate,score1,score2,score3);
                
                    myStudents.add(alumno);
                
                }
        
            resultado.close();
            consulta.close();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        } catch (ClassNotFoundException ex) {
            System.out.println(ex.getMessage());
        }
    }
}
