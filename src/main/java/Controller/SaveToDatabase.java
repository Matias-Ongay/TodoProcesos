package Controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import SQL.ConnectionSQLite;

public class SaveToDatabase implements SavingDatabase{
        private ConnectionSQLite connectionSQLite;
        
        public void saveToDatabase(String description, String endDate, String priority, String userId, String status) {
   

        try (Connection connection = connectionSQLite.conectar()) {
            String sql = "INSERT INTO task (description, endDate, priority, userId, status) VALUES (?, ?, ?, ?, ?)";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setString(1, description);
                statement.setString(2, endDate);
                statement.setString(3, priority);
                statement.setString(4, userId);
                statement.setString(5, status); 

                statement.executeUpdate();

                System.out.println("Tarea guardada en la base de dtos.");
            }
        } catch (SQLException e) {
            System.out.println("Error al guardar la tarea en la base de datos: " + e.getMessage());
        }
    }
}