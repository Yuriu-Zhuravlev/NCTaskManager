package ua.edu.sumdu.j2se.zhuravlev.tasks;

public class ArrayTaskList {
    private int maxIndex;
    private int maxSize;
    private Task[] tasks;
    final int toAdd = 10;

    public ArrayTaskList(){
        maxSize = toAdd;
        tasks = new Task[maxSize];
        maxIndex=0;
    }

    public int size(){
        return maxIndex;
    }

    public Task getTask(int index){
        return tasks[index];
    }

    public void add(Task task){
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

    public ArrayTaskList incoming(int from, int to){
        ArrayTaskList result = new ArrayTaskList();
        for (int i = 0; i < maxIndex; i++) {
            if ((tasks[i].nextTimeAfter(from) <= to) && (tasks[i].nextTimeAfter(from) != -1)){
                result.add(tasks[i]);
            }
        }
        return result;
    }
}
