/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package SQL;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class ConnectionSQLite implements DatabaseConnector{
    private static final String URL = "jdbc:sqlite:miBaseDeDatos.db"; // Ruta a tu base de datos
    
    public Connection conectar() {
        Connection conn = null;
        try {
            Class.forName("org.sqlite.JDBC");
            // Establece la conexión con la base de datos
            conn = DriverManager.getConnection(URL);
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println(e.getMessage());
        }
        return conn;
    }
    
}
