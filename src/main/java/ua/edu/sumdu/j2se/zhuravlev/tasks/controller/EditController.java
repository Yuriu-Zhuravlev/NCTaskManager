package ua.edu.sumdu.j2se.zhuravlev.tasks.controller;

import org.apache.log4j.Logger;
import ua.edu.sumdu.j2se.zhuravlev.tasks.model.AbstractTaskList;
import ua.edu.sumdu.j2se.zhuravlev.tasks.model.Task;
import ua.edu.sumdu.j2se.zhuravlev.tasks.view.InputTypes;
import ua.edu.sumdu.j2se.zhuravlev.tasks.view.Questions;
import ua.edu.sumdu.j2se.zhuravlev.tasks.view.View;

import java.time.LocalDateTime;

public class EditController extends AbstractController {
    private View viewTask;
    private static final Logger log = Logger.getLogger(EditController.class);

    public EditController(AbstractTaskList list, View viewList, View viewTask) {
        super(list, viewList);
        this.viewTask = viewTask;
    }

    @Override
    public void execute() {
        view.set(list);
        view.show();
        int id = (int) view.input(InputTypes.ID);
        Task edit;
        try{
            edit = (Task) list.getTask(id).clone();
        } catch (IndexOutOfBoundsException | CloneNotSupportedException e){
            log.error("Caught exception in EditController ",e);
            view.showError(e);
            return;
        }
        viewTask.set(edit);
        viewTask.show();
        boolean answer = viewTask.inputAnswer(Questions.CHANGE_TITLE);
        if (answer){
            String newTitle = (String) viewTask.input(InputTypes.TITLE);
            try {
                edit.setTitle(newTitle);
            } catch (IllegalArgumentException e){
                viewTask.showError(e);
                log.error("Caught exception in EditController ",e);
                return;
            }
        }
        answer = viewTask.inputAnswer(Questions.CHANGE_TIME);
        if (answer){
            answer = viewTask.inputAnswer(Questions.REPEATED);
            if (!answer){
                LocalDateTime time = (LocalDateTime) viewTask.input(InputTypes.TIME);
                try {
                    edit.setTime(time);
                } catch (IllegalArgumentException e){
                    log.error("Caught exception in EditController ",e);
                    viewTask.showError(e);
                    return;
                }
            } else {
                LocalDateTime startTime = (LocalDateTime) viewTask.input(InputTypes.START);
                LocalDateTime endTime = (LocalDateTime) viewTask.input(InputTypes.END);
                int interval = (int) viewTask.input(InputTypes.INTERVAL);
                try {
                    edit.setTime(startTime,endTime,interval);
                } catch (IllegalArgumentException e){
                    log.error("Caught exception in EditController ",e);
                    viewTask.showError(e);
                    return;
                }
            }
        }
        answer = viewTask.inputAnswer(Questions.ACTIVE);
        edit.setActive(answer);
        viewTask.set(edit);
        viewTask.show();
        answer = view.inputAnswer(Questions.SAVE);
        if (answer){
            list.getTask(id).setTitle(edit.getTitle());
            if (edit.isRepeated()){
                list.getTask(id).setTime(edit.getStartTime(), edit.getEndTime(), edit.getRepeatInterval());
            } else {
                list.getTask(id).setTime(edit.getTime());
            }
            list.getTask(id).setActive(edit.isActive());
           save();
        }
    }
}
