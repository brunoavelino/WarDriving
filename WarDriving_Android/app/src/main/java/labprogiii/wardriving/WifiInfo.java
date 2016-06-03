package labprogiii.wardriving;

/**
 * Created by USER on 30/04/2016.
 */
public class WifiInfo {
    private String SSID;
    private int Intensidade;
    private double Latitude;
    private double Longitude;
    private String Data;

    public String getData() {
        return Data;
    }

    public void setData(String data) {
        Data = data;
    }

    public String getSSID() {
        return SSID;
    }

    public void setSSID(String SSID) {
        this.SSID = SSID;
    }

    public int getIntensidade() {
        return Intensidade;
    }

    public void setIntensidade(int intensidade) {
        Intensidade = intensidade;
    }

    public double getLatitude() {
        return Latitude;
    }

    public void setLatitude(double latitude) {
        Latitude = latitude;
    }

    public double getLongitude() {
        return Longitude;
    }

    public void setLongitude(double longitude) {
        Longitude = longitude;
    }

    WifiInfo( String SSID, int Intensidade, double Latitude, double Longitude, String Data){
        this.SSID = SSID;
        this.Intensidade = Intensidade;
        this.Latitude = Latitude;
        this.Longitude = Longitude;
        this.Data = Data;
    }
}
