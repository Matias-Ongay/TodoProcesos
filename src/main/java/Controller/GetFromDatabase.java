package Controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.mycompany.todoprocesos.Models.Task;

import SQL.ConnectionSQLite;

public class GetFromDatabase implements GettingFromDatabase{
     public List<Task> getAllTasks() {
        List<Task> tasks = new ArrayList<>();
        ConnectionSQLite connectionSQLite = new ConnectionSQLite();

        try (Connection connection = connectionSQLite.conectar()) {
           
            String sql = "SELECT * FROM task";
            try (PreparedStatement statement = connection.prepareStatement(sql);
                 ResultSet resultSet = statement.executeQuery()) {
                
                while (resultSet.next()) {
                    String id = resultSet.getString("id");
                    String description = resultSet.getString("description");
                    String endDate = resultSet.getString("endDate");
                    String priority = resultSet.getString("priority");
                    String userId = resultSet.getString("userId");
                    String status = resultSet.getString("status");
                   
                    Task task = new Task(id, description, endDate, priority, userId, status);
                    tasks.add(task);
                    System.out.println(task+"\n");
                }
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener las tareas de la base de datos: " + e.getMessage());
        }
        return tasks;
    }
}
