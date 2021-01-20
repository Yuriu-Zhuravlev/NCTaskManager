package ua.edu.sumdu.j2se.zhuravlev.tasks.view;

import ua.edu.sumdu.j2se.zhuravlev.tasks.model.Task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class ViewTask implements View<Task> {
    private Task task;
    private final Scanner scanner = new Scanner(System.in);

    @Override
    public void show() {
        System.out.println("Your task:");
        System.out.println(task);
    }

    @Override
    public void set(Task object) {
        task = object;
    }

    @Override
    public Object input(InputTypes type) {
        switch (type){
            case TITLE:
                return inputTitle();
            case TIME:
                return inputTime("time");
            case START:
                return inputTime("start time");
            case END:
                return inputTime("end time");
            case INTERVAL:
                return interval();
        }
        return null;
    }


    private String inputTitle(){
        System.out.println("Input task title");
        return scanner.nextLine();
    }

    private LocalDateTime inputTime(String type){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        System.out.println("Input task " + type + " in format yyyy-MM-dd HH:mm (1986-04-08 12:30)");
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
        return time;
    }

    private String answer(String question){
        System.out.println(question + " [y/n]");
        String answer = scanner.nextLine();
        while (!answer.equals("y") && !answer.equals("n")){
            System.out.println("try again");
            answer = scanner.nextLine();
        }
        return answer;
    }

    private int interval(){
        System.out.println("Input repeat interval in seconds (integer)");
        while(!scanner.hasNextInt())
            System.out.println("try again");
        int interval = scanner.nextInt();
        scanner.nextLine();
        return interval;
    }
}
