package Controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import SQL.ConnectionSQLite;

public class DeleteFromDatabase implements DeletingFromDatabase{
        public void deleteFromDatabase(String id) {
        ConnectionSQLite connectionSQLite = new ConnectionSQLite();

        try (Connection connection = connectionSQLite.conectar()) {
            
            String sql = "DELETE FROM task WHERE id = ?";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setString(1, id);
                statement.executeUpdate();

                System.out.println("Tarea eliminada de la base de datos.");
            }
        } catch (SQLException e) {
            System.out.println("Error al eliminar la tarea de la base de datos: " + e.getMessage());
        }
    }
   
}
