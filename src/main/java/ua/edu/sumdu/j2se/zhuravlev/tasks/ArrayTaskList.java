package ua.edu.sumdu.j2se.zhuravlev.tasks;

public class ArrayTaskList extends AbstractTaskList {
    private int maxSize;
    private Task[] tasks;
    private final int toAdd = 10;

    public ArrayTaskList() {
        maxSize = toAdd;
        tasks = new Task[maxSize];
        maxIndex = 0;
        type = ListTypes.types.LINKED;
    }

    public Task getTask(int index){
        if (index >= maxIndex)
            throw new IndexOutOfBoundsException("index > size");
        return tasks[index];
    }

    public void add(Task task){
        if (task == null)
            throw new NullPointerException("task is null");
        if (size() == maxSize){
            Task[] a = tasks;
            maxSize += toAdd;
            tasks = new Task[maxSize];
            if (size() >= 0) System.arraycopy(a, 0, tasks, 0, size());
        }
        tasks[maxIndex] = task;
        maxIndex ++;
    }

    public boolean remove(Task task){
        if (task == null)
            throw new NullPointerException("task is null");
        int i = 0;
        while ((tasks[i] != task) && (i != size())){
            i ++;
        }
        if (i == size()){
             return false;
        } else {
            maxIndex--;
            if (size() - i >= 0) System.arraycopy(tasks, i + 1, tasks, i, size() - i);
            tasks[size()] = null;
            return true;
        }
    }

    /*public ArrayTaskList incoming(int from, int to){
        if (from < 0)
            throw new IllegalArgumentException("from < 0");
        if (to < from)
            throw new IllegalArgumentException("to < from");
        ArrayTaskList result = new ArrayTaskList();
        for (int i = 0; i < maxIndex; i++) {
            if ((tasks[i].nextTimeAfter(from) <= to) && (tasks[i].nextTimeAfter(from) != -1)){
                result.add(tasks[i]);
            }
        }
        return result;
    }*/
}
