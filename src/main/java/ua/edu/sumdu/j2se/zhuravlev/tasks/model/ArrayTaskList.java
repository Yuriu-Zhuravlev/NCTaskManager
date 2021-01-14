package ua.edu.sumdu.j2se.zhuravlev.tasks.model;

import java.util.Iterator;

public class ArrayTaskList extends AbstractTaskList{
    private int maxSize;
    private Task[] tasks;
    private final int toAdd = 10;

    public ArrayTaskList() {
        maxSize = toAdd;
        tasks = new Task[maxSize];
        maxIndex = 0;
        type = ListTypes.types.ARRAY;
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
        while ((!tasks[i].equals(task)) && (i != size())){
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


    @Override
    public Iterator<Task> iterator() {
        return new Iterator<Task>() {
            private int index = 0;
            private int curIndex = -1;
            @Override
            public boolean hasNext() {
                return index < size();
            }

            @Override
            public Task next() {
                curIndex = index++;
                return tasks[curIndex];
            }

            @Override
            public void remove() {
                if(curIndex == -1) {
                    throw new IllegalStateException();
                }
                else {
                    ArrayTaskList.this.remove(tasks[curIndex]);
                    curIndex = -1;
                    index --;
                }
            }
        };
    }

}
