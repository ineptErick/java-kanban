package Manager;

import Models.Task;

import java.util.ArrayList;
import java.util.LinkedList;

public class InMemoryHistoryManager implements HistoryManager {
    // здесь храним поступающие таски
    private final LinkedList<Task> watchedTasksList = new LinkedList<>();

    // метод добавляет таску в список
    // если тасок уже больше 10, то удаляем первую
    @Override
    public void add(Task task) {
        watchedTasksList.add(task);
        if(watchedTasksList.size()>10){
            watchedTasksList.removeFirst();
        }
    }

    // получить этот список из 10 последних тасок
    @Override
    public ArrayList<Task> getHistory() {
        return null;
    }

    // красиво вывести этот список на экран
    @Override
    public String toString() {
        return "Последние 10 тасок: " + watchedTasksList;
    }
}
