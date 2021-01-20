package ua.edu.sumdu.j2se.zhuravlev.tasks.controller;

import org.apache.log4j.Logger;
import ua.edu.sumdu.j2se.zhuravlev.tasks.model.AbstractTaskList;
import ua.edu.sumdu.j2se.zhuravlev.tasks.model.Task;
import ua.edu.sumdu.j2se.zhuravlev.tasks.model.Tasks;
import ua.edu.sumdu.j2se.zhuravlev.tasks.view.InputTypes;
import ua.edu.sumdu.j2se.zhuravlev.tasks.view.View;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.SortedMap;

public class ShowCalendarController extends AbstractController {
    private static final Logger log = Logger.getLogger(ShowCalendarController.class);

    public ShowCalendarController(AbstractTaskList list, View view) {
        super(list, view);
    }

    @Override
    public void execute() {
        LocalDateTime startTime = (LocalDateTime) view.input(InputTypes.START);
        LocalDateTime endTime = (LocalDateTime) view.input(InputTypes.END);
        try {
            SortedMap<LocalDateTime, Set<Task>> calendar = Tasks.calendar(list,startTime,endTime);
            view.set(calendar);
            view.show();
        } catch (IllegalArgumentException ex){
            view.showError(ex);
            log.error("Caught exception in DeleteTaskController ",ex);
        }
    }
}
