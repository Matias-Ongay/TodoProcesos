package Controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import SQL.ConnectionSQLite;

public class UpdateDatabase {
    public void updateInDatabase(String id, String description, String endDate, String priority, String userId, String status) {
        ConnectionSQLite connectionSQLite = new ConnectionSQLite();

        try (Connection connection = connectionSQLite.conectar()) {
            String sql = "UPDATE task SET description = ?, endDate = ?, priority = ?, userId = ?, status = ? WHERE id = ?";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setString(1, description);
                statement.setString(2, endDate);
                statement.setString(3, priority);
                statement.setString(4, userId);
                statement.setString(5, status);
                statement.setString(6, id);

                statement.executeUpdate();

                System.out.println("Tarea actualizada en la base de datos.");
            }
        } catch (SQLException e) {
            System.out.println("Error al actualizar la tarea en la base de datos: " + e.getMessage());
        }
    }
}
