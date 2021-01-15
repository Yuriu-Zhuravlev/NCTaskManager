package ua.edu.sumdu.j2se.zhuravlev.tasks.controller;

import ua.edu.sumdu.j2se.zhuravlev.tasks.model.AbstractTaskList;
import ua.edu.sumdu.j2se.zhuravlev.tasks.view.View;

public class ShowListController extends AbstractController {
    public ShowListController(AbstractTaskList list, View view) {
        super(list, view);
    }

    @Override
    public void execute() {
        view.set(list);
        view.show();
    }
}
