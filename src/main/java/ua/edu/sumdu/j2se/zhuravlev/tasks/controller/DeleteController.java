package ua.edu.sumdu.j2se.zhuravlev.tasks.controller;

import org.apache.log4j.Logger;
import ua.edu.sumdu.j2se.zhuravlev.tasks.model.AbstractTaskList;
import ua.edu.sumdu.j2se.zhuravlev.tasks.model.Task;
import ua.edu.sumdu.j2se.zhuravlev.tasks.view.View;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.Scanner;

public class DeleteController extends AbstractController {
    private View viewTask;
    private static final Logger log = Logger.getLogger(DeleteController.class);

    public DeleteController(AbstractTaskList list, View viewList, View viewTask) {
        super(list, viewList);
        this.viewTask = viewTask;
    }

    @Override
    public void execute() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Choose task you want to delete");
        view.set(list);
        view.show();
        System.out.println("Input id:");
        while(!scanner.hasNextInt())
            System.out.println("try again");
        int id = scanner.nextInt();
        scanner.nextLine();
        Task toDelete;
        try{
            toDelete = list.getTask(id);
        } catch (IndexOutOfBoundsException e){
            log.error("Caught exception in DeleteTaskController ",e);
            System.out.println("Error " + e.getMessage());
            System.out.println("Finished without saving");
            return;
        }
        viewTask.set(toDelete);
        viewTask.show();
        System.out.println("Are you sure you want to delete this task? [y/n]");
        String answer = scanner.nextLine();
        while (!answer.equals("y") && !answer.equals("n")){
            System.out.println("try again");
            answer = scanner.nextLine();
        }
        if (answer.equals("y")){
            list.remove(toDelete);
            try(ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream("save.dat"))) {
                outputStream.writeObject(list);
            } catch (IOException e) {
                log.error("Caught exception in DeleteTaskController ",e);
            }
        }
    }
}
