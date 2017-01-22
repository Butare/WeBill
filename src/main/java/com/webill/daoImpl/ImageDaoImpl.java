/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.webill.daoImpl;

import javax.sql.DataSource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

/**
 *
 * @author JimmyHome
 */
@Component
public class ImageDaoImpl {
    
    private DataSource dataSource;
    private final JdbcTemplate jdbcTemplate = new JdbcTemplate();
}
