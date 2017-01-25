/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.webill.controller;

import com.google.zxing.NotFoundException;
import com.webill.utils.QrReader;
import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

/**
 * 
 * @author JimmyHome
 * 
 * This request handler class prints the scanned meter ID from qr image.
 */
@Controller
@SessionAttributes("imageName")
public class QrReaderController {

    @RequestMapping(value = "/readQrCode", method = RequestMethod.POST)
    @ResponseBody
    public String getQrCode(HttpServletRequest request, @ModelAttribute("imageName") String imageName, @RequestParam("userID")String userID) throws NotFoundException {

        String stringOutput;

        if (QrReader.getQrCode(request, imageName, userID) == null) {
            stringOutput = "No bitmap detected. Probably No QR code included";
        } else {
            stringOutput = "The Code reads is : " + QrReader.getQrCode(request, imageName, userID);
        }

        return stringOutput;

    }
}
