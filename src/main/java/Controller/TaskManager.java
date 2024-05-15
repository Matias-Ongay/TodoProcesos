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

    private List<Task> tasksPending;
    private List<Task> tasksInProgress;
    private List<Task> tasksCompleted;
    private Scanner scanner;

    public TaskManager() {
        tasksPending = new ArrayList<Task>();
        tasksInProgress = new ArrayList<>();
        tasksCompleted = new ArrayList<>();
        scanner = new Scanner(System.in);
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

        System.out.print("Ingrese el estado de la tarea (pending/in progress/completed): ");
        String status = scanner.nextLine();

        taskController.saveToDatabase(description, endDate, priority, userId, status);
    }

    public void moveTaskToInProgress(TaskController taskController) {
        System.out.println("=== Tareas Pendientes ===");
        tasksPending=taskController.getAllTasks();
        listTasks(tasksPending);
        System.out.println(tasksPending);
        System.out.print("Ingrese el ID de la tarea que desea mover a 'En Proceso': ");
        String taskId = scanner.nextLine();

        Task task = findTask(taskId, tasksPending);
        if (task != null) {
            tasksPending.remove(task);
            task.setStatus("In progress");
            tasksInProgress.add(task);
            taskController.updateInDatabase(taskId, task.getDescription(), task.getEndDate(), task.getPriority(), task.getUserId(), task.getStatus());
            System.out.println("Tarea movida a 'En Proceso' exitosamente.");
        } else {
            System.out.println("No se encontró ninguna tarea con ese ID en 'Pendientes'.");
        }
    }

    public void moveTaskToCompleted(TaskController taskController) {
        System.out.println("=== Tareas en Proceso ===");
        listTasks(tasksInProgress);

        System.out.print("Ingrese el ID de la tarea que desea mover a 'Terminadas': ");
        String taskId = scanner.nextLine();

        Task task = findTask(taskId, tasksInProgress);
        if (task != null) {
            tasksInProgress.remove(task);
            task.setStatus("completed");
            tasksCompleted.add(task);
            taskController.updateInDatabase(taskId, taskId, taskId, taskId, taskId, taskId);
            System.out.println("Tarea movida a 'Terminadas' exitosamente.");
        } else {
            System.out.println("No se encontró ninguna tarea con ese ID en 'En Proceso'.");
        }
    }

    public void moveTaskToPending(TaskController taskController) {
        System.out.println("=== Tareas Terminadas ===");
        listTasks(tasksCompleted);

        System.out.print("Ingrese el ID de la tarea que desea mover a 'Pendientes': ");
        String taskId = scanner.nextLine();

        Task task = findTask(taskId, tasksCompleted);
        if (task != null) {
            tasksCompleted.remove(task);
            task.setStatus("pending");
            tasksPending.add(task);
            taskController.updateInDatabase(taskId, taskId, taskId, taskId, taskId, taskId);
            System.out.println("Tarea movida a 'Pendientes' exitosamente.");
        } else {
            System.out.println("No se encontró ninguna tarea con ese ID en 'Terminadas'.");
        }
    }

    public void deleteTask(TaskController taskController) {
        System.out.println("=== Tareas Pendientes ===");
        listTasks(tasksPending);
        System.out.println("=== Tareas en Proceso ===");
        listTasks(tasksInProgress);
        System.out.println("=== Tareas Terminadas ===");
        listTasks(tasksCompleted);

        System.out.print("Ingrese el ID de la tarea que desea eliminar: ");
        String taskId = scanner.nextLine();

        Task task = findTask(taskId, tasksPending);
        if (task != null) {
            tasksPending.remove(task);
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
        taskController.deleteFromDatabase(taskId);
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

    private void listTasks(List<Task> taskList) {
        for (Task task : taskList) {
            System.out.println(task);
        }
        if (taskList.isEmpty()) {
            System.out.println("No hay tareas en esta lista.");
        }
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