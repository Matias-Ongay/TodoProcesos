/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

/**
 *
 * @author matia
 */

import com.mycompany.todoprocesos.Models.Task;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TaskManager {
    private List<Task> allTasks;
    private List<Task> tasksPending;
    private List<Task> tasksInProgress;
    private List<Task> tasksCompleted;
    private Scanner scanner;
    private SaveToDatabase savingDatabase;
    private UpdateDatabase updateDatabase;

    public TaskManager() {
        allTasks=new ArrayList<>();
        tasksPending = new ArrayList<Task>();
        tasksInProgress = new ArrayList<>();
        tasksCompleted = new ArrayList<>();
        scanner = new Scanner(System.in);
        savingDatabase = new SaveToDatabase();
        updateDatabase = new UpdateDatabase();
    }
    
   public void addTask(TaskController taskController) {
        System.out.print("Ingrese la descripción de la tarea: ");
        String description = scanner.nextLine();

        System.out.print("Ingrese la fecha de finalización de la tarea: ");
        String endDate = scanner.nextLine();

        System.out.print("Ingrese la prioridad de la tarea: ");
        String priority = scanner.nextLine();

        System.out.print("Ingrese el ID del usuario asignado a la tarea: ");
        String userId = scanner.nextLine();

        System.out.print("Ingrese el estado de la tarea (pending/in progress/Completed): ");
        String status = scanner.nextLine();

        savingDatabase.saveToDatabase(description, endDate, priority, userId, status);
    }

    public void moveTaskToInProgress(TaskController taskController) {
        System.out.println("=== Tareas Pendientes ===");
        GetFromDatabase getFromDatabase = new GetFromDatabase();
        allTasks=getFromDatabase.getAllTasks();
        System.out.print("Ingrese el ID de la tarea que desea mover a 'En proceso': ");
        String taskId = scanner.nextLine();

        Task task = findTask(taskId, allTasks);
        if (task != null) {
            allTasks.remove(task);
            task.setStatus("in progress");
            tasksInProgress.add(task);
            updateDatabase.updateInDatabase(taskId, task.getDescription(), task.getEndDate(), task.getPriority(), task.getUserId(), task.getStatus());
            System.out.println("Tarea movida a 'En Proceso' exitosamente.");
        } else {
            System.out.println("No se encontró ninguna tarea con ese ID en 'Pendientes'.");
        }
    }


    public void moveTaskToPending(TaskController taskController) {
        System.out.println("=== Tareas Pendientes ===");
        GetFromDatabase getFromDatabase = new GetFromDatabase();
        allTasks=getFromDatabase.getAllTasks();
        System.out.print("Ingrese el ID de la tarea que desea mover a 'Pendiente': ");
        String taskId = scanner.nextLine();

        Task task = findTask(taskId, allTasks);
        if (task != null) {
            allTasks.remove(task);
            task.setStatus("pending");
            tasksPending.add(task);
            updateDatabase.updateInDatabase(taskId, task.getDescription(), task.getEndDate(), task.getPriority(), task.getUserId(), task.getStatus());
            System.out.println("Tarea movida a 'En Proceso' exitosamente.");
        } else {
            System.out.println("No se encontró ninguna tarea con ese ID en 'Pendientes'.");
        }
    }


    public void moveTaskToCompleted(TaskController taskController) {
        System.out.println("=== Tareas Pendientes ===");
        GetFromDatabase getFromDatabase = new GetFromDatabase();
        allTasks=getFromDatabase.getAllTasks();
        System.out.print("Ingrese el ID de la tarea que desea mover a 'Completado': ");
        String taskId = scanner.nextLine();

        Task task = findTask(taskId, allTasks);
        if (task != null) {
            System.out.printf("tarea que se va a borrar ",task);
            allTasks.remove(task);
            task.setStatus("completed");
            tasksCompleted.add(task);
            updateDatabase.updateInDatabase(taskId, task.getDescription(), task.getEndDate(), task.getPriority(), task.getUserId(), task.getStatus());
            System.out.println("Tarea movida a 'En Proceso' exitosamente.");
        } else {
            System.out.println("No se encontró ninguna tarea con ese ID en 'Pendientes'.");
        }
    }

    public void deleteTask(TaskController taskController) {
        System.out.println("=== Tareas ===");
        GetFromDatabase getFromDatabase = new GetFromDatabase();
        allTasks=getFromDatabase.getAllTasks();
        DeleteFromDatabase deleteFromDatabase = new DeleteFromDatabase();


        System.out.print("Ingrese el ID de la tarea que desea eliminar: ");
        String taskId = scanner.nextLine();

        Task task = findTask(taskId, allTasks);
        if (task != null) {
            allTasks.remove(task);
        } else {
            task = findTask(taskId, tasksInProgress);
            if (task != null) {
            tasksInProgress.remove(task);
            } else {
            task = findTask(taskId, tasksCompleted);
            if (task != null) {
                tasksCompleted.remove(task);
            } else {
                System.out.println("No se encontró ninguna tarea con ese ID.");
                return;
            }
            }
        }
        deleteFromDatabase.deleteFromDatabase(taskId);
        System.out.println("Tarea eliminada exitosamente.");
    }

    private Task findTask(String taskId, List<Task> taskList) {
        for (Task task : taskList) {
            if (task.getId().equals(taskId)) {
                return task;
            }
        }
        return null;
    }

    public List<Task> getTasksPending() {
        return tasksPending;
    }

    public List<Task> getTasksInProgress() {
        return tasksInProgress;
    }

    public List<Task> getTasksCompleted() {
        return tasksCompleted;
    }


}