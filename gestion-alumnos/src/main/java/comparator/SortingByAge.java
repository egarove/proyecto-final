/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package comparator;

import com.mycompany.gestion.alumnos.Student;
import java.util.Comparator;

/**
 *
 * @author Eloy Garofano Velazquez
 */
public class SortingByAge implements Comparator<Student>{

    @Override
    public int compare(Student o1, Student o2) {
        if(o1.getAge()==o2.getAge()){
            return 0;
        } else if (o1.getAge() < o2.getAge()){
            return -1;
        }
        else return 1;
    }
}
