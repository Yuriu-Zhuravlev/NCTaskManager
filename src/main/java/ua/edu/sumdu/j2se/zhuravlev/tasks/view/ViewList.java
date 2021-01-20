package ua.edu.sumdu.j2se.zhuravlev.tasks.view;

import ua.edu.sumdu.j2se.zhuravlev.tasks.model.AbstractTaskList;
import ua.edu.sumdu.j2se.zhuravlev.tasks.model.Task;

import java.util.Scanner;

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

    @Override
    public Object input(InputTypes type) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Input task id:");
        while(!scanner.hasNextInt())
            System.out.println("try again");
        int id = scanner.nextInt();
        scanner.nextLine();
        return id;
    }

}
