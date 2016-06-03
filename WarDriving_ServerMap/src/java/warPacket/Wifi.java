/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package warPacket;

import control.LatLng;
//import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author brunoavelino
 */
public class Wifi {
    private int id;
    private String ssid;
    private String data;
    private int intensidade;
    private double latitude;
    private double longitude;
    private LatLng latlng;

    public Wifi(int id, String ssid, String data, int intensidade, double latitude, double longitude) {
        this.id = id;
        this.ssid = ssid;
        this.data = data;
        this.intensidade = intensidade;
        this.latitude = latitude;
        this.longitude = longitude;
        this.latlng = new LatLng(latitude,longitude);
    }

    public Wifi(String ssid, String data, int intensidade, double latitude, double longitude) throws ParseException {
        this.ssid = ssid;
        this.data = data;
        this.intensidade = intensidade;
        this.latitude = latitude;
        this.longitude = longitude;
        this.latlng = new LatLng(latitude,longitude);
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSsid() {
        return ssid;
    }

    public void setSsid(String ssid) {
        this.ssid = ssid;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
    
    public Date getDataToDate(String data) throws ParseException{
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date parsed = format.parse(data);
        return parsed;
    }
     
    public Date getDataFormated() throws ParseException{
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date parsed = format.parse(this.data);
        return parsed;
    }

    public int getIntensidade() {
        return intensidade;
    }

    public void setIntensidade(int intensidade) {
        this.intensidade = intensidade;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }
    
    public LatLng getLatLng(){
        return latlng;
    }
}
    
   
