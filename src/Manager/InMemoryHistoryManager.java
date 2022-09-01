package Manager;

import Models.Node;
import Models.Task;

import java.util.*;

public class InMemoryHistoryManager implements HistoryManager {
    // здесь храним поступающие таски
    private final ArrayList<Task> watchedTasksList = new ArrayList<>();
    private final int ONE_STEP_METER = 10;


    // метод добавляет таску в список
    // если тасок уже больше 10, то удаляем первую
    @Override
    public void add(Task task) {
        watchedTasksList.add(task);
        if (watchedTasksList.size()>ONE_STEP_METER) {
            watchedTasksList.remove(0);
        }
        // Если какая-либо задача просматривалась несколько раз,
        // в истории должен отобразиться только последний просмотр.

        if (watchedTasksList.contains(task)){
            watchedTasksList.remove(task.getId());
            // Предыдущий просмотр должен быть удалён сразу же после появления нового
            // как вот это релизовать пока не понимаю
        }
    }

    // получить этот список из 10 последних тасок
    // Реализация метода getHistory должна перекладывать
    // задачи из связного списка в ArrayList для формирования ответа.
    @Override
    public ArrayList<Task> getHistory() {
        for(Task task : tasksOrder.values()){
            watchedTasksList.add(task.getId(),task);
        }
        return watchedTasksList;
    }

    // удаление задач из просмотра
    @Override
    public void remove(int id) {
        for(Integer number : tasksOrder.keySet()){
            if(id==number){
                tasksOrder.remove(id);
                watchedTasksList.remove(id);
            }
        }
    }

    // красиво вывести этот список на экран
    @Override
    public String toString() {
        return "Последние 10 тасок: " + watchedTasksList;
    }




    // двусвязный список задач
    private List<Node> customLinkedList = new LinkedList<>();

    public List<Node> getCustomLinkedList() {
        return customLinkedList;
    }

    // и его методы:
    // добавляет задачу в конец списка
    void linkLast(int id){
        for(Integer number : tasksOrder.keySet()){
            if(number==id){
                customLinkedList.add(customLinkedList.size()-1,tasksOrder.get(id));
            }
        }
    }

    // собирает задачи из него в обычный ArrayList
    ArrayList<Task> getTasks(){
        ArrayList<Task> customArrayList = new ArrayList<>(customLinkedList);
        for(Node node : customLinkedList){
            customArrayList.add(node);
        }
        return customArrayList;
    }

    void removeNode(Node node){
        for(Node n : customLinkedList){
            if(n==node){
                customLinkedList.remove(n);
            }
        }
    }

    // хранение порядка вызовов
    // ключ - id задачи, просмотр которой требуется удалить
    // значение — место просмотра этой задачи в списке - узел связного списка
    private final Map<Integer, Node> tasksOrder = new HashMap<>();

}

