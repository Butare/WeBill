/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.webill.controller;

import com.webill.dao.JdbcDaoImpl;
import com.webill.model.User;
import com.webill.pagevalidator.LoginFormValidation;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author JimmyHome
 */
@Controller
public class LoginController{
    
    @Autowired
    LoginFormValidation loginFormValidation;
    
    @Autowired 
    private JdbcDaoImpl jdbcDaoImpl;
 
 @RequestMapping(value="/login", method = RequestMethod.GET) 
 public String getLoginPage(Model model){
     User user = new User();
      
     model.addAttribute("userLogin", user);
        return "Login"; 
   }
 
 @RequestMapping(value="/login", method = RequestMethod.POST)
 public String validateLoginPage(@Valid @ModelAttribute("userLogin") User user
                  , BindingResult result, Model model ) throws ClassNotFoundException, SQLException{
     
     Logger l = Logger.getLogger("Test");
     l.log(Level.INFO, "User Role :{0}", user.getUserRole());
     
     if(result.hasErrors()){
         return "Login";
     }
     
     String viewPage;
     
     if (!user.getUserID().isEmpty() && !user.getPassWord().isEmpty() && !user.getUserRole().isEmpty()) 
     {
     
     List<Map<String, Object>> successUser = jdbcDaoImpl.getUser(user.getUserID(), user.getPassWord());
        if (successUser != null && !successUser.isEmpty()) {
            
           // Logger l = Logger.getLogger("Test");
            
            Map<String, Object> userLogin = successUser.get(0);
            
                model.addAttribute("role", userLogin.get("userRole"));
                model.addAttribute("givenName", userLogin.get("givenName"));
                model.addAttribute("meterID", userLogin.get("meterID"));
                viewPage = "welcome";
                
                l.log(Level.INFO, "The user. {0} successfully loged in.", userLogin.get("givenName"));

        }else {
                model.addAttribute("errorMessage", "Incorrect username/password. Try again");
                viewPage = "Login";
            }
     }else{
            model.addAttribute("errorMessage", "Please input all values. Try again");
            viewPage = "Login";
        }
     
     return viewPage;

    }      
}
    