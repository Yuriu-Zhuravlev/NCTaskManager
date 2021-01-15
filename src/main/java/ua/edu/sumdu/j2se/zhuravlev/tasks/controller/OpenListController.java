package ua.edu.sumdu.j2se.zhuravlev.tasks.controller;

import ua.edu.sumdu.j2se.zhuravlev.tasks.model.AbstractTaskList;
import ua.edu.sumdu.j2se.zhuravlev.tasks.model.ArrayTaskList;
import ua.edu.sumdu.j2se.zhuravlev.tasks.model.Task;
import ua.edu.sumdu.j2se.zhuravlev.tasks.view.View;

import java.io.*;

public class OpenListController extends AbstractController {
    public OpenListController(AbstractTaskList list, View view) {
        super(list, view);
    }

    @Override
    public void execute() {
        try (ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream("save.dat"))){
            AbstractTaskList listTemp = (AbstractTaskList) inputStream.readObject();
            System.out.println("Save file loaded");
            for (Task task: listTemp) {
                list.add(task);
            }
            view.set(list);
            view.show();
        } catch (FileNotFoundException e) {
            System.out.println("Save file wasn't found, create new save file");
            try(ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream("save.dat"))) {
                outputStream.writeObject(list);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
