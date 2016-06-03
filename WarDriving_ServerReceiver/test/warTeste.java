
import java.text.ParseException;
import java.util.ArrayList;
import warPacket.Wifi;
import warPacket.WifiDAO;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author brunoavelino
 */
public class warTeste {
    public static void main(String[] args) throws ParseException {
            WifiDAO teste = new WifiDAO();
            for (int i = 0; i < teste.getListOfStringDate().size(); i++) {
                System.out.println(teste.getListOfStringDate().get(i));
            }
            
            System.out.println("**************************");
            
            String data = "2016-15-05 16:40:30";
            ArrayList<Wifi> iter =  teste.getAllWifiInSomeDate(data);
            System.out.println("TAMANHO"+iter.size());
//            for (int i = 0; i < iter.size(); i++) {
//                System.out.println(iter.get(i).getSsid());
//            }
            

//        Wifi temp = new Wifi("PROXIMOOO", "2016-15-05 16:40:30",100,-1000,7000);
//        teste.insert(temp);
            for (int i = 0; i < teste.getAllWifi().size(); i++) {
                System.out.println(teste.getAllWifi().get(i).getId());
                System.out.println(teste.getAllWifi().get(i).getSsid());
                System.out.println(teste.getAllWifi().get(i).getIntensidade());
                System.out.println(teste.getAllWifi().get(i).getData());
                System.out.println(teste.getAllWifi().get(i).getLatitude());
                System.out.println(teste.getAllWifi().get(i).getLongitude());
                System.out.println("********************");
            }  
    }
}
