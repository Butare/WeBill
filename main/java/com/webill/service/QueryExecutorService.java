/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.webill.service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author JimmyHome
 */
@Service
public class QueryExecutorService {

    @Autowired  // inject the database connection bean.
    DBConnectionService dbConnectionService;

    /**
     * @param sqlQuery the query to be executed
     * @return ResultSet from the database
     * @throws ClassNotFoundException 
     */
    
    public ResultSet getQueryResult(String sqlQuery) throws ClassNotFoundException {

        ResultSet matchingQuery = null;
        try {
            
            matchingQuery = dbConnectionService.getDBConnection().createStatement().executeQuery(sqlQuery);
            return matchingQuery;
            
        } catch (SQLException ex) {
            Logger.getLogger(QueryExecutorService.class.getName()).log(Level.SEVERE, null, ex);
            return matchingQuery;
        }

    }
}