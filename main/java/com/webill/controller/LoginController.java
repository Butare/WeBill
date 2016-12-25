/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.webill.controller;

import com.webill.utils.MD5ByteGenerator;
import com.webill.service.QueryExecutorService;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author JimmyHome
 */
@Controller
public class LoginController {
    
    @Autowired
    QueryExecutorService queryExecutorService;
    
 @RequestMapping(value="/login", method = RequestMethod.GET) 
 public String getLoginPage(){
        return "Login"; 
   }
    
    /**
     * @param userName a user name string
     * @param passWord the password string
     * @param userRole the role sting {@literal  Admin/Customer}
     * @param model a view model
     * @return the welcome view for successful login.
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    @RequestMapping(value="/login", method= RequestMethod.POST)
    public String validateLoginPage(@RequestParam String userName, 
                                    @RequestParam String passWord, 
                                    @RequestParam String userRole, 
                                    ModelMap model) throws SQLException, ClassNotFoundException{
        
        model.put("role", userRole);
        
        String viewPage; 
        
        if (!userName.isEmpty() && !passWord.isEmpty() && !userRole.isEmpty()) {
            
            String idSecretMatchingQuery = "select * from Users where userID='"+userName+"'and "
                    + "secret='"+MD5ByteGenerator.getMD5Bytes(passWord)+"'";
            ResultSet rs = queryExecutorService.getQueryResult(idSecretMatchingQuery);
            if (rs.next()) {
                model.put("givenName", rs.getString("givenName"));
                model.put("meterID", rs.getString("meterID"));
                viewPage = "welcome";
            } else {
                model.put("errorMessage", "Incorrect username/password. Try again");
                viewPage = "Login";
            }
        } else{
            model.put("errorMessage", "Please input all values. Try again");
            viewPage = "Login";
        }
        return viewPage;
    }   
}
