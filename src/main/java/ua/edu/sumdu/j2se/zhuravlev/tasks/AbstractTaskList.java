package ua.edu.sumdu.j2se.zhuravlev.tasks;

public abstract class AbstractTaskList{
    protected ListTypes.types type;
    protected int maxIndex;

    public int size(){
        return maxIndex;
    }
    public abstract void add(Task task);
    public abstract boolean remove(Task task);
    public abstract Task getTask(int index);
    public AbstractTaskList incoming(int from, int to){
        if (from < 0)
            throw new IllegalArgumentException("from < 0");
        if (to < from)
            throw new IllegalArgumentException("to < from");
        AbstractTaskList result = TaskListFactory.createTaskList(type);
        for (int i = 0; i < size(); i++) {
            if ((getTask(i).nextTimeAfter(from) <= to) && (getTask(i).nextTimeAfter(from) != -1)){
                result.add(getTask(i));
            }
        }
        return result;
    }
}
