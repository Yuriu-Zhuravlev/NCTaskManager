package ua.edu.sumdu.j2se.zhuravlev.tasks.controller;

import ua.edu.sumdu.j2se.zhuravlev.tasks.model.AbstractTaskList;
import ua.edu.sumdu.j2se.zhuravlev.tasks.model.Tasks;
import ua.edu.sumdu.j2se.zhuravlev.tasks.view.View;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class ShowCalendarController extends AbstractController {
    public ShowCalendarController(AbstractTaskList list, View view) {
        super(list, view);
    }

    @Override
    public void execute() {
        Scanner scanner = new Scanner(System.in);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        System.out.println("Input start time of calendar in format yyyy-MM-dd HH:mm:ss (1986-04-08 12:30:32)");
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
        System.out.println("Input end time of calendar in format yyyy-MM-dd HH:mm:ss (1986-04-08 12:30:32)");
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
        try {
            view.set(Tasks.calendar(list,startTime,endTime));
            view.show();
        } catch (IllegalArgumentException ex){
            System.out.println("Error: " + ex.getMessage());
        }
    }
}
