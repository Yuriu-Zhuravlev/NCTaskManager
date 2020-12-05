package ua.edu.sumdu.j2se.zhuravlev.tasks;

import java.util.Objects;

public abstract class AbstractTaskList implements Iterable<Task>{
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

    @Override
    public int hashCode() {
        int result = size();
        for (Task task: this) {
            result = 7 * result + task.hashCode();
        }
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null){
            return  false;
        }
        if (obj == this) {
            return true;
        }
        if (!(obj.getClass() == getClass())) {
            return false;
        }
        if (maxIndex!=((AbstractTaskList) obj).maxIndex){
            return false;
        }
        boolean flag = true;
        for (int i = 0; i <size(); i++){
            if(!getTask(i).equals(((AbstractTaskList) obj).getTask(i)))
                flag = false;
        }
        return flag;
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder(getClass().getSimpleName()+"{\n");
        for (Task task: this){
            result.append("    ").append(task).append("\n");
        }
        result.append("}");
        return result.toString();
    }

    public AbstractTaskList clone() {
        AbstractTaskList copy = TaskListFactory.createTaskList(type);
        for(Task task: this){
            copy.add(task);
        }
        return copy;
    }
}
