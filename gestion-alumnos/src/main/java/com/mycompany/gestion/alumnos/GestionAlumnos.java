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

        ArrayList<Student> myStudents = new ArrayList<>();

        try {
            //cargamos el driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            //establecemos conexion
            Connection conexion = DriverManager.getConnection(url, user, password);

            //si ha salido bien (NO SALTA AL CATCH)
            System.out.println("ABLE TO CONNECT DATABASE");

            //crear un Statement y ejecutar una consulta
            Statement consulta = (Statement) conexion.createStatement();

            //obtenemos el resultado de la consulta
            ResultSet resultado = consulta.executeQuery("SELECT * FROM alumnos");

            //recorremos las filas devueltas por la db
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

                System.out.println(score1);

                Student alumno = new Student(id, name, surname, studentClass,
                        dni, age, gender, phoneNumber, score1, score2, score3);

                //añadimos el alumno a la coleccion
                myStudents.add(alumno);
            }
            //Cerramos los recursos        
            resultado.close();
            consulta.close();

            //MOSTRAR MENU
            int choice = 0;
            while (choice < 1 || choice > 3) {
                choice = menu();
            }
            switch (choice) {
                case 1:
                    listStudents(myStudents);
                    break;
                case 2:
                    addStudent(conexion, myStudents);
                    break;
                case 3:
                    deleteStudent(conexion, myStudents);
                    break;
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

        System.out.println("1. Listar alumnos");
        System.out.println("2. Añadir alumno");
        System.out.println("3. Eliminar alumno");
        System.out.println("4. Salir");

        try {
            int choice = keyboard.nextInt();
            return choice;
        } catch (InputMismatchException e) {
            System.out.println("Error... Introduce 1, 2 or 3!");
            return 0;
        }
    }

    public static void listStudents(ArrayList<Student> myStudents) {
        System.out.println("\n---SHOWING STUDENTS---");
        for (Object myStudent : myStudents) {
            System.out.println(myStudent);
        }
        System.out.println("---END OF THE LIST---");
    }

    public static void addStudent(Connection conexion, ArrayList<Student> myStudents) {
        Scanner keyboard = new Scanner(System.in);
        try {
            System.out.println("\n---ADD A NEW STUDENT---");
            System.out.print("Introduce id: ");
            int id = keyboard.nextInt();
            System.out.print("Introduce name: ");
            String name = keyboard.next();
            System.out.print("Introduce surname: ");
            String surname = keyboard.next();
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

            if (studentExists(id, myStudents)) {
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
        } catch (StudentCreationException ex) {
            System.out.println("Student creation interrupted. Reason: " + ex.getMessage());
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        } catch (InputMismatchException ex) {
            System.out.println("Student creation interrupted. Reason: Invalid data type");
        }
    }

    public static void deleteStudent(Connection conexion, ArrayList<Student> myStudents) {
        Scanner keyboard = new Scanner(System.in);
        System.out.print("Introduce id to remove: ");
        int idRemove = keyboard.nextInt();

        //comprobamos si existe
        if (studentExists(idRemove, myStudents)) {
            try {
                Statement consulta = conexion.createStatement();
                consulta.execute("DELETE FROM alumnos WHERE id = " + idRemove + ";");
                myStudents.removeIf(student -> student.getId() == idRemove);
                System.out.println("student " + idRemove + " deleted.");
                //cerramos recursos
                consulta.close();
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        } else {
            System.out.println("Student does not exists!");
        }
    }

    public static boolean studentExists(int idRemove, ArrayList<Student> myStudents) {
        for (Student myStudent : myStudents) {
            if (myStudent.getId() == idRemove) {
                return true;
            }
        }
        return false;
    }

}
