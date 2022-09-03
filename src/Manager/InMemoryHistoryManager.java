package Manager;

import Models.Node;
import Models.Task;

import java.util.*;

public class InMemoryHistoryManager implements HistoryManager {

    // хранение порядка вызовов
    // ключ - id задачи, просмотр которой требуется удалить
    // значение — место просмотра этой задачи в списке - узел связного списка
    private final Map<Integer, Node> history = new HashMap<>();

    // история просмотра задач
    private final CustomLinkedList<Task> list = new CustomLinkedList<>();

    // метод добавляет таску в список
    @Override
    public void add(Task task) {
        if(task!=null){
            for(Integer id : history.keySet()){
                if(id==task.getId()){
                    history.remove(id);
                    list.removeNode(history.get(id));
                }
            }
        }
    }

    // перекладывает задачи из map в list для формирования ответа
    @Override
    public List<Task> getHistory() {
        for(Node node : history.values()){
            list.linkLast(node.getTask());
        }
        return list.getTasks();
    }

    // удаление задачи из просмотра
    @Override
    public void remove(int id) {
        if (history.containsKey(id)) {
            history.remove(id);
            list.removeNode(history.get(id));
        }
    }

    // красиво вывести список тасок на экран
    @Override
    public String toString() {
        return "Последние добавленные таски: " + list.getTasks();
    }

    public static class CustomLinkedList<T> {
        private Node first; // Указатель на первый элемент списка
        private Node last;  // Указатель на последний элемент списка

        // добавляет задачу в конец списка
        void linkLast(Task task) {
            // если первого элемента нет
            if (first == null) {
                Node currentNode = new Node(first, task, null);
                first = currentNode;
                last = new Node(currentNode, null, null);
                return;
            }
            // если первый элемент есть
            Node currentNode = last;
            currentNode.setTask(task);
            last = new Node(currentNode, null, null);

            Node prev = currentNode.getPrev();
            prev.setNext(currentNode);
            currentNode.setPrev(prev);

            currentNode.setNext(last);
        }

        // собирает задачи в обычный ArrayList
        public List<Task> getTasks() {
            List<Task> tasksList = new ArrayList<>();
            Node node = first;

            while (node != null) {
                tasksList.add(node.getTask());
                node = node.getNext();
            }
            return tasksList;
        }

        public void removeNode(Node node){
            if (node != null) {
                final Node prev = node.getPrev(); // Ссылка на предыдущую ноду
                final Node next = node.getNext(); // Ссылка на следующую ноду

                if (prev == null) { // IF предыдущий узел == null, то головой списка становится NEXT узел
                    first = next;
                } else { // IF узел находится в центре списка, тогда:
                    prev.setNext(next); // то поле NEXT у предыдущей ноды, начинает ссылаться на поле NEXT удаляемой
                    node.setPrev(null); // поле PREV у удаляемой ноды обнуляем, т.к. мы изменили ссылки и сохранили связь
                }
                if (next == null) { // IF следующий узел == null, то хвостом списка становится PREV узел
                    last = prev;
                } else { // IF узел находится в центре списка, тогда:
                    next.setPrev(prev); // то поле PREV у следующей ноды, начинает ссылаться на поле PREV удаляемой
                    node.setNext(null); // поле NEXT у удаляемой ноды обнуляем, т.к. мы изменили ссылки и сохранили связь
                }
                node.setTask(null); // Обнуляем значение узла
            }
        }
    }
}

