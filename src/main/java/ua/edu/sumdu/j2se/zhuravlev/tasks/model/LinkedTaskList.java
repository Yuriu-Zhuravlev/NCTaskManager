package ua.edu.sumdu.j2se.zhuravlev.tasks.model;

import java.io.Serializable;
import java.util.Iterator;

public class LinkedTaskList extends AbstractTaskList{
    @Override
    public Iterator<Task> iterator() {
        return new Iterator<Task>() {
            Node next = head;
            Node cur;
            @Override
            public boolean hasNext() {
                return next != null;
            }

            @Override
            public Task next() {
                cur = next;
                next = next.getNext();
                return cur.getTask();
            }

            @Override
            public void remove() {
                if(cur == null) {
                    throw new IllegalStateException();
                }
                else {
                    LinkedTaskList.this.remove(cur.getTask());
                    cur = null;
                }
            }
        };
    }

    private class Node implements Serializable{
        private Task task;
        private Node next;

        public Node(Task task) {
            this.task = task;
            next = null;
        }

        public Node getNext() {
            return next;
        }

        public void setNext(Node next) {
            this.next = next;
        }

        public Task getTask() {
            return task;
        }

        public void setTask(Task task) {
            this.task = task;
        }
    }

    private Node head;
    private Node tail;

    public LinkedTaskList() {
        type = ListTypes.LINKED;
        maxIndex = 0;
        head = null;
        tail = null;
    }

    public void add(Task task){
        if (task == null)
            throw new NullPointerException("task is null");
        Node node = new Node(task);
        if (head == null){
            head = node;
            tail = node;
        } else {
            tail.setNext(node);
            tail = node;
        }
        maxIndex++;
    }

    public boolean remove(Task task){
        if (task == null)
            throw new NullPointerException("task is null");
        if (head.getTask().equals(task)){
            head = head.getNext();
            maxIndex--;
            return true;
        }
        Node tempNode = head;
        while ((!tempNode.getNext().getTask().equals(task)) && (tempNode.getNext() != null))
            tempNode = tempNode.getNext();
        if (tempNode.getNext() != null){
            if(tempNode.getNext() == tail){
                tail = tempNode;
            }
            tempNode.setNext(tempNode.getNext().getNext());
            maxIndex--;
            return true;
        } else {
            return false;
        }
    }

    public Task getTask(int index){
        if (index >= maxIndex)
            throw new IndexOutOfBoundsException("index > size");
        int i = 0;
        Node temp = head;
        while (i < index){
            temp = temp.getNext();
            i++;
        }
        return temp.getTask();
    }
}
