package ua.edu.sumdu.j2se.zhuravlev.tasks.view;

import ua.edu.sumdu.j2se.zhuravlev.tasks.model.AbstractTaskList;

public class ViewList implements View<AbstractTaskList> {
    AbstractTaskList list;
    public void show(){
        System.out.println(list);
    }

    @Override
    public void set(AbstractTaskList object) {
        list = object;
    }

}
