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
import static com.webill.utils.Constants.ADMIN_ROLE;
import static com.webill.utils.Constants.CUSTOMER_ROLE;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author JimmyHome
 */
@Controller
@SessionAttributes({"userName"})
public class LoginController {

    @Autowired
    LoginFormValidation loginFormValidation;

    @Autowired
    private UserDao jdbcDao;

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String getLoginPage(Model model) {
        User user = new User();

        model.addAttribute("userLogin", user);
        
        
        return "Login";
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ModelAndView validateLoginPage(HttpServletRequest request, @Valid @ModelAttribute("userLogin") User user,
            BindingResult result, Model model)
            throws ClassNotFoundException, SQLException {

        Logger l = Logger.getLogger("Test");
        l.log(Level.INFO, "User Role :{0}", user.getUserRole());

        // model data
        Map modelM = model.asMap();
        for (Object modelKey : modelM.keySet()) {
            Object modelValue = modelM.get(modelKey);
            System.out.println(modelKey + "  :Model key and values are: " + modelValue);
        }

        ModelAndView mv = null;

        loginFormValidation.validate(user, result);

        if (result.hasErrors()) {
            model.addAttribute("errorMessage", "Got form errors. Try again");
            mv = new ModelAndView("Login");
            return mv;
        }

        if (!user.getUserID().isEmpty() && !user.getPassWord().isEmpty() && !user.getUserRole().isEmpty()) {

            List<Map<String, Object>> successUser = jdbcDao.getUserLogin(user.getUserID(), user.getPassWord(), user.getUserRole());
            if (successUser != null && !successUser.isEmpty()) {

                Map<String, Object> userLogin = successUser.get(0);

                // session 
                model.addAttribute("userName", user.getUserID());
                //model.addAttribute("userID", USER)

                // model attribute
                model.addAttribute("meterID", userLogin.get("meterID"));

                if (user.getUserRole().equals(ADMIN_ROLE)) {
                    mv = new ModelAndView("/userList");
                    mv.addObject("userList", jdbcDao.getUserList());

                } else if (user.getUserRole().equals(CUSTOMER_ROLE)) {
                    mv = new ModelAndView(showUploaded(request, user.getUserID(), model));

                    //redirect error check.
                }

                l.log(Level.INFO, "The user. {0} successfully loged in.", userLogin.get("givenName"));

            } else {
                model.addAttribute("errorMessage", "Incorrect username/password. Try again");
                mv = new ModelAndView("Login");
            }
        } else {
            model.addAttribute("errorMessage", "Please input all values. Try again");
            mv = new ModelAndView("Login");
        }

        return mv;

    }

    @RequestMapping(value = "/showUploaded", method = RequestMethod.GET)
    public String showUploaded(HttpServletRequest request, @ModelAttribute("userName") String userID, Model model) {

        User user = jdbcDao.getUserById(userID);

        model.addAttribute("userName", user.getUserID());
        model.addAttribute("role", user.getUserRole());

        System.out.println("The role in show uploded is : " + user.getEmail());
        model.addAttribute("listImageDetails", jdbcDao.getImageDetailByUser(userID));
       
        model.addAttribute("fullPath", request.getSession().
                getServletContext().getRealPath(Constants.UPLOAD_DIRECTORY));
       
        //try to add thumbnail details into a model. !!!
        return "imageDetails";

    }

    @RequestMapping(value = "/uploadImage", method = RequestMethod.GET)
    public String showUploadPage(HttpServletRequest request, @ModelAttribute("userName") String userID, Model model) {

        User user = jdbcDao.getUserById(userID);

        model.addAttribute("role", user.getUserRole());
        model.addAttribute("givenName", user.getGivenName());
        model.addAttribute("meterID", user.getMeterID());

        return "uploadImage";
    }

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logoutUser(HttpServletRequest request, Model model) {
        HttpSession session = request.getSession(false);

        if (session != null) {
            session.invalidate();
        }
        return getLoginPage(model);

    }
}
