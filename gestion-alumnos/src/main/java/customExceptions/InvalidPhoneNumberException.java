/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package customExceptions;

/**
 *
 * @author Sebasugami
 */
public class InvalidPhoneNumberException extends StudentCreationException{

    public InvalidPhoneNumberException(String message) {
        super(message);
    }
    
}
