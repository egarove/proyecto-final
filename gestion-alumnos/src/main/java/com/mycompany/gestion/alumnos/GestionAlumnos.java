/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package com.mycompany.gestion.alumnos;

import com.mycompany.gestion.alumnos.Student;
import customExceptions.StudentCreationException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Eloy Garofano Velazquez/Sebastian Rodriguez Rodriguez
 */
public class GestionAlumnos {

    public static void main(String[] args) {

        String url = "jdbc:mysql://localhost:3306/colegio";
        String user = "root";
        String password = "";

        try {
            //cargamos el driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            //establecemos conexion
            Connection conexion = DriverManager.getConnection(url, user, password);

            //si ha salido bien (NO SALTA AL CATCH)
            System.out.println("ABLE TO CONNECT DATABASE");

            //Menu inicial
            boolean endProgram = false;
            while (!endProgram) {
                switch (menu()) {
                    case 1:
                        manageStudents(getStudents(conexion));
                        break;
                    case 2:
                        addStudent(conexion);
                        break;
                    case 3:
                        deleteStudent(conexion);
                        break;
                    case 4:
                        System.out.println("Closing program");
                        endProgram = true;
                        break;
                    default:
                        System.out.println("Invalid option. please, introduce a valid one");
                }
            }
          
            //Cerramos recursos
            conexion.close();

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        } catch (ClassNotFoundException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public static int menu() {
        Scanner keyboard = new Scanner(System.in);

        System.out.println("1. Manage students");
        System.out.println("2. Add student");
        System.out.println("3. Delete student");
        System.out.println("4. Exit");

        try {
            int choice = keyboard.nextInt();
            return choice;
        } catch (InputMismatchException e) {
            System.out.println("Invalid input. Please, introduce a number");
            return 0;
        }
    }
    
    public static void addStudent(Connection conexion) {
        Scanner keyboard = new Scanner(System.in);
        try {
            System.out.println("\n---ADD A NEW STUDENT---");
            System.out.print("Introduce id: ");
            int id = keyboard.nextInt();
            System.out.print("Introduce name: ");
            String name = keyboard.next();
            System.out.print("Introduce surname: ");
            keyboard.nextLine(); // limpiar buffer 
            String surname = keyboard.nextLine();   
            System.out.print("Introduce class: ");
            char studentClass = keyboard.next().charAt(0);
            System.out.print("Introduce dni: ");
            String dni = keyboard.next();
            System.out.print("Introduce age: ");
            int age = keyboard.nextInt();
            System.out.print("Introduce gender: ");
            String gender = keyboard.next();
            System.out.print("Introduce phone number: ");
            String phone = keyboard.next();
            System.out.print("Introduce first score: ");
            int score1 = keyboard.nextInt();
            System.out.print("Introduce second score: ");
            int score2 = keyboard.nextInt();
            System.out.print("Introduce third score: ");
            int score3 = keyboard.nextInt();

            if (studentExists(id, conexion)) {
                System.out.println("this id already exists!");
            } else {
                Student alumno = new Student(id, name, surname, studentClass, dni, age, gender, phone, score1, score2, score3);

                Statement consulta = conexion.createStatement();
                consulta.execute(
                        "INSERT INTO alumnos "
                        + "(id, nombre, apellidos, clase, dni, edad, genero, telefono, nota1trim, nota2trim, nota3trim) "
                        + "VALUES ("
                        + id + ", '" + name + "', '" + surname + "', '" + studentClass + "', '" + dni + "', "
                        + age + ", '" + gender + "', '" + phone + "', "
                        + score1 + ", " + score2 + ", " + score3 + ");"
                );
            }
            System.out.println("Student added succesfully!");
        } catch (StudentCreationException ex) {
            System.out.println("Student creation interrupted. Reason: " + ex.getMessage());
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        } catch (InputMismatchException ex) {
            System.out.println("Student creation interrupted. Reason: Invalid data type");
        }
    }

    public static void deleteStudent(Connection conexion) {
        Scanner keyboard = new Scanner(System.in);
        System.out.print("Introduce id to remove: ");
        int idRemove = keyboard.nextInt();

        //comprobamos si existe
        if (studentExists(idRemove, conexion)) {
            try (Statement consulta = conexion.createStatement()){
                consulta.execute("DELETE FROM alumnos WHERE id = " + idRemove + ";");
                System.out.println("student " + idRemove + " deleted.");
                
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        } else {
            System.out.println("Student does not exists!");
        }
    }

    public static boolean studentExists(int id, Connection conexion) {
        try (Statement consulta = (Statement) conexion.createStatement(); ResultSet resultado = consulta.executeQuery("SELECT id FROM alumnos");) {

            while (resultado.next()) {
                if (id == resultado.getInt("id")) {
                    return true;
                }
            }
        } catch (SQLException ex) {
            System.out.println("Something went wrong while checking if the student exists");
        }
        return false;
    }

    public static ArrayList<Student> getStudents(Connection conexion) {
        ArrayList<Student> students = new ArrayList<>(); //en este arrayList se recopilan todos los alumnos para usar sus datos

        //recopilamos todos los alumnos de la base de datos y los añadimos al arraylist students
        try (Statement consulta = (Statement) conexion.createStatement(); ResultSet resultado = consulta.executeQuery("SELECT * FROM alumnos");) {

            while (resultado.next()) {

                int id = resultado.getInt("id");
                String name = resultado.getString("nombre");
                String surname = resultado.getString("apellidos");
                char studentClass = resultado.getString("clase").charAt(0);
                String dni = resultado.getString("dni");
                int age = resultado.getInt("edad");
                String gender = resultado.getString("genero");
                String phoneNumber = resultado.getString("telefono");
                double score1 = resultado.getDouble("nota1trim");
                double score2 = resultado.getDouble("nota2trim");
                double score3 = resultado.getDouble("nota3trim");


                Student student = new Student(id, name, surname, studentClass,
                        dni, age, gender, phoneNumber, score1, score2, score3);

                students.add(student);
            }

        } catch (SQLException ex) {
            System.out.println("Something went wrong while extracting students from database. " + ex.getMessage());
        } catch (StudentCreationException ex) {
            System.out.println("something went wrong while creating each student");
        }

        return students;
    }
    
    
    /*--------------------------*/
    public static void manageStudents(ArrayList<Student> students){
        //Aquí se crearán las opciones para filtrar por cada campo
        StudentsManagement.setStudents(students);
        //Menu inicial
            boolean endProgram = false;
            while (!endProgram) {
                switch (menuManagement()) {
                    case 1:
                        StudentsManagement.showStudents();
                        break;
                    case 2:
                        sorting();
                        break;
                    case 3:
                        filter();
                        break;
                    case 4:
                        StudentsManagement.searchId();
                        break;
                    case 5:
                        System.out.println("Closing program");
                        endProgram = true;
                        break;
                    default:
                        System.out.println("Invalid option. please, introduce a valid one");
                }
            }
    }
    
    public static int menuManagement() {
        Scanner keyboard = new Scanner(System.in);

        System.out.println("1. Show Students");
        System.out.println("2. Sort students");
        System.out.println("3. Filter");
        System.out.println("4. Search id");
        System.out.println("5. Exit");

        try {
            int choice = keyboard.nextInt();
            return choice;
        } catch (InputMismatchException e) {
            System.out.println("Invalid input. Please, introduce a number");
            return 0;
        }
    }
    
    /*--------------------------*/
    
    public static void sorting(){
        //Menu inicial
            boolean endProgram = false;
            while (!endProgram) {
                switch (sortingMenu()) {
                    case 1:
                        StudentsManagement.orderBySurnameAndName();
                        break;
                    case 2:
                        StudentsManagement.orderByAge();
                        break;
                    case 3:
                        System.out.println("returning...");
                        endProgram = true;
                        break;
                    default:
                        System.out.println("Invalid option. please, introduce a valid one");
                }
            }
    }
    
    public static int sortingMenu() {
        Scanner keyboard = new Scanner(System.in);

        System.out.println("Choose sorting method");
        System.out.println("1. by name/surname");
        System.out.println("2. by age");
        System.out.println("3. Exit");

        try {
            int choice = keyboard.nextInt();
            return choice;
        } catch (InputMismatchException e) {
            System.out.println("Invalid input. Please, introduce a number");
            return 0;
        }
    }
    
    /*--------------------------*/    
    
    
    public static void filter(){
        //Menu inicial
            boolean endProgram = false;
            while (!endProgram) {
                switch (filterMenu()) {
                    case 1:
                        StudentsManagement.filterByGroup();
                        break;
                    case 2:
                        StudentsManagement.filterByGender();
                        break;
                    case 3:
                        StudentsManagement.filterByGrades();
                        break;
                    case 4:
                        System.out.println("returning...");
                        endProgram = true;
                        break;
                    default:
                        System.out.println("Invalid option. please, introduce a valid one");
                }
            }
    }
    
    public static int filterMenu() {
        Scanner keyboard = new Scanner(System.in);

        System.out.println("Choose filter method");
        System.out.println("1. by group");
        System.out.println("2. by gender");
        System.out.println("3. by score");
        System.out.println("4. Exit");

        try {
            int choice = keyboard.nextInt();
            return choice;
        } catch (InputMismatchException e) {
            System.out.println("Invalid input. Please, introduce a number");
            return 0;
        }
    }    
}
