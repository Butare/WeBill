/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.webill.daoApi;

import com.webill.model.ImageDetail;
import com.webill.model.User;
import java.util.List;
import java.util.Map;

/**
 *
 * @author JimmyHome
 */
public interface UserDao {
    public void saveOrUpdate(User user);
    public User getUserById(String userID);
    public List<User> getUserList();
    public List<Map<String,Object>> getUserLogin(String userID, String password, String userRole);
    public void createUserTable( String userID);
    public boolean isGpsInformationExist(double longitude, double latitude);
    public void addGpsInformation(String userID, double longitude, double latitude);
    public boolean isQRCodeExist(String qrCode);
    public void insertImageDetails(String userID, String imageName);
    public List<ImageDetail> getImageDetailByUser(String userID);
}