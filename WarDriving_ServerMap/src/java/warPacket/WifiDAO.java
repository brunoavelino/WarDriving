/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package warPacket;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;

/**
 *
 * @author brunoavelino
 */
public class WifiDAO {
    private final Connection connection;
    
    public WifiDAO(){
        this.connection = new FabricaDeConexoes().getConnection();
    }

    public void insert(Wifi wifi){
        String tableName = "schemawar.tablewar";
//        String cmd = "insert into schemawar.tablewar(ssid,intensidade,latitude,longitude,data) values('ime-wifi',10,'s1020','l3381','1998-10-01')";

        String cmd = "insert into " + tableName + "(ssid,intensidade,latitude,longitude,data) " + "values(?,?,?,?,?)";
                
        try (PreparedStatement stmt = connection.prepareStatement(cmd)){
            stmt.setString(1, wifi.getSsid());
            stmt.setInt(2, wifi.getIntensidade());
            stmt.setDouble(3, wifi.getLatitude());
            stmt.setDouble(4, wifi.getLongitude());
            stmt.setString(5, wifi.getData());
            stmt.execute();
            stmt.close();
        } 
        catch (SQLException exc) {
            throw new RuntimeException(exc);
        }
        
    }
    
    public ArrayList<Wifi> getAllWifi(){
        ArrayList<Wifi> wifis = new ArrayList<Wifi>();
        
        String cmd = "select * from schemawar.tablewar order by tablewar.id ASC";
        
        try (PreparedStatement stmt = connection.prepareStatement(cmd)){
            ResultSet resultSql = stmt.executeQuery();
            while (resultSql.next()) {
                Wifi wifiTemp = new Wifi(resultSql.getInt("id"), resultSql.getString("ssid"), resultSql.getTimestamp("data").toString(), resultSql.getInt("intensidade"), resultSql.getDouble("latitude"), resultSql.getDouble("longitude"));
                wifis.add(wifiTemp);
            }
            stmt.close();
            resultSql.close();
        } 
        catch (SQLException exc) {
            throw new RuntimeException(exc);
        }
        return wifis;
    }
    
    public ArrayList<Wifi> getAllWifiInSomeDate(String data){
        ArrayList<Wifi> wifis = new ArrayList<Wifi>();
        
        String cmd = "select * from schemawar.tablewar where data = ?; ";
        
        try (PreparedStatement stmt = connection.prepareStatement(cmd)){
            stmt.setString(1, data);
            ResultSet resultSql = stmt.executeQuery();
            while (resultSql.next()) {
                Wifi wifiTemp = new Wifi(resultSql.getInt("id"), resultSql.getString("ssid"), resultSql.getString("data"), resultSql.getInt("intensidade"), resultSql.getDouble("latitude"), resultSql.getDouble("longitude"));
                wifis.add(wifiTemp);
            }
            stmt.close();
            resultSql.close();
        } 
        catch (SQLException exc) {
            throw new RuntimeException(exc);
        }
        return wifis;
    }
    
    public ArrayList<String> getListOfStringDate(){
        ArrayList<String> datas = new ArrayList<String>();
        
        String cmd = "select distinct data from schemawar.tablewar;";
        
        try (PreparedStatement stmt = connection.prepareStatement(cmd)){
            ResultSet resultSql = stmt.executeQuery();
            while (resultSql.next() ) {
                String dataTemp = resultSql.getString("data");
                datas.add(dataTemp);
            }
            stmt.close();
            resultSql.close();
        } 
        catch (SQLException exc) {
            throw new RuntimeException(exc);
        }
        return datas;
    }
    
    public String getListOfStringDateJSP(){
        StringBuilder sb = new StringBuilder();
        sb.append('[');
        
        String cmd = "select distinct data from schemawar.tablewar;";
        
        try (PreparedStatement stmt = connection.prepareStatement(cmd)){
            ResultSet resultSql = stmt.executeQuery();
            while (resultSql.next() ) {
                String dataTemp = "\'"+resultSql.getString("data")+"\',";
                sb.append(dataTemp);
            }
            stmt.close();
            resultSql.close();
        } 
        catch (SQLException exc) {
            throw new RuntimeException(exc);
        }
        
        sb.delete(sb.length() - 1, sb.length()).append(']');
        return sb.toString();
    }
}   
    
