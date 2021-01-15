package ua.edu.sumdu.j2se.zhuravlev.tasks.view;

import ua.edu.sumdu.j2se.zhuravlev.tasks.model.AbstractTaskList;
import ua.edu.sumdu.j2se.zhuravlev.tasks.model.Task;

public class ViewList implements View<AbstractTaskList> {
    AbstractTaskList list;

    public void show(){
        if (list.size() == 0){
            System.out.println("Your list is empty");
        } else {
            System.out.println("Your list: {");
            int id = 0;
            for (Task task: list){
                System.out.println("    Id = " + id++ + " task = "+task);
            }
            System.out.println("}");
        }
    }

    @Override
    public void set(AbstractTaskList object) {
        list = object;
    }

}
