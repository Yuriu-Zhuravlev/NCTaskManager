package ua.edu.sumdu.j2se.zhuravlev.tasks.controller;

import org.apache.log4j.Logger;
import ua.edu.sumdu.j2se.zhuravlev.tasks.model.AbstractTaskList;
import ua.edu.sumdu.j2se.zhuravlev.tasks.model.Task;
import ua.edu.sumdu.j2se.zhuravlev.tasks.model.Tasks;
import ua.edu.sumdu.j2se.zhuravlev.tasks.view.View;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;
import java.util.Set;
import java.util.SortedMap;

public class ShowCalendarController extends AbstractController {
    private static final Logger log = Logger.getLogger(ShowCalendarController.class);

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
            SortedMap<LocalDateTime, Set<Task>> calendar = Tasks.calendar(list,startTime,endTime);
            view.set(calendar);
            view.show();
        } catch (IllegalArgumentException ex){
            System.out.println("Error: " + ex.getMessage());
            log.error("Caught exception in DeleteTaskController ",ex);
        }
    }
}
