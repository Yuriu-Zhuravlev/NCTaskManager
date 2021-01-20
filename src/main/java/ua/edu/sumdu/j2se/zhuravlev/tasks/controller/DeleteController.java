package ua.edu.sumdu.j2se.zhuravlev.tasks.controller;

import org.apache.log4j.Logger;
import ua.edu.sumdu.j2se.zhuravlev.tasks.model.AbstractTaskList;
import ua.edu.sumdu.j2se.zhuravlev.tasks.model.Task;
import ua.edu.sumdu.j2se.zhuravlev.tasks.view.InputTypes;
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
        view.set(list);
        view.show();
        int id = (int) view.input(InputTypes.ID);
        Task toDelete;
        try{
            toDelete = list.getTask(id);
        } catch (IndexOutOfBoundsException e){
            log.error("Caught exception in DeleteTaskController ",e);
            view.showError(e);
            return;
        }
        viewTask.set(toDelete);
        viewTask.show();
        String answer = view.inputAnswer("Are you sure you want to delete this task?");
        if (answer.equals("y")){
            list.remove(toDelete);
            save();
        }
    }
}
