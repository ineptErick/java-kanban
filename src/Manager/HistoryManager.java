package Manager;

import Models.Task;

import java.util.ArrayList;

// интерфейс для управления историей просмотров
public interface HistoryManager {

    // должен помечать задачи как просмотренные
    void add(Task task);

    // должен помечать задачи как просмотренные
    ArrayList getHistory = new ArrayList<Task>();

    // получить этот список из 10 последних тасок
    ArrayList<Task> getHistory();
}
