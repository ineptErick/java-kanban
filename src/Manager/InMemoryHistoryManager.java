package Manager;

import Models.Node;
import Models.Task;

import java.util.*;

public class InMemoryHistoryManager implements HistoryManager {

    // хранение порядка вызовов
    // ключ - id задачи, просмотр которой требуется удалить
    // значение — место просмотра этой задачи в списке - узел связного списка
    private final Map<Integer, Node> tasksSequence = new HashMap<>();

    // история просмотра задач
    private final CustomLinkedList<Task> historyList = new CustomLinkedList<>();

    // метод добавляет таску в список
    @Override
    public void add(Task task) {
        if(task!=null){
            // Если какая-либо задача просматривалась несколько раз,
            // в истории должен отобразиться только последний просмотр.
            // Предыдущий просмотр должен быть удалён сразу же после появления нового.
            remove(task.getId());
            historyList.linkLast(task);
            // как вот здесь взаимодействовать с нодами также не понимаю
            // прописать дополнительно здесь код по созданию\поиску этих двух нодов?
            // или прописать это в виде метода в классе Node?
            Node prev;
            Node next;
            Node node = new Node(task,prev,next);
            tasksSequence.put(task.getId(),node);
        }
    }

    // перекладывает задачи из связного списка в ArrayList для формирования ответа.
    // айдие не нравится ретурн этого метода
    // но я не понимаю почему и что нужно изменить
    @Override
    public CustomLinkedList<Task> getHistory() {
        return historyList.getTasks();
    }

    // удаление задач из просмотра
    @Override
    public void remove(int id) {
        if (tasksSequence.containsKey(id)) {
            historyList.removeNode(tasksSequence.get(id));
            tasksSequence.remove(id);
        }
    }

    // красиво вывести список тасок на экран
    @Override
    public String toString() {
        return "Последние добавленные таски: " + historyList;
    }

    public static class CustomLinkedList<T> {
        private Node first; // Указатель на первый элемент списка
        private Node last;  // Указатель на последний элемент списка

        // добавляет задачу в конец списка
        void linkLast(Task task){
            if (first == null) {
                Node currentNode = new Node(task, first, null);
                first = currentNode;
                last = new Node(task, currentNode, null);
                return;
            }
            Node currentNode = last;
            currentNode.setTask(task);
            last = new Node(task, null, null);
            // здесь что-то еще, пока не понимаю
        }

        // собирает задачи в обычный ArrayList
        public List<Task> getTasks(){
            List<Task> tasksList = new ArrayList<>();
            Node node = first;

            while (node != null) {
                tasksList.add(node.getTask());
                node = node.getNext();
            }
            return tasksList;
        }

        void removeNode(Node node){
            if (node != null) {
                final Node prev = node.getPrev(); // Ссылка на предыдущую ноду
                final Node next = node.getNext(); // Ссылка на следующую ноду

                if (prev == null) { // IF предыдущий узел == null, то головой списка становится NEXT узел
                    first = next;
                } else { // IF узел находится в центре списка, тогда:
                    prev.getNext() = next; // то поле NEXT у предыдущей ноды, начинает ссылаться на поле NEXT удаляемой
                    node.getPrev() = null; // поле PREV у удаляемой ноды обнуляем, т.к. мы изменили ссылки и сохранили связь
                }
                if (next == null) { // IF следующий узел == null, то хвостом списка становится PREV узел
                    last = prev;
                } else { // IF узел находится в центре списка, тогда:
                    next.getPrev() = prev; // то поле PREV у следующей ноды, начинает ссылаться на поле PREV удаляемой
                    node.getNext() = null; // поле NEXT у удаляемой ноды обнуляем, т.к. мы изменили ссылки и сохранили связь
                }
                node.getTask() = null; // Обнуляем значение узла

                // здесь get методы идея не принимает
                // пока не знаю как изменить
            }
        }
    }
}

