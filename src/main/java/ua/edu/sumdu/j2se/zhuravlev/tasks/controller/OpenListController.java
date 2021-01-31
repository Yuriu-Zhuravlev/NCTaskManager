package ua.edu.sumdu.j2se.zhuravlev.tasks.controller;

import org.apache.log4j.Logger;
import ua.edu.sumdu.j2se.zhuravlev.tasks.model.AbstractTaskList;
import ua.edu.sumdu.j2se.zhuravlev.tasks.model.Task;
import ua.edu.sumdu.j2se.zhuravlev.tasks.view.View;

import java.io.*;

public class OpenListController extends AbstractController {
    private static final Logger log = Logger.getLogger(OpenListController.class);

    public OpenListController(AbstractTaskList list, View view) {
        super(list, view);
    }

    @Override
    public void execute() {
        try (ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(filename))){
            AbstractTaskList listTemp = (AbstractTaskList) inputStream.readObject();
            view.message("Save file loaded");
            log.info("Save file loaded");
            for (Task task: listTemp) {
                list.add(task);
            }
            show(view,list);
        } catch (FileNotFoundException e) {
            view.message("Save file wasn't found, create new save file");
            log.info("Save file created");
            save();
        } catch (IOException e) {
            log.error(Errors.OPEN_FILE.getError(),e);
        } catch (ClassNotFoundException e) {
            log.error(Errors.DESERIALIZATION.getError(),e);
        }
    }
}
