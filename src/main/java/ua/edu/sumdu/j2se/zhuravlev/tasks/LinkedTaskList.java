package ua.edu.sumdu.j2se.zhuravlev.tasks;

public class LinkedTaskList extends AbstractTaskList {
    private class Node{
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
    }

    private Node head;
    private Node tail;

    public LinkedTaskList() {
        type = ListTypes.types.LINKED;
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
        if (head.getTask() == task){
            head = head.getNext();
            maxIndex--;
            return true;
        }
        Node tempNode = head;
        while ((tempNode.getNext().getTask() != task) && (tempNode.getNext() != null))
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

    /*public LinkedTaskList incoming(int from, int to){
        if (from < 0)
            throw new IllegalArgumentException("from < 0");
        if (to < from)
            throw new IllegalArgumentException("to < from");
        LinkedTaskList result = new LinkedTaskList();
        Node temp = head;
        while (temp.getNext() != null){
            Task tempTask = temp.getTask();
            if ((tempTask.nextTimeAfter(from) <= to) && (tempTask.nextTimeAfter(from) != -1)){
                result.add(tempTask);
            }
            temp = temp.getNext();
        }
        return result;
    }*/
}
