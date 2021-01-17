package ua.edu.sumdu.j2se.zhuravlev.tasks.controller;

import org.apache.log4j.Logger;
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

public class EditController extends AbstractController {
    private View viewTask;
    private static final Logger log = Logger.getLogger(EditController.class);

    public EditController(AbstractTaskList list, View viewList, View viewTask) {
        super(list, viewList);
        this.viewTask = viewTask;
    }

    @Override
    public void execute() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        Scanner scanner = new Scanner(System.in);
        System.out.println("Choose task you want to edit:");
        view.set(list);
        view.show();
        System.out.println("Input task id to edit:");
        while(!scanner.hasNextInt())
            System.out.println("try again");
        int id = scanner.nextInt();
        scanner.nextLine();
        Task edit;
        try{
            edit = (Task) list.getTask(id).clone();
        } catch (IndexOutOfBoundsException | CloneNotSupportedException e){
            log.error("Caught exception in EditController ",e);
            System.out.println("Error " + e.getMessage());
            System.out.println("Finished without saving");
            return;
        }
        System.out.println("Your task:");
        viewTask.set(edit);
        viewTask.show();
        System.out.println("Your current title: " + edit.getTitle() + " do you want to change it?[y/n]");
        String answer = scanner.nextLine();
        while (!answer.equals("y") && !answer.equals("n")){
            System.out.println("try again");
            answer = scanner.nextLine();
        }
        if (answer.equals("y")){
            System.out.println("Input new title:");
            String newTitle = scanner.nextLine();
            try {
                edit.setTitle(newTitle);
            } catch (IllegalArgumentException e){
                System.out.println("Error " + e.getMessage());
                System.out.println("Finished without saving");
                log.error("Caught exception in EditController ",e);
                return;
            }
        }
        String currentTime = edit.isRepeated() ? "start = " + edit.getStartTime().format(formatter) + ", end = " +
                edit.getEndTime().format(formatter) + ", repeat interval = " + edit.getRepeatInterval() :
                "time = " + edit.getTime().format(formatter);
        System.out.println("Your current time: " + currentTime);
        System.out.println("Do you want to change it?[y/n]");
        answer = scanner.nextLine();
        while (!answer.equals("y") && !answer.equals("n")){
            System.out.println("try again");
            answer = scanner.nextLine();
        }
        if (answer.equals("y")){
            System.out.println("Do you want task to be repeated? [y/n]");
            answer = scanner.nextLine();
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
                    edit.setTime(time);
                } catch (IllegalArgumentException e){
                    log.error("Caught exception in EditController ",e);
                    System.out.println("Error: " + e.getMessage());
                    System.out.println("Finished without saving");
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
                    edit.setTime(startTime,endTime,interval);
                } catch (IllegalArgumentException e){
                    log.error("Caught exception in EditController ",e);
                    System.out.println("Error: " + e.getMessage());
                    System.out.println("Finished without saving");
                    return;
                }
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
            edit.setActive(true);
        } else {
            edit.setActive(false);
        }
        System.out.println("Your edited task:");
        viewTask.set(edit);
        viewTask.show();
        System.out.println("Do you want to save it? [y/n]");
        answer = scanner.nextLine();
        while (!answer.equals("y") && !answer.equals("n")){
            System.out.println("try again");
            answer = scanner.nextLine();
        }
        if (answer.equals("y")){
            list.getTask(id).setTitle(edit.getTitle());
            if (edit.isRepeated()){
                list.getTask(id).setTime(edit.getStartTime(), edit.getEndTime(), edit.getRepeatInterval());
            } else {
                list.getTask(id).setTime(edit.getTime());
            }
            list.getTask(id).setActive(edit.isActive());
            try(ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream("save.dat"))) {
                outputStream.writeObject(list);
            } catch (IOException e) {
                log.error("Caught exception in EditController ",e);
            }
        }
    }
}
