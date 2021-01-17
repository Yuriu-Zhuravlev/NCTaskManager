package ua.edu.sumdu.j2se.zhuravlev.tasks;

import ua.edu.sumdu.j2se.zhuravlev.tasks.model.AbstractTaskList;
import ua.edu.sumdu.j2se.zhuravlev.tasks.model.ArrayTaskList;
import ua.edu.sumdu.j2se.zhuravlev.tasks.controller.*;
import ua.edu.sumdu.j2se.zhuravlev.tasks.view.*;

import org.apache.log4j.Logger;

import java.util.*;

public class Main {
	private static final Logger log = Logger.getLogger(Main.class);

	public static void shCommandList(){
		System.out.println("Commands:");
		System.out.println("show - show task list;");
		System.out.println("add - add new task to list;");
		System.out.println("edit - edit task in list;");
		System.out.println("delete - delete task from list;");
		System.out.println("calendar - show calendar;");
		System.out.println("help - get command list;");
		System.out.println("quit - end working.");
	}

	public static void main(String[] args){
		log.info("Program started");
		AbstractTaskList list = new ArrayTaskList();
		View viewList = new ViewList();
		View viewTask = new ViewTask();
		View viewCalendar = new ViewCalendar();
		AbstractController openController  = new OpenListController(list, viewList);
		AbstractController showListController = new ShowListController(list,viewList);
		AbstractController addTaskController = new AddTaskController(list, viewTask);
		AbstractController calendarController = new ShowCalendarController(list,viewCalendar);
		AbstractController editController = new EditController(list, viewList, viewTask);
		AbstractController deleteController = new DeleteController(list, viewList, viewTask);
		openController.execute();
		shCommandList();
		System.out.println("type a command:");
		Scanner scanner = new Scanner(System.in);
		String command = scanner.nextLine();
		while (!command.equals("quit")){
			switch (command){
				case "show":
					showListController.execute();
					break;
				case "add":
					addTaskController.execute();
					break;
				case "edit":
					editController.execute();
					break;
				case "delete":
					deleteController.execute();
					break;
				case "calendar":
					calendarController.execute();
					break;
				case "help":
					shCommandList();
					break;
				default:
					System.out.println("unknown command, type help to see possible");
					break;
			}
			System.out.println("type a command:");
			command = scanner.nextLine();
		}
		log.info("Program finished");
	}
}
