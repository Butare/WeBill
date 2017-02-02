/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.webill.utils;

import java.io.File;
import net.sourceforge.tess4j.ITesseract;
import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;

/**
 *
 * @author JimmyHome
 */
public class TestOCR {
    public static void main(String[] args) {
        File imageFile = new File("/WEB-INF/photos/imageOCR.jpeg");
        ITesseract instance = new Tesseract();
        try{
        String result = instance.doOCR(imageFile);
        System.out.println("OCR READ IS :"+result);
        }catch(TesseractException e) {
            System.err.println(e.getMessage());
        }
    }
    
}
