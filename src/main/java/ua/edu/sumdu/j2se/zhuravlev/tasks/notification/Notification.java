package ua.edu.sumdu.j2se.zhuravlev.tasks.notification;

import ua.edu.sumdu.j2se.zhuravlev.tasks.model.Task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Set;

public class Notification {
    public static void notify(LocalDateTime time, Set<Task> tasks){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        System.out.print("Notification " + time.format(formatter) + ": tasks to do:");
        for (Task task: tasks) {
            System.out.print(task.getTitle()+" ");
        }
        System.out.println();
    }
}
