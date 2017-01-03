/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.webill.dao;

import com.webill.utils.MD5ByteGenerator;
import java.util.List;
import java.util.Map;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

/**
 *
 * @author JimmyHome
 */
@Component
public class JdbcDaoImpl {
    
    
    private DataSource dataSource;

    private JdbcTemplate  jdbcTemplate = new JdbcTemplate();

    
   public List<Map<String, Object>> getUser(String userID, String password){
        String idSecretMatchingQuery = "select * from Users where userID='"+userID+"'and "
                    + "secret='"+MD5ByteGenerator.getMD5Bytes(password)+"'";
        return jdbcTemplate.queryForList(idSecretMatchingQuery);
   }
    
    
    public int getUserCount() {
        String sql = "SELECT COUNT(*) FROM Users";
        return jdbcTemplate.queryForObject(sql, Integer.class);
    }
    
    public DataSource getDataSource() {
        return dataSource;
    }

    @Autowired
    public void setDataSource(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }
    
    public JdbcTemplate getJdbcTemplate() {
        return jdbcTemplate;
    }

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    
}
