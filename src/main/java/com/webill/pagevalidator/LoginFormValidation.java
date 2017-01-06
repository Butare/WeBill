/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.webill.pagevalidator;

import com.webill.model.User;
import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

/**
 *
 * @author JimmyHome
 */
//@Service
public class LoginFormValidation implements Validator{

    /*
     * This Validator validates <i>just</i> User instances
     * consider using <i> User.class.isAssignableFrom(clazz) </i> if you want
     * to validate; the User instances and any subclasses of User too.
     */
    public boolean supports(Class clazz) {
        return User.class.equals(clazz);
    }

    public void validate(Object obj, Errors errors) {
       User user = (User) obj;
       
       ValidationUtils.rejectIfEmptyOrWhitespace(errors, "userID", "userLogin.userID");
       ValidationUtils.rejectIfEmptyOrWhitespace(errors, "passWord", "userLogin.passWord");
       ValidationUtils.rejectIfEmptyOrWhitespace(errors, "userRole", "userLogin.userRole");
    }
    
}
