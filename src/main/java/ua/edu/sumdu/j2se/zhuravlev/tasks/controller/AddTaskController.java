package ua.edu.sumdu.j2se.zhuravlev.tasks.controller;

import ua.edu.sumdu.j2se.zhuravlev.tasks.model.AbstractTaskList;
import ua.edu.sumdu.j2se.zhuravlev.tasks.model.Task;
import ua.edu.sumdu.j2se.zhuravlev.tasks.view.View;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class AddTaskController extends AbstractController {
    public AddTaskController(AbstractTaskList list, View view) {
        super(list, view);
    }

    @Override
    public void execute() {
        Task task;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        Scanner scanner = new Scanner(System.in);
        System.out.println("Input task title");
        String title = scanner.nextLine();
        System.out.println("Do you want task to be repeated? [y/n]");
        String answer = scanner.nextLine();
        while (!answer.equals("y") && !answer.equals("n")){
            System.out.println("try again");
            answer = scanner.nextLine();
        }
        if (answer.equals("n")){
            System.out.println("Input task time in format yyyy-MM-dd HH:mm:ss (1986-04-08 12:30:32)");
            String strTime = scanner.nextLine();
            boolean notParsed = false;
            LocalDateTime time = null;
            try {
                time = LocalDateTime.parse(strTime, formatter);
            }catch (DateTimeParseException e) {
                notParsed = true;
            }
            while (notParsed){
                notParsed = false;
                System.out.println("try again");
                strTime = scanner.nextLine();
                try {
                    time = LocalDateTime.parse(strTime, formatter);
                }catch (DateTimeParseException e) {
                    notParsed = true;
                }
            }
            try {
                task = new Task(title, time);
            } catch (IllegalArgumentException e){
                System.out.println("Error: " + e.getMessage());
                System.out.println("Process finished without adding task");
                return;
            }
        } else {
            System.out.println("Input task start time in format yyyy-MM-dd HH:mm:ss (1986-04-08 12:30:32)");
            String strStartTime = scanner.nextLine();
            boolean notParsed = false;
            LocalDateTime startTime = null;
            try {
                startTime = LocalDateTime.parse(strStartTime, formatter);
            }catch (DateTimeParseException e) {
                notParsed = true;
            }
            while (notParsed){
                notParsed = false;
                System.out.println("try again");
                strStartTime = scanner.nextLine();
                try {
                    startTime = LocalDateTime.parse(strStartTime, formatter);
                }catch (DateTimeParseException e) {
                    notParsed = true;
                }
            }
            System.out.println("Input task end time in format yyyy-MM-dd HH:mm:ss (1986-04-08 12:30:32)");
            String strEndTime = scanner.nextLine();
            notParsed = false;
            LocalDateTime endTime = null;
            try {
                endTime = LocalDateTime.parse(strEndTime, formatter);
            }catch (DateTimeParseException e) {
                notParsed = true;
            }
            while (notParsed){
                notParsed = false;
                System.out.println("try again");
                strEndTime = scanner.nextLine();
                try {
                    endTime = LocalDateTime.parse(strEndTime, formatter);
                }catch (DateTimeParseException e) {
                    notParsed = true;
                }
            }
            System.out.println("Input repeat interval in seconds (integer)");
            while(!scanner.hasNextInt())
                System.out.println("try again");
            int interval = scanner.nextInt();
            scanner.nextLine();
            try {
                task = new Task(title, startTime, endTime, interval);
            } catch (IllegalArgumentException e){
                System.out.println("Error: " + e.getMessage());
                System.out.println("Process finished without adding task");
                return;
            }
        }
        System.out.println("Do you want to set task active? [y/n]");
        answer = scanner.nextLine();
        System.out.println(answer);
        while (!answer.equals("y") && !answer.equals("n")){
            System.out.println("try again");
            answer = scanner.nextLine();
        }
        if (answer.equals("y")){
            task.setActive(true);
        }
        System.out.println("Your task:");
        view.set(task);
        view.show();
        System.out.println("Do you want to save it? [y/n]");
        answer = scanner.nextLine();
        while (!answer.equals("y") && !answer.equals("n")){
            System.out.println("try again");
            answer = scanner.nextLine();
        }
        if (answer.equals("y")){
            list.add(task);
            try(ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream("save.dat"))) {
                outputStream.writeObject(list);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
