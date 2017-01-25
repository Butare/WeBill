/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.webill.model;

/**
 *
 * @author JimmyHome
 */
public class ImageDetail {
    
    private int month;
    private int meterReading;
    private int consumption;
    private String meterImageName;
    private String billFileName;

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getMeterReading() {
        return meterReading;
    }

    public void setMeterReading(int meterReading) {
        this.meterReading = meterReading;
    }

    public int getConsumption() {
        return consumption;
    }

    public void setConsumption(int consumption) {
        this.consumption = consumption;
    }

    public String getMeterImageName() {
        return meterImageName;
    }

    public void setMeterImageName(String meterImageName) {
        this.meterImageName = meterImageName;
    }

    public String getBillFileName() {
        return billFileName;
    }

    public void setBillFileName(String billFileName) {
        this.billFileName = billFileName;
    }
}
