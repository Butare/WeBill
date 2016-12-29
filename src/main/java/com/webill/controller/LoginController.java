/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.webill.controller;

import com.webill.model.User;
import com.webill.utils.MD5ByteGenerator;
import com.webill.service.QueryExecutorService;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
    QueryExecutorService queryExecutorService;
    
 
 @RequestMapping(value="/login", method = RequestMethod.GET) 
 public String getLoginPage(Model model){
     User user = new User();
      
     model.addAttribute("userLogin", user);
        return "Login"; 
   }
 
 @RequestMapping(value="/login", method = RequestMethod.POST)
 public String validateLoginPage(@ModelAttribute("userLogin") User user, Model model) throws ClassNotFoundException, SQLException{
     
        model.addAttribute("role", user.getUserRole());

        String viewPage; 
        
        if (!user.getUserID().isEmpty() && !user.getPassWord().isEmpty() && !user.getUserRole().isEmpty()) {
            
            String idSecretMatchingQuery = "select * from Users where userID='"+user.getUserID()+"'and "
                    + "secret='"+MD5ByteGenerator.getMD5Bytes(user.getPassWord())+"'";
            ResultSet rs = queryExecutorService.getQueryResult(idSecretMatchingQuery);
            if (rs.next()) {
                model.addAttribute("givenName", rs.getString("givenName"));
                model.addAttribute("meterID", rs.getString("meterID"));
                viewPage = "welcome";
            } else {
                model.addAttribute("errorMessage", "Incorrect username/password. Try again");
                viewPage = "Login";
            }
        } else{
            model.addAttribute("errorMessage", "Please input all values. Try again");
            viewPage = "Login";
        }
        return viewPage;
    } 
     
     
 }
    