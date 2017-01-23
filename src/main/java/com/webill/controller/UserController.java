/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.webill.controller;

import com.webill.daoApi.UserDao;
import com.webill.model.User;
import com.webill.pagevalidator.LoginFormValidation;
import com.webill.utils.Constants;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author JimmyHome
 */
@Controller
public class UserController {

    @Autowired
    private UserDao jdbcDao;

    @Autowired
    LoginFormValidation loginFormValidation;
    
    //private static final Map<String, String> roleList = Constants.populateUserRole();

    @RequestMapping(value = "/addUser", method = RequestMethod.GET)
    public String getUserForm(@ModelAttribute("userForm") User user, Model model) {
        
        model.addAttribute("roleList", Constants.populateUserRole());
        return "addUser";
    }

    @RequestMapping(value = "/addUser", method = RequestMethod.POST)
    public String saveUser(@Valid @ModelAttribute("userForm") User user, BindingResult result, Model model) {

        loginFormValidation.validate(user, result);

        if (result.hasErrors()) {
            model.addAttribute("roleList", Constants.populateUserRole());
            return "addUser";
        }

        jdbcDao.saveOrUpdate(user);
        jdbcDao.createUserTable(user.getUserID());
        return "/userList";
    }

    @RequestMapping(value = "/userList", method = {RequestMethod.POST})
    public String userListView(@ModelAttribute("userList") User user, Model model) {
         
        model.addAttribute("roleList", Constants.populateUserRole());
        return "userList";
    }

}
