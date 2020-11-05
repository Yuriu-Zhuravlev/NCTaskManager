package ua.edu.sumdu.j2se.zhuravlev.tasks;

public class Main {

	public static void main(String[] args) {
		ArrayTaskList arrayTaskList = new ArrayTaskList();
		arrayTaskList.add(new Task("A", 0));
		arrayTaskList.add(new Task("B", 1));
		arrayTaskList.add(new Task("C", 2));
		ArrayTaskList res = arrayTaskList.incoming(0,100);
		System.out.println(res.size());
	}
}
