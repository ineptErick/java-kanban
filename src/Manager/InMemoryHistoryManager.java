package Manager;

import Models.Task;

import java.util.ArrayList;

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
    }

    // получить этот список из 10 последних тасок
    @Override
    public ArrayList<Task> getHistory() {
        return watchedTasksList;
    }

    // красиво вывести этот список на экран
    @Override
    public String toString() {
        return "Последние 10 тасок: " + watchedTasksList;
    }
}
