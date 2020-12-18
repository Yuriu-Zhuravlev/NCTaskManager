package ua.edu.sumdu.j2se.zhuravlev.tasks;

import java.time.LocalDateTime;
import java.util.stream.Stream;


public abstract class AbstractTaskList implements Iterable<Task>{
    protected ListTypes.types type;
    protected int maxIndex;

    public int size(){
        return maxIndex;
    }
    public abstract void add(Task task);
    public abstract boolean remove(Task task);
    public abstract Task getTask(int index);

    public AbstractTaskList incoming(LocalDateTime from, LocalDateTime to){
        if (from == null)
            throw new IllegalArgumentException("from is null");
        if (to == null)
            throw new IllegalArgumentException("to is null");
        if (to.isBefore(from))
            throw new IllegalArgumentException("to < from");
        AbstractTaskList result = TaskListFactory.createTaskList(type);
        Stream<Task> resStream = getStream().filter(task -> (task.nextTimeAfter(from).isBefore(to) && (task.nextTimeAfter(from) != null)));
        resStream.forEach(result::add);
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
        StringBuilder result = new StringBuilder(getClass().getSimpleName()+" of " + maxIndex +  " elements: {\n");
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

    public Stream<Task> getStream(){
        Stream.Builder<Task> taskBuilder = Stream.builder();
        for (Task task: this){
            taskBuilder.add(task);
        }
        return taskBuilder.build();
    }
}
