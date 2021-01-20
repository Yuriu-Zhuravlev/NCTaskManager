package ua.edu.sumdu.j2se.zhuravlev.tasks.controller;

import org.apache.log4j.Logger;
import ua.edu.sumdu.j2se.zhuravlev.tasks.model.AbstractTaskList;
import ua.edu.sumdu.j2se.zhuravlev.tasks.view.View;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public abstract class AbstractController {
    protected AbstractTaskList list;
    protected View view;
    private static final Logger log = Logger.getLogger(AbstractController.class);

    public AbstractController(AbstractTaskList list, View view) {
        this.list = list;
        this.view = view;
    }

    public abstract void execute();

    public void save(){
        try(ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream("save.dat"))) {
            outputStream.writeObject(list);
        } catch (IOException e) {
            log.error("Caught exception in AddTaskController ",e);
        }
    }

    public static void show (View view, Object o){
        view.set(o);
        view.show();
    }
}
