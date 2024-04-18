/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.todoprocesos;

import Controler.TaskController;
import Controler.TaskManager;
import com.mycompany.todoprocesos.Models.Task;

import static SQL.ConnectionSQLite.deleteDatabaseData;

import java.util.Scanner;

/**
 *
 * @author adri zaanja rota
 */
public class MainMenu {
    private TaskController taskController;
    private TaskManager taskManager;
    private Scanner scanner;
    private Task task;
    public MainMenu(TaskController taskController, TaskManager taskManager, Scanner scanner ,Task task) {
        this.taskController = taskController;
        this.taskManager = taskManager;
        this.scanner = scanner;
        this.task = task;
    }

    public void displayMenu() {
        int choice = 0;
        do {
            System.out.println("\n=== Menú de Tareas ===");
            System.out.println("1. Ver lista de tareas completas");
            System.out.println("2. Agregar una tarea a pendiente");
            System.out.println("3. Modificar estado de la tarea");
            System.out.println("4. Eliminar una tarea");
            System.out.println("5. Salir");
            System.out.print("Ingrese su opción: ");
            
            try {
                choice = scanner.nextInt();
                scanner.nextLine(); // Limpiar el buffer de entrada
                switch (choice) {
                    case 1:
                        taskController.getAllTasks();
                        break;
                    case 2:
                        taskManager.addTask(taskController);
                        break;
                    case 3:
                        taskManager.moveTaskToInProgress(taskController);
                        break;
                    case 4:
                        taskManager.deleteTask(taskController);
                        break;
                    case 5:
                        System.out.println("Saliendo del programa...");
                        break;
                    case 6:
                        deleteDatabaseData();
                        System.out.println("DELETE");
                        break;
                    default:
                        System.out.println("Opción no válida. Por favor, ingrese un número del 1 al 5.");
                }
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
                scanner.nextLine(); // Limpiar el buffer de entrada en caso de error
            }
        } while (choice != 5);
    }

   
}