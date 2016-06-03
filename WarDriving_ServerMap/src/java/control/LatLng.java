/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

/**
 *
 * @author narcelio
 */
public class LatLng {
    private final double lat;
    private final double lng;
    
    public LatLng(double lat,double lng){
        this.lat = lat;
        this.lng = lng;
    }
    public double getLat(){
        return lat;
    }
    public double getLng(){
        return lng;
    }
    
    public String latLngString(){
        return "{lat: "+lat+",lng:"+lng+"}";
    }
    
    @Override
    public boolean equals(Object o){
        boolean flag = false;
        if(o != null && o instanceof LatLng)
            flag = (this.lat == ((LatLng)o).getLat() && this.lng == ((LatLng)o).getLng());
        return flag;
    }
}
