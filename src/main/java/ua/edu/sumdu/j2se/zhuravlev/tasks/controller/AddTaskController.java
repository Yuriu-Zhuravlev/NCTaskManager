package ua.edu.sumdu.j2se.zhuravlev.tasks.controller;

import org.apache.log4j.Logger;
import ua.edu.sumdu.j2se.zhuravlev.tasks.model.AbstractTaskList;
import ua.edu.sumdu.j2se.zhuravlev.tasks.model.Task;
import ua.edu.sumdu.j2se.zhuravlev.tasks.view.InputTypes;
import ua.edu.sumdu.j2se.zhuravlev.tasks.view.View;

import java.time.LocalDateTime;


public class AddTaskController extends AbstractController {
    private static final Logger log = Logger.getLogger(AddTaskController.class);
    public AddTaskController(AbstractTaskList list, View view) {
        super(list, view);
    }

    @Override
    public void execute(){
        Task task;
        String title = (String) view.input(InputTypes.TITLE);
        String answer = view.inputAnswer("Do you want task to be repeated?");
        if (answer.equals("n")){
            LocalDateTime time = (LocalDateTime) view.input(InputTypes.TIME);
            try {
                task = new Task(title, time);
            } catch (IllegalArgumentException e){
                log.error("Caught exception in AddTaskController ",e);
                view.showError(e);
                return;
            }
        } else {
            LocalDateTime startTime = (LocalDateTime) view.input(InputTypes.START);
            LocalDateTime endTime = (LocalDateTime) view.input(InputTypes.END);
            int interval = (int) view.input(InputTypes.INTERVAL);
            try {
                task = new Task(title, startTime, endTime, interval);
            } catch (IllegalArgumentException e){
                log.error("Caught exception in AddTaskController ",e);
                view.showError(e);
                return;
            }
        }
        answer = view.inputAnswer("Do you want to set task active?");
        if (answer.equals("y")){
            task.setActive(true);
        }
        view.set(task);
        view.show();
        answer = view.inputAnswer("Do you want to save it?");
        if (answer.equals("y")){
            list.add(task);
            save();
        }
    }
}
