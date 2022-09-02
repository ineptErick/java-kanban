package Models;

public class Node {
    // подсмотрела как некоторые сокурсники сделали из Node лист, который хранит <T>
    // но я пока решила сделать так
    private Task task;
    private Node prev;
    private Node next;

    public Node(Task task, Node prev, Node next) {
        this.task = task;
        this.prev = prev;
        this.next = next;
    }

    public Task getTask() {
        return task;
    }

    public Node getPrev() {
        return prev;
    }

    public Node getNext() {
        return next;
    }

    public void setTask(Task task) {
        this.task = task;
    }

    public void setPrev(Node prev) {
        this.prev = prev;
    }

    public void setNext(Node next) {
        this.next = next;
    }
}
