/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package customExceptions;

/**
 *
 * @author Sebasugami
 */
public class InvalidAgeException extends StudentCreationException{

    public InvalidAgeException(String message) {
        super(message);
    }
    
}
