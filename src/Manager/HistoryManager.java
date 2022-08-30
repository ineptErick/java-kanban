package Manager;

import Models.Task;

import java.util.ArrayList;

// интерфейс для управления историей просмотров
public interface HistoryManager {

    // должен помечать задачи как просмотренные
    void add(Task task);

    // удаление задач из просмотра
    void remove(int id);

    // получить этот список из 10 последних тасок
    ArrayList<Task> getHistory();

}
