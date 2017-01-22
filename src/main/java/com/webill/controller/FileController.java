/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.webill.controller;

import com.webill.utils.Constants;
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
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
@SessionAttributes("imageName")
public class FileController {

    @RequestMapping(value = "/fileUpload", method = RequestMethod.POST)
   // @ResponseBody
    public ModelAndView getFileUploaded(HttpServletRequest request, HttpServletResponse response,
            @RequestParam("file") MultipartFile file, Model model) throws IOException, ServletException {

        response.setContentType("text/html;charset=UTF-8");

        ModelAndView modelView = null;
        
        if (ServletFileUpload.isMultipartContent(request)) {

            OutputStream outFile;
            InputStream fileContent;

            outFile = new FileOutputStream(new File(request.getSession().
                    getServletContext().getRealPath(Constants.UPLOAD_DIRECTORY) + "/_" + file.getOriginalFilename()));

            fileContent = file.getInputStream();
            int read;
            final byte[] bytes = new byte[1024];
            while ((read = fileContent.read(bytes)) != -1) {
                outFile.write(bytes, 0, read);
            }
            
            // add photo name to the session
            model.addAttribute("imageName", file.getOriginalFilename());
            
            
            fileContent.close();
            outFile.close();

            String stringOutput = null;

            File imageFile = new File(request.getSession().
                    getServletContext().getRealPath(Constants.UPLOAD_DIRECTORY) + "/_" + file.getOriginalFilename());

            try {
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

                                stringOutput = "GPS Description: " + gpsDescription
                                        + "Longitude (Degrees East):" + longitude
                                        + "Latitude (Degrees North):" + latitude;
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

        } else {
            model.addAttribute("errorUpload", "Error: form type must be multipart/form");
        }
            modelView = new ModelAndView("successUpload");
            return modelView;
    }
}
