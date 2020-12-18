package ua.edu.sumdu.j2se.zhuravlev.tasks;

import java.time.LocalDateTime;
import java.util.*;

public class Tasks {
    public static Iterable<Task> incoming(Iterable<Task> tasks, LocalDateTime start,
                            LocalDateTime end) {
        List<Task> res = new LinkedList<>();
        Iterator<Task> it = tasks.iterator();
        while (it.hasNext()){
            Task temp = it.next();
            if ( (temp.nextTimeAfter(start) != null) && (temp.nextTimeAfter(start).isBefore(end) ||
                    temp.nextTimeAfter(start).equals(end)) ){
                res.add(temp);
            }
        }
        return res;
    }

    public static SortedMap<LocalDateTime, Set<Task>> calendar(Iterable<Task> tasks, LocalDateTime start,
                                                      LocalDateTime end){
        SortedMap<LocalDateTime, Set<Task>> result = new TreeMap<>();
        tasks = Tasks.incoming(tasks,start,end);
        for (Task task:tasks) {
            LocalDateTime temp = task.nextTimeAfter(start);
            while ( (temp != null) && (temp.isBefore(end) || temp.equals(end))){
                if (!result.containsKey(temp)){
                    result.put(temp,new HashSet<>());
                }
                result.get(temp).add(task);
                temp = task.nextTimeAfter(temp);
            }
        }
        return result;
    }
}
