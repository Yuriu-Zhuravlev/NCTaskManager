package ua.edu.sumdu.j2se.zhuravlev.tasks;

public class Main {

	public static void main(String[] args) {
		System.out.println("Hello");
		Task task = new Task("some", 10, 100, 20);
		task.setActive(true);
		System.out.println(task.nextTimeAfter(0));
		System.out.println(task.nextTimeAfter(9));
		System.out.println(task.nextTimeAfter(30));
		System.out.println(task.nextTimeAfter(40));
		System.out.println(task.nextTimeAfter(10));
		System.out.println(task.nextTimeAfter(95));
		System.out.println(task.nextTimeAfter(100));
		task = new Task("some", 10);
		task.setActive(true);
		System.out.println(task.nextTimeAfter(0));
		System.out.println(task.nextTimeAfter(9));
		System.out.println(task.nextTimeAfter(10));
		System.out.println(task.nextTimeAfter(100));
		task.setActive(false);
		System.out.println(task.nextTimeAfter(0));
	}
}
