/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.webill.dao;

import com.webill.model.User;
import static com.webill.utils.Constants.ADMIN_ROLE;
import static com.webill.utils.Constants.CUSTOMER_ROLE;
import com.webill.utils.MD5ByteGenerator;
import java.util.ArrayList;
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
public class UserDaoImpl {
    
    
    private DataSource dataSource;

    private JdbcTemplate  jdbcTemplate = new JdbcTemplate();

    
   public List<Map<String, Object>> getUser(String userID, String password, String userRole){
       
       List<Map<String, Object>> foundUser = null ;
       
       if (userRole.equals(CUSTOMER_ROLE)) {
        String idSecretMatchingQuery = "select * from Users where userID='"+userID+"'and "
                    + "secret='"+MD5ByteGenerator.getMD5Bytes(password)+"'";
        foundUser = jdbcTemplate.queryForList(idSecretMatchingQuery);
       } else if (userRole.equals(ADMIN_ROLE)) {
        String idSecretMatchingQuery = "select * from Admins where userID='"+userID+"'and "
                    + "secretWord='"+MD5ByteGenerator.getMD5Bytes(password)+"'";
        foundUser = jdbcTemplate.queryForList(idSecretMatchingQuery);
       }
       
       return foundUser;
   }
   
   public List<User> getAllUser() {
       final String userListQuery = "select * from Users";
       final List<User> users = new ArrayList<>();
       final List<Map<String, Object>> allUsers = jdbcTemplate.queryForList(userListQuery);
       
       for (Map<String, Object> userRow : allUsers) {
           User user = new User();
           user.setUserID((String) userRow.get("userID"));
           user.setGivenName((String) userRow.get("givenName"));
           user.setSurName((String) userRow.get("surName"));
           user.setLocationLatitude((String) userRow.get("locationLatitude"));
           user.setLocationLongitude((String) userRow.get("locationLongitude"));
           user.setMeterID((String) userRow.get("meterID"));
           user.setAddress((String) userRow.get("address"));
           
           users.add(user); // add a user to the user list
       }
       return users;
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
