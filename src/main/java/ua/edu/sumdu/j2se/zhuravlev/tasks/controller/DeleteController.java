package ua.edu.sumdu.j2se.zhuravlev.tasks.controller;

import org.apache.log4j.Logger;
import ua.edu.sumdu.j2se.zhuravlev.tasks.model.AbstractTaskList;
import ua.edu.sumdu.j2se.zhuravlev.tasks.model.Task;
import ua.edu.sumdu.j2se.zhuravlev.tasks.view.InputTypes;
import ua.edu.sumdu.j2se.zhuravlev.tasks.view.Questions;
import ua.edu.sumdu.j2se.zhuravlev.tasks.view.View;


public class DeleteController extends AbstractController {
    private View viewTask;
    private static final Logger log = Logger.getLogger(DeleteController.class);

    public DeleteController(AbstractTaskList list, View viewList, View viewTask) {
        super(list, viewList);
        this.viewTask = viewTask;
    }

    @Override
    public void execute() {
        show(view,list);
        int id = (int) view.input(InputTypes.ID);
        Task toDelete;
        try{
            toDelete = list.getTask(id);
        } catch (IndexOutOfBoundsException e){
            log.error("Caught IllegalArgumentException in DeleteController caused by user's input " +
                    e.getMessage(),e);
            view.showError(e);
            return;
        }
        show(viewTask,toDelete);
        boolean answer = view.inputAnswer(Questions.DELETE);
        if (answer){
            list.remove(toDelete);
            save();
        }
    }
}
