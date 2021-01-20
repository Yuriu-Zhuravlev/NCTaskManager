package ua.edu.sumdu.j2se.zhuravlev.tasks.view;

public enum Questions {
    REPEATED("Do you want task to be repeated?"),
    ACTIVE("Do you want to set task active?"),
    SAVE("Do you want to save it?"),
    DELETE("Are you sure you want to delete this task?"),
    CHANGE_TITLE("Do you want to change title?"),
    CHANGE_TIME("Do you want to change time?")
    ;

    private String question;

    Questions(String question){
        this.question = question;
    }

    public String getQuestion() {
        return question;
    }
}
