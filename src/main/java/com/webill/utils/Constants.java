/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.webill.utils;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 *
 * @author JimmyHome
 */
public class Constants {

    /**
     * All constants that used for general purpose in WeBill are defined here.
     */
    public static final String UPLOAD_DIRECTORY = "/WEB-INF/photos";
    public static final String ADMIN_ROLE = "Admin";
    public static final String CUSTOMER_ROLE = "Customer";
    
    public static Map<String, String> ROLELIST = new LinkedHashMap<>();

    public static Map<String, String> populateUserRole() {
        //Map<String, String> roleList = new LinkedHashMap<>();
        ROLELIST.put(ADMIN_ROLE, "Admin.");
        ROLELIST.put(CUSTOMER_ROLE, "Customer");
        return ROLELIST;
    }

   
}
