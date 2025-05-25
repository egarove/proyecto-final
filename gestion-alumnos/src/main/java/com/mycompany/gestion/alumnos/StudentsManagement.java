/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.gestion.alumnos;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

/**
 *
 * @author Sebasugami
 */
public class StudentsManagement {

    private static ArrayList<Student> students = new ArrayList<>();

    public static void setStudents(ArrayList<Student> students) {
        StudentsManagement.students = students;
    }

    public static void showStudents() {
        if (students.isEmpty()) {
            System.out.println("There're no students!");
        } else {
            students.forEach(System.out::println); //muestra los estudiantes si existen uno a uno
        }
    }

    public static void filterByGroup() {
        Scanner sc = new Scanner(System.in);

        System.out.println("Introduce a group");

        try {
            char studentClass = sc.next().charAt(0);
            List<Student> filteredStudents = students.stream().filter(st -> st.getStudentClass() == studentClass).collect(Collectors.toList()); //filtra los estudiantes según la clase y los recopila en una lista
            
            if(filteredStudents.isEmpty()){
                System.out.println("This group doesn't exists");
            }else{
                filteredStudents.forEach(System.out::println); //muestra los estudiantes si existen
            }
        } catch (InputMismatchException ex) {
            System.out.println("Invalid input");
        }
    }
    
    public static void filterByGrades(){
        //Separar los alumnos aprobados de los suspensos
        List<Student> approvedStudents = students.stream().filter(st -> Arrays.stream(st.getGrades()).average().getAsDouble() >= 5).collect(Collectors.toList());
        List<Student> failedStudents = students.stream().filter(st -> Arrays.stream(st.getGrades()).average().getAsDouble() < 5).collect(Collectors.toList());
        
        //mostrar cuantos alumnos hay en cada caso, y listarlos uno a uno
        System.out.println("Approved students: " + approvedStudents.stream().count());
        approvedStudents.forEach(System.out::println);
        System.out.println("-----------------------------");
        System.out.println("Failed students:" + failedStudents.stream().count());
        failedStudents.forEach(System.out::println);
    }

    public static void filterByGender(){
        //Separar los hombres y las mujeres
        List<Student> maleStudents = students.stream().filter(st -> st.getGender() == 'M').collect(Collectors.toList());
        List<Student> femaleStudents = students.stream().filter(st -> st.getGender() == 'F').collect(Collectors.toList());
        
        //mostrar cuantos alumnos hay en cada caso, y listarlos uno a uno
        System.out.println("Men: " + maleStudents.stream().count());
        maleStudents.forEach(System.out::println);
        System.out.println("-----------------------------");
        System.out.println("Women:" + femaleStudents.stream().count());
        femaleStudents.forEach(System.out::println);
    }
    
    public static void orderBySurnameAndName(){
        students.stream().sorted().forEach(System.out::println);
    }
    
    public static void orderByAge(){
        students.stream().sorted(Comparator.comparing(Student::getAge)).forEach(System.out::println);
    }
    
    public static void searchId(){
        Scanner sc = new Scanner(System.in);

        
        int id;
        boolean found= false; 
        
        try {
            System.out.println("Introduce an id");
            id= sc.nextInt();
            
            for (Student student : students) {
                if(student.getId()==id){
                    System.out.println(student);
                    found = true;
                }
            }
            if (!found) System.out.println("id: "+id+"id does not exists.");
        } catch (InputMismatchException ex) {
            System.out.println("Invalid input");
        }
    }
}
