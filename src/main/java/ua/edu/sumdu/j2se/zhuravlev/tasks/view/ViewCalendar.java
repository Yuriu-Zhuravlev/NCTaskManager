package ua.edu.sumdu.j2se.zhuravlev.tasks.view;

import ua.edu.sumdu.j2se.zhuravlev.tasks.model.Task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.SortedMap;

public class ViewCalendar implements View<SortedMap<LocalDateTime, Set<Task>>> {
    SortedMap<LocalDateTime, Set<Task>> calendar;

    @Override
    public void show() {
        if (calendar.isEmpty()){
            System.out.println("Calendar is empty");
        } else {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            System.out.println("Your calendar:");
            for (Map.Entry<LocalDateTime, Set<Task>> entry : calendar.entrySet()) {
                System.out.println("    " + entry.getKey().format(formatter) + " {");
                for (Task task : entry.getValue()) {
                    System.out.println("        " + task.getTitle());
                }
                System.out.println("    }");
            }
        }
    }

    @Override
    public void set(SortedMap<LocalDateTime, Set<Task>> object) {
        calendar = object;
    }

    @Override
    public Object input(InputTypes type) {
        switch (type){
            case START:
                return inputTime("start time");
            case END:
                return inputTime("end time");
        }
        return null;
    }

    private LocalDateTime inputTime(String type){
        Scanner scanner = new Scanner(System.in);
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
}
