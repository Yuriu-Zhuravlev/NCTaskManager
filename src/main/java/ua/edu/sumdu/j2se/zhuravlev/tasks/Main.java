package ua.edu.sumdu.j2se.zhuravlev.tasks;

import ua.edu.sumdu.j2se.zhuravlev.tasks.controller.*;
import ua.edu.sumdu.j2se.zhuravlev.tasks.model.*;
import ua.edu.sumdu.j2se.zhuravlev.tasks.view.*;

import java.io.*;
import java.time.LocalDateTime;
import java.util.*;

public class Main {
	public static final LocalDateTime NOW = LocalDateTime.now();
	public static final LocalDateTime FROM_NOW_1 = NOW.plusSeconds(1);
	public static final LocalDateTime FROM_NOW_3 = NOW.plusSeconds(3);
	public static final LocalDateTime FROM_NOW_5 = NOW.plusSeconds(5);
	public static final LocalDateTime FROM_NOW_9 = NOW.plusSeconds(9);
	public static final LocalDateTime FROM_NOW_10 = NOW.plusSeconds(10);
	public static final LocalDateTime FROM_NOW_25 = NOW.plusSeconds(25);
	public static final LocalDateTime FROM_NOW_30 = NOW.plusSeconds(30);
	public static final LocalDateTime FROM_NOW_40 = NOW.plusSeconds(40);
	public static final LocalDateTime FROM_NOW_42 = NOW.plusSeconds(42);
	public static final LocalDateTime FROM_NOW_50 = NOW.plusSeconds(50);
	public static final LocalDateTime FROM_NOW_51 = NOW.plusSeconds(51);
	public static final LocalDateTime FROM_NOW_55 = NOW.plusSeconds(55);
	public static final LocalDateTime FROM_NOW_58 = NOW.plusSeconds(58);
	public static final LocalDateTime FROM_NOW_60 = NOW.plusSeconds(60);
	public static final LocalDateTime FROM_NOW_65 = NOW.plusSeconds(65);
	public static final LocalDateTime FROM_NOW_95 = NOW.plusSeconds(95);
	public static final LocalDateTime FROM_NOW_100 = NOW.plusSeconds(100);
	public static final LocalDateTime FROM_NOW_1000 = NOW.plusSeconds(1000);

	public static final LocalDateTime TODAY = NOW.withHour(0).withMinute(0).withSecond(0);
	public static final LocalDateTime YESTERDAY = TODAY.minusDays(1);
	public static final LocalDateTime ALMOST_TODAY = TODAY.minusSeconds(1);
	public static final LocalDateTime TODAY_1H = TODAY.plusHours(1);
	public static final LocalDateTime TODAY_2H = TODAY.plusHours(2);
	public static final LocalDateTime TODAY_3H = TODAY.plusHours(3);
	public static final LocalDateTime TODAY_4H = TODAY.plusHours(4);
	public static final LocalDateTime TOMORROW = TODAY.plusDays(1);
	public static final LocalDateTime ALMOST_TOMORROW = TOMORROW.minusSeconds(1);

	public static void shCommandList(){
		System.out.println("Commands:");
		System.out.println("show - show task list;");
		System.out.println("add - add new task to list;");
		System.out.println("calendar - show calendar");
		System.out.println("help - get command list;");
		System.out.println("quit - end working");
	}

	public static void main(String[] args){
		AbstractTaskList list = new ArrayTaskList();
		View viewList = new ViewList();
		View viewTask = new ViewTask();
		View viewCalendar = new ViewCalendar();
		AbstractController openController  = new OpenListController(list, viewList);
		openController.execute();
		AbstractController showListController = new ShowListController(list,viewList);
		AbstractController addTaskController = new AddTaskController(list, viewTask);
		AbstractController calendarController = new ShowCalendarController(list,viewCalendar);
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
	}
}
