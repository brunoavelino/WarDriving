/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import java.util.ArrayList;
import warPacket.Wifi;
import warPacket.WifiDAO;

/**
 *
 * @author narcelio
 */
public class MarkerPlot {
    WifiDAO dao;

    ArrayList<Wifi> wifiList;
    ArrayList<LatLng> latlngList;
    ArrayList<String> stringInfoList;
    
    StringBuilder listLatLng;
    StringBuilder finalString;
    
    public MarkerPlot(String date){
        dao = new WifiDAO();
        wifiList = dao.getAllWifiInSomeDate(date);
        latlngList = new ArrayList<>();
        stringInfoList = new ArrayList<>();
        listLatLng = new StringBuilder();
        finalString = new StringBuilder();
        
        startMarker();
    }
    
    private void startMarker(){
        listLatLng.append('[');

        for(Wifi wifi : wifiList){
            if(!latlngList.contains(wifi.getLatLng())){
                latlngList.add(wifi.getLatLng());
            }
        }

        for(LatLng l:latlngList){
            listLatLng.append(l.latLngString()).append(',');
        }

        for(int j = 0; j < latlngList.size() ; j++){
            StringBuilder listInfo = new StringBuilder();
            listInfo.append('[');

            for(Wifi wifi : wifiList){
                if(wifi.getLatLng().equals(latlngList.get(j))){
                    listInfo.append("{SSID:\"")
                            .append(wifi.getSsid())
                            .append("\",Intensidade:")
                            .append(wifi.getIntensidade())
                            .append("},");
                }
            }
            listInfo.delete(listInfo.length() - 1, listInfo.length()).append(']');
            stringInfoList.add(listInfo.toString());
        }

        listLatLng.delete(listLatLng.length() - 1, listLatLng.length()).append(']');
        
        finalString.append("[");
        for(int k = 0 ; k < stringInfoList.size() - 1 ; k++){
            finalString.append(stringInfoList.get(k)).append(',');
        }
        finalString.append(stringInfoList.get(stringInfoList.size() - 1)).append(']');
    }
    
    public String getCoordinates(){
        return listLatLng.toString();
    }
    
    public String getMarkerList(){
        return finalString.toString();
    }
}
