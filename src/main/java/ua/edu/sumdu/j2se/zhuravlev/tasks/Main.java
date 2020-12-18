package ua.edu.sumdu.j2se.zhuravlev.tasks;

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

	public static void main(String[] args) throws CloneNotSupportedException {
		Task daily = new Task("Daily", YESTERDAY, TOMORROW, 3600*24);
		Task hourly = new Task("Hourly", TODAY, TOMORROW, 3600);
		Task every3h = new Task("Every 3 hours", TODAY_1H, TOMORROW, 3*3600);
		daily.setActive(true);
		hourly.setActive(true);
		every3h.setActive(true);
		SortedMap<LocalDateTime, Set<Task>> result = Tasks.calendar(new HashSet<>(Arrays.asList(daily, hourly, every3h)), ALMOST_TODAY, TODAY_4H);
		System.out.println(result);
		/*Task t1 = new Task("A", NOW);
		Task t2 = new Task("B", FROM_NOW_1);
		Task t3 = new Task("C", FROM_NOW_3);
		List<Task> input = Arrays.asList(t1, t2, t3);
		Iterable<?> res = Tasks.incoming(input, NOW, FROM_NOW_1000);*/
		/*List<Task> input = Arrays.asList(new Task("Simple IN", FROM_NOW_55),
				new Task("Simple OUT", FROM_NOW_10),
				new Task("Inactive OUT", NOW, FROM_NOW_1000, 1),
				new Task("Simple bound OUT", FROM_NOW_50),
				new Task("Simple bound IN", FROM_NOW_60),
				new Task("Repeat inside IN", FROM_NOW_51, FROM_NOW_58, 2),
				new Task("Repeat outside IN", NOW, FROM_NOW_100, 5),
				new Task("Repeat outside OUT", NOW, FROM_NOW_100, 22),
				new Task("Repeat left OUT", NOW, FROM_NOW_40, 1),
				new Task("Repeat right OUT", FROM_NOW_65, FROM_NOW_100, 1),
				new Task("Repeat left intersect IN 1", NOW, FROM_NOW_55, 13),
				new Task("Repeat left intersect IN 2", NOW, FROM_NOW_60, 30),
				new Task("Repeat left intersect OUT", NOW, FROM_NOW_55, 22),
				new Task("Repeat right intersect IN", FROM_NOW_55, FROM_NOW_100, 20));
		for (Task task:input) {
			task.setActive(true);
		}
		input.get(2).setActive(false);
		Iterable<Task> res = Tasks.incoming(input, FROM_NOW_50, FROM_NOW_60);
		for(Task task: res){
			System.out.println(task);
		}*/
	}
}
