package ua.edu.sumdu.j2se.zhuravlev.tasks.controller;

import ua.edu.sumdu.j2se.zhuravlev.tasks.model.AbstractTaskList;
import ua.edu.sumdu.j2se.zhuravlev.tasks.view.View;

import java.io.IOException;

public abstract class AbstractController {
    protected AbstractTaskList list;
    protected View view;

    public AbstractController(AbstractTaskList list, View view) {
        this.list = list;
        this.view = view;
    }

    public abstract void execute();
}
