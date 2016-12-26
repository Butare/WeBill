/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.webill.utils;

/**
 *
 * @author JimmyHome
 */
public class Validations {
    
    // throw an IllegalArgumentException if x is null
    public static void validateNotNull(Object x) {
        
        if (x == null) 
            throw new IllegalArgumentException("The Argument is null");
    }
    
}
