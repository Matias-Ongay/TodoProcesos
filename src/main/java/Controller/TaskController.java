/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;


/**
 *
 * @author matia
 */
public class TaskController {

     public String toString(String id, String description, String endDate, String priority, String userId, String status) {
        return "ID: " + id + ", Descripción: " + description + ", Fecha de finalización: " + endDate +
               ", Prioridad: " + priority + ", Usuario: " + userId + ", Estado: " + status;
    }
    
}
