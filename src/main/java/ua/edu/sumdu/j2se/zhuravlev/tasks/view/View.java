package ua.edu.sumdu.j2se.zhuravlev.tasks.view;

import java.util.Scanner;

public interface View<T> {
    void show();
    void set(T object);
    Object input(InputTypes type);

    default void showError(Throwable e){
        System.out.println("Error: " + e.getMessage());
        System.out.println("Process finished without saving");
    }

    default String inputAnswer(String question){
        Scanner scanner = new Scanner(System.in);
        System.out.println(question + " [y/n]");
        String answer = scanner.nextLine();
        while (!answer.equals("y") && !answer.equals("n")){
            System.out.println("try again");
            answer = scanner.nextLine();
        }
        return answer;
    }

    default void message(String message){
        System.out.println(message);
    }
}
