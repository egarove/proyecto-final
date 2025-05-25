/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.gestion.alumnos;

import customExceptions.InvalidAgeException;
import customExceptions.InvalidDniException;
import customExceptions.InvalidGenderException;
import customExceptions.InvalidGradeException;
import customExceptions.InvalidPhoneNumberException;
import customExceptions.InvalidStudentClassException;
import java.sql.Date;
import java.time.LocalDate;
import java.util.Arrays;

/**
 *
 * @author Sebasugami
 */
public class Student {
    private int id;
    private String name;
    private String surname;
    private char studentClass;
    private String dni;
    private int age;
    private Character gender;
    private String phoneNumber;
    private double[] grades = new double[3];

    public Student(int id, String name, String surname, char studentClass, String dni, int age, String gender, String phoneNumber, double grade1, double grade2, double grade3) 
    throws InvalidAgeException, InvalidDniException, InvalidGenderException, InvalidGradeException, InvalidPhoneNumberException, InvalidStudentClassException{
        setId(id);
        setName(name);
        setSurname(surname);
        setStudentClass(studentClass);
        setDni(dni);
        setAge(age);
        setGender(gender);
        setPhoneNumber(phoneNumber);
        setGrades(grade1, grade2, grade3);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public char getStudentClass() {
        return studentClass;
    }

    public void setStudentClass(char studentClass) throws InvalidStudentClassException{
        if(!Character.isLetter(studentClass)){
            throw new InvalidStudentClassException("Invalid class");
        }else{
            this.studentClass = studentClass;
        }
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) throws InvalidDniException{
        boolean dniIsValid = true;
        if(dni.length() != 9){
            dniIsValid = true;
        } else{
            for (int i = 0; i < dni.length(); i++) {
                if(i == (dni.length() -1)){
                    if(!Character.isLetter(dni.charAt(i))){
                        dniIsValid = false;
                    }
                }else{
                    if(!Character.isDigit(dni.charAt(i))){
                        dniIsValid = false;
                    }
                }
            }
        }
        
        if(!dniIsValid){
            throw new InvalidDniException("Invalid DNI");
        }else{
            this.dni = dni;
        }
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) throws InvalidAgeException{
        if(age < 2 || age > 65){
            throw new InvalidAgeException("Invalid Age");
        }else{
            this.age = age;
        }
    }

    public char getGender() {
        return gender;
    }

    public void setGender(String gender) throws InvalidGenderException{
        if(gender.equalsIgnoreCase("Male") || gender.equalsIgnoreCase("Female") || gender.equalsIgnoreCase("F") || gender.equalsIgnoreCase("M")){
            
            this.gender = gender.toUpperCase().charAt(0);
        }else{
            throw new InvalidGenderException("Invalid gender");
        }
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) throws InvalidPhoneNumberException{
        boolean validNumber = true;
        if(phoneNumber.length() != 9){
            validNumber = false;
        }else{
            for (int i = 0; i < phoneNumber.length(); i++) {
                if (!Character.isDigit(phoneNumber.charAt(i))) {
                    validNumber = false;
                }
            }
        }
        
        if(!validNumber){
            throw new InvalidPhoneNumberException("Invalid phone number");
        }else{
            this.phoneNumber = phoneNumber;
        }
    }

    public double[] getGrades() {
        return grades;
    }

    public void setGrades(double g1, double g2, double g3) throws InvalidGradeException{
        
        if(Arrays.stream(new double[]{g1, g2, g3}).anyMatch(grade -> grade < 0 || grade > 10)){
            throw new InvalidGradeException("There's at least one invalid grade");
        } else{
            grades[0] = g1;
            grades[1] = g2;
            grades[2] = g3;
        }
    }

    @Override
    public String toString() {
        String msg = ("ID:" + id + " | " + name + " " + surname + " | Age: " + age + " | Gender: " + gender + 
                " | Phone number: " + phoneNumber + " | DNI: " + dni + " | Group: " + studentClass + " | Grades: " +
                grades[0] + " " + grades[1] + " " + grades[2] + " | Average: " + Arrays.stream(grades).average() );
        
        return msg;
    }
    
    
}
