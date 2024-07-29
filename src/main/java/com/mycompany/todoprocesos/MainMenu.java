/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.todoprocesos;

import Controller.GetFromDatabase;
import Controller.TaskController;
import Controller.TaskManager;
import SQL.DataDeleter;
import SQL.DatabaseConnector;

import com.mycompany.todoprocesos.Models.Task;


import java.util.Scanner;

/**
 *
 * @author adri zaanja rota
 */
public class MainMenu {
    private DatabaseConnector databaseConnector;
    private DataDeleter dataDeleter;
    private TaskController taskController;
    private TaskManager taskManager;
    private Scanner scanner;
    public MainMenu(TaskController taskController, TaskManager taskManager, Scanner scanner ,Task task,DatabaseConnector databaseConnector,DataDeleter dataDeleter) {
        this.taskController = taskController;
        this.taskManager = taskManager;
        this.scanner = scanner;
        this.databaseConnector = databaseConnector;
        this.dataDeleter = dataDeleter;
    }

    public void displayMenu() {
        GetFromDatabase getFromDatabase = new GetFromDatabase();
        int choice = 0;
        do {
            databaseConnector.conectar();
            System.out.println("\n=== Menú de Tareas ===");
            System.out.println("1. Ver lista de tareas");
            System.out.println("2. Agregar una tarea a pendiente");
            System.out.println("3. Mover tarea a pendiente");
            System.out.println("4. Mover tarea a in progress");
            System.out.println("5. Mover tarea a completada");
            System.out.println("6. Eliminar una tarea");
            System.out.println("7. Salir");
            System.out.println("8. Borrar datos de la base de datos");
            System.out.print("Ingrese su opción: ");
            try {
                choice = scanner.nextInt();
                scanner.nextLine();
                switch (choice) {
                    case 1:
                        getFromDatabase.getAllTasks();
                        break;
                    case 2:
                        taskManager.addTask(taskController);
                        break;
                    case 3:
                        taskManager.moveTaskToPending(taskController);
                    break;
                    case 4:
                        taskManager.moveTaskToInProgress(taskController);
                        break;
                    case 5:
                        taskManager.moveTaskToCompleted(taskController);
                        break;
                    case 6:
                        taskManager.deleteTask(taskController);
                        break;
                    case 7:
                        System.out.println("Saliendo del programa...");
                        break;
                    case 8:
                        dataDeleter.deleteDatabaseData() ;
                        System.out.println("DELETE");
                        break;
                    default:
                        System.out.println("Opción no válida. Por favor, ingrese un número del 1 al 5.");
                }
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
                scanner.nextLine();
            }
        } while (choice != 9);
    }

   
}