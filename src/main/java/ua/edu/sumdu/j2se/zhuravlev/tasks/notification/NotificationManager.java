package ua.edu.sumdu.j2se.zhuravlev.tasks.notification;

import org.apache.log4j.Logger;
import ua.edu.sumdu.j2se.zhuravlev.tasks.model.AbstractTaskList;
import ua.edu.sumdu.j2se.zhuravlev.tasks.model.Task;
import ua.edu.sumdu.j2se.zhuravlev.tasks.model.Tasks;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.SortedMap;

public class NotificationManager implements Runnable {
    private static final Logger log = Logger.getLogger(NotificationManager.class);
    private AbstractTaskList list;

    public NotificationManager(AbstractTaskList list) {
        this.list = list;
    }

    @Override
    public void run() {
        System.out.println("Notification thread started");
        for(;;){
            if (list.size() != 0) {
                LocalDateTime now = LocalDateTime.now();
                SortedMap<LocalDateTime, Set<Task>> calendar = Tasks.calendar(list,now.minusSeconds(1),now.plusSeconds(1));
                if (calendar.containsKey(now)){
                    Notification.notify(now, calendar.get(now));
                    try {
                        Thread.sleep(1);
                    } catch (InterruptedException e) {
                        log.error("Caught exception in NotificationManager",e);
                    }
                }
            }
        }
    }
}
