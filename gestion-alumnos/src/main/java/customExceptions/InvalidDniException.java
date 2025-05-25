/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package customExceptions;

/**
 *
 * @author Sebasugami
 */
public class InvalidDniException extends StudentCreationException{

    public InvalidDniException(String message) {
        super(message);
    }
    
}
