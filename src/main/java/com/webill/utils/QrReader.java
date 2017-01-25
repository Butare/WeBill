/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.webill.utils;

import com.google.zxing.BinaryBitmap;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.NotFoundException;
import com.google.zxing.RGBLuminanceSource;
import com.google.zxing.Result;
import com.google.zxing.common.HybridBinarizer;
import com.webill.controller.QrReaderController;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author JimmyHome
 */
//@SessionAttributes({"userName"})
public class QrReader {

    public static String getQrCode(HttpServletRequest request, String imageName, String userID) {

        System.out.println("THE USER IN QRREADER METHOD IS " + userID);
        File imageFile = new File(request.getSession().
                getServletContext().getRealPath(Constants.UPLOAD_DIRECTORY) + "/_" + userID + "_" + imageName);
        
        BufferedImage bufImage;
        BinaryBitmap bitMap;        
        Result result;
        String qrCode = null;        // to get the qr code from the result
        boolean resultFound = false;

        //String stringOutput = null;
        try {
            bufImage = ImageIO.read(imageFile);

            if (bufImage == null) {
                return null;
            } else {
                int[] pixels = bufImage.getRGB(0, 0, bufImage.getWidth(), bufImage.getHeight(), null, 0, bufImage.getWidth());
                RGBLuminanceSource source = new RGBLuminanceSource(bufImage.getWidth(), bufImage.getHeight(), pixels);
                bitMap = new BinaryBitmap(new HybridBinarizer(source));
                MultiFormatReader reader = new MultiFormatReader();

                try {
                    result = reader.decode(bitMap);
                    qrCode = result.getText();
                    resultFound = true;
                } catch (NotFoundException ex) {
                    resultFound = false;
                }
                // return result.getText();
            }

        } catch (IOException ex) {
            Logger.getLogger(QrReaderController.class.getName()).log(Level.SEVERE, null, ex);
        }

        return (resultFound ? qrCode : "");
    }

}
