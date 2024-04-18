/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controler;

import SQL.ConnectionSQLite;
import com.mycompany.todoprocesos.Models.Task;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author matia
 */
public class TaskController {
    
    public void saveToDatabase(String description, String endDate, String priority, String userId, String status) {
        // Llama al método estático createTableTask() de la clase ConnectionSQLite para crear la tabla si no existe
        ConnectionSQLite.createTableTask();

        // Crea la conexión con la base de datos
        try (Connection connection = ConnectionSQLite.conectar()) {
            // Prepara la sentencia SQL de inserción
            String sql = "INSERT INTO task (description, endDate, priority, userId, status) VALUES (?, ?, ?, ?, ?)";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setString(1, description);
                statement.setString(2, endDate);
                statement.setString(3, priority);
                statement.setString(4, userId);
                statement.setString(5, status); 

                // Ejecuta la sentencia SQL de inserción
                statement.executeUpdate();

                System.out.println("Tarea guardada en la base de datos.");
            }
        } catch (SQLException e) {
            System.out.println("Error al guardar la tarea en la base de datos: " + e.getMessage());
        }
    }

    public void updateInDatabase(String id, String description, String endDate, String priority, String userId, String status) {
        try (Connection connection = ConnectionSQLite.conectar()) {
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

    public void deleteFromDatabase(String id) {
        try (Connection connection = ConnectionSQLite.conectar()) {
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
   
     public String toString(String id, String description, String endDate, String priority, String userId, String status) {
        return "ID: " + id + ", Descripción: " + description + ", Fecha de finalización: " + endDate +
               ", Prioridad: " + priority + ", Usuario: " + userId + ", Estado: " + status;
    }
     public List<Task> getAllTasks() {
        List<Task> tasks = new ArrayList<>();
        try (Connection connection = ConnectionSQLite.conectar()) {
           
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
