/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package SQL;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import javax.xml.catalog.Catalog;

public class ConnectionSQLite {
    private static final String URL = "jdbc:sqlite:miBaseDeDatos.db"; // Ruta a tu base de datos

    public static Connection conectar() {
        Connection conn = null;
        try {
            // Carga el driver JDBC de SQLite
            Class.forName("org.sqlite.JDBC");
            // Establece la conexión con la base de datos
            conn = DriverManager.getConnection(URL);
            System.out.println("Conexión establecida.");
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println(e.getMessage());
        }
        return conn;
    }
    
    public static void createTableTask() {
        // Script SQL para crear la tabla 'task' si no existe y agregar la columna 'status'
        String sql = "CREATE TABLE IF NOT EXISTS task (\n"
                + "    id INTEGER PRIMARY KEY AUTOINCREMENT,\n"
                + "    description TEXT NOT NULL,\n"
                + "    endDate TEXT NOT NULL,\n"
                + "    priority TEXT NOT NULL,\n"
                + "    userId TEXT NOT NULL,\n"
                + "    status TEXT NOT NULL\n" // Agrega la columna 'status'
                + ");";

        try (Connection conn = conectar();
             Statement stmt = conn.createStatement()) {
            // Crea la tabla si no existe y agrega la columna 'status'
            stmt.execute(sql);
            System.out.println("Tabla 'task' verificada o creada con éxito.");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void deleteDatabaseData(){
        String sql= "DELETE FROM task";
        try (Connection conn = conectar();
             Statement stmt = conn.createStatement()) {
                stmt.execute(sql);
                System.out.println("DELETE OF DATABASE DATA");
        }catch(SQLException e){
            System.out.println("ERROR"+ e.getMessage());
        }
    }
}
