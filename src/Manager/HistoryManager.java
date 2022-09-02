package Manager;

import Models.Task;

// интерфейс для управления историей просмотров
public interface HistoryManager {

    // должен помечать задачи как просмотренные
    void add(Task task);

    // удаление задач из просмотра
    void remove(int id);

    // получить этот список из 10 последних тасок
    InMemoryHistoryManager.CustomLinkedList getHistory();

}
