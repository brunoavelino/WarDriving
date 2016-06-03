/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package warPacket;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author brunoavelino
 */
public class FabricaDeConexoes {
    private final String dbName = "jdbc:postgresql://";
    private final String endereco = "localhost:5432";
    private final String dbBanco = "dbwar";
    private final String url = dbName + endereco + "/" + dbBanco ;
    private final String driverName = "org.postgresql.Driver";
    private final String user = "postgres";
    private final String password = "qwerty4";
    
    public Connection getConnection(){
//        testando driver
        try {
            Class.forName(driverName);
        } catch (ClassNotFoundException exc) {
            System.err.println("Excecao de classe não encontrada:");
            System.err.println(exc.getMessage());
        }
//        testando Conexao
        try {
            return DriverManager.getConnection(url, user, password);
        } catch (SQLException exc) {
            throw new RuntimeException(exc);
        }
    }
}
