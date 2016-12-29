/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.webill.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author JimmyHome
 */
public class MD5ByteGenerator {
    
    /**
     * @param password from user input
     * @return a converted string {@code String};
     *         {@code null} if empty string has passed 
     */
    public static String getMD5Bytes(String password) {
        
        Validations.validateNotNull(password);
        
        try {
            byte[] md5SecreteBytes = MessageDigest.getInstance("MD5").digest(password.getBytes());
            
            StringBuilder strBuilder = new StringBuilder();
            String tmpStr;
            
            for (int i = 0; i < md5SecreteBytes.length; i++){
                tmpStr = Integer.toHexString(0xFF & md5SecreteBytes[i]);
                if (tmpStr.length() == 1) {
                    strBuilder.append('0');
                }
                strBuilder.append(tmpStr);
                    
            }
           return strBuilder.toString();
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(MD5ByteGenerator.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    
}
