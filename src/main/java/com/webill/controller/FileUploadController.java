/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.webill.controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import javax.servlet.http.Part;
import org.apache.sanselan.ImageReadException;
import org.apache.sanselan.Sanselan;
import org.apache.sanselan.common.IImageMetadata;
import org.apache.sanselan.formats.jpeg.JpegImageMetadata;
import org.apache.sanselan.formats.tiff.TiffImageMetadata;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
/**
 *
 * @author JimmyHome
 */
@Controller
@MultipartConfig
public class FileUploadController {
    Logger log = Logger.getLogger("Upload");
    @RequestMapping(value="/uploadFile", method = RequestMethod.POST)
    @ResponseBody
    protected String processRequest(HttpServletRequest request, HttpServletResponse response, 
                                    @RequestParam("imageFile") MultipartFile file) throws IOException, ServletException {
        
         String gps = null;
         
        if (file.isEmpty())
            return file.getOriginalFilename();
        
        try (PrintWriter out = response.getWriter()) {
            // Get the file from the request. It is a multipart file.
            final Part filePart = request.getPart("imageFile");
            OutputStream outFile;
            InputStream fileContent;
            
            // Get the file name
            final String partHeader = filePart.getHeader("content-dispostion");
            String fileName = "";
            for (String content : filePart.getHeader("content-disposition").split(";")) {
                if (content.trim().startsWith("fileName")) {
                    fileName = content.substring(content.indexOf('=') + 1).trim().replace("\"","");
                    break;
                }
            }
            
            // wer will sae the file in the temporary folder of the system.
            // we will add _ at the beginning of the file name.
            outFile = new FileOutputStream(
                        new File(System.getProperty("java.io.tmpdir") + "/_"
                                + fileName));
                fileContent = filePart.getInputStream();
                int read;
                final byte[] bytes = new byte[1024];
                while ((read = fileContent.read(bytes)) != -1) {
                    outFile.write(bytes, 0, read);
                }
                fileContent.close();
                outFile.close();
                
               
                
                File imageFile = new File(System.getProperty("java.io.tmpdir") + "/_"
                        + fileName);
                if (Sanselan.getMetadata(imageFile) != null) {
                    final IImageMetadata metadata = (IImageMetadata) Sanselan.getMetadata(imageFile);
                    final JpegImageMetadata jpegMetadata = (JpegImageMetadata) metadata;
                    if (jpegMetadata.getExif() != null) {
                        final TiffImageMetadata exifMetadata = jpegMetadata.getExif();
                        if (null != exifMetadata.getGPS()) {
                            final TiffImageMetadata.GPSInfo gpsInfo = exifMetadata.getGPS();
                            if (null != gpsInfo) {
                                final String gpsDescription = gpsInfo.toString();
                                final double longitude = gpsInfo.getLongitudeAsDegreesEast();
                                final double latitude = gpsInfo.getLatitudeAsDegreesNorth();

                                out.println("    " + "GPS Description: "
                                        + gpsDescription);
                                gps = gpsDescription; // remove this line.
                                out.println("    "
                                        + "GPS Longitude (Degrees East): " + longitude);
                                out.println("    "
                                        + "GPS Latitude (Degrees North): " + latitude);
                            }
                        } else {
                            out.println("No GPS data!");
                        }
                    } else {
                        out.println("No EXIF data!");
                    }
                } else {
                    out.println("No META data!");
                }
                
            
        } catch (FileNotFoundException | ImageReadException ex) {
         log.log(Level.INFO, "Error in the Servlet: {0}", ex.getMessage());
        }
        return "Successfully uploaded"+ gps;
    }
    
}
