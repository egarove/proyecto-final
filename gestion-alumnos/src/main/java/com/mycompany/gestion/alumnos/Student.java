/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.gestion.alumnos;

import java.sql.Date;
import java.time.LocalDate;

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
    private char gender;
    private String phoneNumber;
    private Date entryDate;
    private double[] grades = new double[3];

    public Student(int id, String name, String surname, char studentClass, String dni, int age, char gender, String phoneNumber, Date entryDate, double grade1, double grade2, double grade3) {
        setId(id);
        setName(name);
        setSurname(surname);
        setStudentClass(studentClass);
        setDni(dni);
        setAge(age);
        setGender(gender);
        setPhoneNumber(phoneNumber);
        setEntryDate(entryDate);
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

    public void setStudentClass(char studentClass) {
        this.studentClass = studentClass;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public char getGender() {
        return gender;
    }

    public void setGender(char gender) {
        this.gender = gender;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Date getEntryDate() {
        return entryDate;
    }

    public void setEntryDate(Date entryDate) {
        this.entryDate = entryDate;
    }

    public double[] getGrades() {
        return grades;
    }

    public void setGrades(double g1, double g2, double g3) {
        grades[0] = g1;
        grades[1] = g2;
        grades[3] = g3;
    }
    
    
    
}
