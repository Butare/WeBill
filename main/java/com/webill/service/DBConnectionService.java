/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.webill.service;

import com.webill.utils.Constants;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.stereotype.Service;

/**
 *
 * @author JimmyHome
 */

@Service
public class DBConnectionService {
    
    public Connection mySqlConnection = null;
    
    public Connection getDBConnection() throws ClassNotFoundException{
        
        try {
            Class.forName("com.mysql.jdbc.Driver");
            mySqlConnection = DriverManager.getConnection(Constants.getDB_URL()+":"
                    + Constants.getDB_PORTNO()+"/"+Constants.getDB_NAME(),
                    Constants.getDB_USERNAME(), Constants.getDB_PASSWORD());
        } catch (SQLException ex) {
            System.out.println("Error catched");
            Logger.getLogger(DBConnectionService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return mySqlConnection;
    }
    
    public void closeDBConnection(){
    
        try {
            mySqlConnection.close();
        } catch (SQLException ex) {
            Logger.getLogger(DBConnectionService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
