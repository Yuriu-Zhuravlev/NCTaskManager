package ua.edu.sumdu.j2se.zhuravlev.tasks;

public class TaskListFactory {
    public static AbstractTaskList createTaskList(ListTypes.types type){
        AbstractTaskList result;
        switch(type){
            case ARRAY:
                result = new ArrayTaskList();
                break;
            case LINKED:
                result = new LinkedTaskList();
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + type);
        }
        return result;
    }
}
