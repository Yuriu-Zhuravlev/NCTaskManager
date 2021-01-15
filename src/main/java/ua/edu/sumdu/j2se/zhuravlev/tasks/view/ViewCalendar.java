package ua.edu.sumdu.j2se.zhuravlev.tasks.view;

import ua.edu.sumdu.j2se.zhuravlev.tasks.model.Task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;
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
}
