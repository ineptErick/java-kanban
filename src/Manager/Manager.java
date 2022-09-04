package Manager;

import Models.Epic;
import Models.Subtask;
import Models.Task;

import java.util.ArrayList;
import java.util.List;

public interface Manager {

    // Создание задачи
    int addTask(Task task);


    // Создание эпика
    int addEpic(Epic epic);


    // Создание подзадачи
    int addSubtask(Subtask subtask);


    //Получение списка всех задач.
    List<Task> printAndGetTasks();


    //Получение списка всех эпиков.
    List<Epic> printAndGetEpics();


    //Получение списка всех подзадач.
    List<Subtask> printAndGetSubtasks();


    //Удаление всех задач.
    void deleteAllTasks();

    //Удаление всех эпиков.
    void deleteAllEpics();

    //Удаление всех подзадач.
    void deleteAllSubtasks();

    //Получение задачи по идентификатору.
    Task getTaskById(int id);


    //Получение эпика по идентификатору.
    Epic getEpicById(int id);


    //Получение подзадачи по идентификатору.
    Subtask getSubtaskById(int id);


    // Обновление. Новая версия объекта с верным идентификатором передаётся в виде параметра.
    // Обновление задачи.
    void updateTask(Task task);

    // Обновление эпика.
    void updateEpic(Epic epic);

    // Обновление подзадачи.
    void updateSubtask(Subtask subtask);

    // Удаление задачи по идентификатору.
    void deleteTaskById(int id);


    // Удаление эпика по идентификатору.
    void deleteEpicById(int id);


    // Удаление подзадачи по идентификатору.
    void deleteSubtaskById(int id);


    // Дополнительные методы:
    // Получение списка всех подзадач определённого эпика.
    ArrayList<Subtask> getSubtaskListByEpic(int id);


    // Обновление статуса эпика.
    void updateEpicStatus(Epic epic);


    // облегченный вариант обновления статуса метода
    void updateEpicStatusSecondVar(Epic epic);


    // История просмотров задач
    List<Task> getHistory();

}