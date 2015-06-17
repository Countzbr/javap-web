/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.lasalle.av2.CONNECTION;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author salgado
 */
public class ConnectionFactory {
    private String connectionString = "jdbc:postgresql://localhost:5432/postgres";
    private String user="postgres";
    private String pass= "lasalle2014";

    public Connection getConnection() {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ConnectionFactory.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            
            return DriverManager.getConnection(
          connectionString, user, pass);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
