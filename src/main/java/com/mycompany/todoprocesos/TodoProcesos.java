/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Project/Maven2/JavaApp/src/main/java/${packagePath}/${mainClassName}.java to edit this template
 */

package com.mycompany.todoprocesos;


import com.mycompany.todoprocesos.Models.Task;

import Controller.TaskController;
import Controller.TaskManager;
import SQL.CreateTableSQLite;

import java.util.Scanner;


/**
 *
 * @author matia
 */
public class TodoProcesos {

    public static void main(String[] args) {
        CreateTableSQLite createTableSQLite = new CreateTableSQLite();
        Scanner scanner = new Scanner(System.in);
        TaskController taskController = new TaskController();
        TaskManager taskManager = new TaskManager();
        createTableSQLite.createTableTask();
        Task task = new Task();

        MainMenu menu = new MainMenu(taskController, taskManager, scanner,task);
        menu.displayMenu();
    }
}
