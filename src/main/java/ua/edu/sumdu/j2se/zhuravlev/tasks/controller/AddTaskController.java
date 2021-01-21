package ua.edu.sumdu.j2se.zhuravlev.tasks.controller;

import org.apache.log4j.Logger;
import ua.edu.sumdu.j2se.zhuravlev.tasks.model.AbstractTaskList;
import ua.edu.sumdu.j2se.zhuravlev.tasks.model.Task;
import ua.edu.sumdu.j2se.zhuravlev.tasks.view.InputTypes;
import ua.edu.sumdu.j2se.zhuravlev.tasks.view.Questions;
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
        boolean answer = view.inputAnswer(Questions.REPEATED);
        if (!answer){
            LocalDateTime time = (LocalDateTime) view.input(InputTypes.TIME);
            try {
                task = new Task(title, time);
            } catch (IllegalArgumentException e){
                log.error(Errors.USER_INPUT.getError(), e);
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
                log.error(Errors.USER_INPUT.getError(), e);
                view.showError(e);
                return;
            }
        }
        answer = view.inputAnswer(Questions.ACTIVE);
        task.setActive(answer);
        show(view,task);
        answer = view.inputAnswer(Questions.SAVE);
        if (answer){
            list.add(task);
            save();
        }
    }
}
