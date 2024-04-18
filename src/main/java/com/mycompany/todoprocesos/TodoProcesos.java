/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Project/Maven2/JavaApp/src/main/java/${packagePath}/${mainClassName}.java to edit this template
 */

package com.mycompany.todoprocesos;

import Controler.TaskController;
import Controler.TaskManager;

import static SQL.ConnectionSQLite.createTableTask;
import com.mycompany.todoprocesos.Models.Task;
import java.util.Scanner;


/**
 *
 * @author matia
 */
public class TodoProcesos {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        TaskController taskController = new TaskController();
        TaskManager taskManager = new TaskManager();
        createTableTask();
        Task task = new Task("1","b","c","f","f","g");

        MainMenu menu = new MainMenu(taskController, taskManager, scanner,task);
        menu.displayMenu();
    }
}
