package ua.edu.sumdu.j2se.zhuravlev.tasks.view;

import ua.edu.sumdu.j2se.zhuravlev.tasks.model.Task;

public class ViewTask implements View<Task> {
    private Task task;

    @Override
    public void show() {
        System.out.println(task);
    }

    @Override
    public void set(Task object) {
        task = object;
    }
}
