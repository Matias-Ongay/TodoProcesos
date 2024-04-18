
package com.mycompany.todoprocesos.Models;



/**
 *
 * @author matia
 */
public class Task {
    private String id;
    private String description;
    private String endDate;
    private String priority;
    private String userId;
    private String status;

    public Task(String id, String description, String endDate, String priority, String userId, String status) {
        this.id = id;
        this.description = description;
        this.endDate = endDate;
        this.priority = priority;
        this.userId = userId;
        this.status = status;
    }

    public Task() {
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
     public String toString() {
        return "ID: " + id + ", Descripción: " + description + ", Fecha de finalización: " + endDate +
               ", Prioridad: " + priority + ", Usuario: " + userId + ", Estado: " + status;
    }
    
}