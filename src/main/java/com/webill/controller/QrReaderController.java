/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.webill.controller;

import com.google.zxing.BinaryBitmap;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.NotFoundException;
import com.google.zxing.RGBLuminanceSource;
import com.google.zxing.Result;
import com.google.zxing.common.HybridBinarizer;
import com.webill.utils.Constants;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

/**
 *
 * @author JimmyHome
 */
@Controller
@SessionAttributes("imageName")
public class QrReaderController {
    
    @RequestMapping(value ="/readQrCode" , method = RequestMethod.POST)
    @ResponseBody
    public String getQrCode (HttpServletRequest request, @ModelAttribute("imageName") String imageName) throws NotFoundException{
         File imageFile = new File(request.getSession().
                    getServletContext().getRealPath(Constants.UPLOAD_DIRECTORY) + "/_" + imageName);
         BufferedImage bufImage;
         BinaryBitmap bitMap;
         Result result;
         
         
         String stringOutput = null;
         
        try {
            bufImage = ImageIO.read(imageFile);
            
            if (bufImage == null) {
                stringOutput = "No bitmap detected. Probably No QR code included";
            } else {
                int[] pixels = bufImage.getRGB(0, 0, bufImage.getWidth(), bufImage.getHeight(), null, 0, bufImage.getWidth());
            RGBLuminanceSource source = new RGBLuminanceSource(bufImage.getWidth(), bufImage.getHeight(), pixels);
                bitMap = new BinaryBitmap(new HybridBinarizer(source));
                MultiFormatReader reader = new MultiFormatReader();
                result = reader.decode(bitMap);
                stringOutput = "The QR code reads "+ result.getText();
            }
            
        } catch (IOException ex) {
            Logger.getLogger(QrReaderController.class.getName()).log(Level.SEVERE, null, ex);
        }
    return stringOutput;
}
    
}
