/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.webill.controller;

import com.google.zxing.NotFoundException;
import com.webill.daoApi.UserDao;
import com.webill.model.User;
import com.webill.utils.Constants;
import com.webill.utils.QrReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.sanselan.ImageReadException;
import org.apache.sanselan.Sanselan;
import org.apache.sanselan.common.IImageMetadata;
import org.apache.sanselan.formats.jpeg.JpegImageMetadata;
import org.apache.sanselan.formats.tiff.TiffImageMetadata;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author JimmyHome
 */
@Controller
@SessionAttributes({"imageName", "userID"})
public class FileController {

    @Autowired
    private UserDao jdbcDao;

    @RequestMapping(value = "/fileUpload", method = RequestMethod.POST)
    // @ResponseBody
    public ModelAndView getFileUploaded(HttpServletRequest request, HttpServletResponse response,
            @RequestParam("file") MultipartFile file, Model model, @RequestParam("userID") String userID) throws IOException, ServletException, NotFoundException {

        response.setContentType("text/html;charset=UTF-8");

        ModelAndView modelView = null;

        if (ServletFileUpload.isMultipartContent(request)) {

            OutputStream outFile;
            InputStream fileContent;

            String stringOutput = null; // text for updloaded image details
            String gpsFound = null;     // message for found or not found gps information.
            String meterIDFound = null; // message for found or not found meter no.

            outFile = new FileOutputStream(new File(request.getSession().
                    getServletContext().getRealPath(Constants.UPLOAD_DIRECTORY) + "/_" + userID + "_" + file.getOriginalFilename()));

            fileContent = file.getInputStream();
            int read;
            final byte[] bytes = new byte[1024];
            while ((read = fileContent.read(bytes)) != -1) {
                outFile.write(bytes, 0, read);
            }

//            try { // please remember to remove this block !!!!!!!!!!!
//
//                if (jdbcDao.isQRCodeExist(QrReader.getQrCode(request, file.getOriginalFilename(), userID))) {
//                    meterIDFound="THE QR CODE: " + QrReader.getQrCode(request, file.getOriginalFilename(), userID) +" Already in DB";
//                }
//            } catch (NotFoundException ex) {
//                // when the image has no QR Code can't scan the gps coordinates
//                System.out.println("QR CODE NOT FOUND");
//                Logger.getLogger(FileController.class.getName()).log(Level.SEVERE, null, ex);
//            }
            // add photo name to the session
            model.addAttribute("imageName", file.getOriginalFilename());
            model.addAttribute("userID", userID);

            fileContent.close();
            outFile.close();

            File imageFile = new File(request.getSession().
                    getServletContext().getRealPath(Constants.UPLOAD_DIRECTORY) + "/_" + userID + "_" + file.getOriginalFilename());
            
            try {
//                try{
                if (jdbcDao.isQRCodeExist(QrReader.getQrCode(request, file.getOriginalFilename(), userID))) {
                    meterIDFound = "THE QR CODE: " + QrReader.getQrCode(request, file.getOriginalFilename(), userID) + " Already in DB";
                    jdbcDao.insertImageDetails(userID, file.getOriginalFilename());
                } else {
                    meterIDFound = " No QR READS";
                }
                //               }catch(NotFoundException ex){
//                    meterIDFound = "No QR Reads";
//                }

                if (Sanselan.getMetadata(imageFile) != null) {
                    final IImageMetadata metadata = (IImageMetadata) Sanselan.getMetadata(imageFile);
                    final JpegImageMetadata jpegMetadata = (JpegImageMetadata) metadata;
                    if (jpegMetadata.getExif() != null) {
                        final TiffImageMetadata exifMetadata = jpegMetadata.getExif();
                        if (exifMetadata.getGPS() != null) {
                            final TiffImageMetadata.GPSInfo gpsInfo = exifMetadata.getGPS();
                            if (gpsInfo != null) {
                                final String gpsDescription = gpsInfo.toString();
                                final double longitude = gpsInfo.getLongitudeAsDegreesEast();
                                final double latitude = gpsInfo.getLatitudeAsDegreesNorth();

                                if (jdbcDao.isGpsInformationExist(longitude, latitude)) {

                                    String gpsCord = "GPS Description: " + gpsDescription
                                            + "Longitude (Degrees East):" + longitude
                                            + "Latitude (Degrees North):" + latitude + " and the Found in DB";
                                    stringOutput = gpsCord;
                                    // try{
                                    if (jdbcDao.isQRCodeExist(QrReader.getQrCode(request, file.getOriginalFilename(), userID))) {
                                        meterIDFound = "QR Reads :" + QrReader.getQrCode(request, file.getOriginalFilename(), userID);
                                    } else {
                                        meterIDFound = "No QR Reads";
                                    }
//                                    }catch(NotFoundException ex){
//                                        meterIDFound = "NO QR READS AT EXCEPTION";
//                                    }

                                } else {
                                    // if not found then add gps information
                                    jdbcDao.addGpsInformation(userID, longitude, latitude);

                                    stringOutput = "The Longitude(Degrees East): " + longitude
                                            + " and The Latitude" + latitude + " successfully added";
                                }

                            }
                        } else {
                            stringOutput = "No GPS data!";
                        }
                    } else {
                        stringOutput = "No EXIF data!";
                    }
                } else {
                    stringOutput = "No META data!";
                }

            } catch (ImageReadException ex) {
                Logger.getLogger(FileController.class.getName()).log(Level.SEVERE, null, ex);
            }

            model.addAttribute("stringOutput", stringOutput);
            model.addAttribute("meterIDFound", meterIDFound);

        } else {
            model.addAttribute("errorUpload", "Error: form type must be multipart/form");
        }
        modelView = new ModelAndView("successUpload");
        return modelView;
    }
  
    
}
