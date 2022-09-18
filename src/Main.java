import Manager.TaskManager;
import Manager.Managers;
import Models.Epic;
import Models.SubTask;
import Models.Task;

import static Enums.Status.NEW;

public class Main {
    public static void main(String[] args) {

        TaskManager taskManager = Managers.getDefaultTask();

        // создайте две задачи
        System.out.println("Создаю первую задачу...");
        Task taskFirst = new Task("Первая задача", "Описание первой задачи", NEW);
        taskManager.addTask(taskFirst);
        taskManager.updateTask(taskFirst);

        System.out.println("Создаю вторую задачу...");
        Task taskSecond = new Task("Вторая задача", "Описание второй задачи", NEW);
        taskManager.addTask(taskSecond);
        taskManager.updateTask(taskSecond);

        // эпик с тремя подзадачами
        System.out.println("Создаю эпик с 3-я подзадачами...");
        Epic epicFirst = new Epic("Эпик с тремя подзадачами", "Описание эпика с тремя подзадачами", NEW);
        taskManager.addEpic(epicFirst);
        taskManager.updateEpicStatus(epicFirst);

        System.out.println("Создаю 1-ю подзадачу эпика...");
        SubTask subTaskFirst = new SubTask("Первая подзадача первого эпика", "Описание первой подзадачи", NEW, epicFirst.getId());
        taskManager.addSubtask(subTaskFirst);
        taskManager.updateEpicStatus(epicFirst);

        System.out.println("Создаю 2-ю подзадачу эпика...");
        SubTask subTaskSecond = new SubTask("Вторая подзадача первого эпика", "Описание второй подзадачи", NEW, epicFirst.getId());
        taskManager.addSubtask(subTaskSecond);
        taskManager.updateEpicStatus(epicFirst);

        System.out.println("Создаю 3-ю подзадачу эпика...");
        SubTask subTaskThird = new SubTask("Третья подзадача первого эпика", "Описание второй подзадачи", NEW, epicFirst.getId());
        taskManager.addSubtask(subTaskSecond);
        taskManager.updateEpicStatus(epicFirst);

        // и эпик без подзадач
        System.out.println("Создаю эпик без подзадач...");
        Epic epicSecond = new Epic("Эпик", "Описание эпика без подзадач", NEW);
        taskManager.addEpic(epicSecond);
        taskManager.updateEpicStatus(epicSecond);

        System.out.println("Вывожу списков всех задач, эпиков и подзадач:");
        System.out.println(taskManager.getTasks());
        System.out.println(taskManager.getEpics());
        System.out.println(taskManager.getSubTask());

        System.out.println("Запрашиваю данные разных задач: ");
        // запросите созданные задачи несколько раз в разном порядке;
        System.out.println(taskManager.getTaskById(taskSecond.getId()));
        System.out.println("История просмотров: " + taskManager.getHistory());
        System.out.println(taskManager.getTaskById(epicSecond.getId()));
        System.out.println("История просмотров: " + taskManager.getHistory());
        System.out.println(taskManager.getTaskById(subTaskThird.getId()));
        System.out.println("История просмотров: " + taskManager.getHistory());
        System.out.println(taskManager.getTaskById(taskSecond.getId()));
        System.out.println("История просмотров: " + taskManager.getHistory());

        taskManager.getTaskById(taskFirst.getId());
        taskManager.getTaskById(subTaskThird.getId());
        taskManager.getTaskById(subTaskThird.getId());
        taskManager.getTaskById(epicSecond.getId());
        // после каждого запроса выведите историю и убедитесь,
        // что в ней нет повторов;

        System.out.println("История просмотров теперь: " + taskManager.getHistory());

        // удалите задачу, которая есть в истории,
        // и проверьте, что при печати она не будет выводиться;
        taskManager.deleteByIdTask(taskFirst.getId());
        System.out.println("Удалена задача: " + taskManager.getTaskById(taskFirst.getId()));
        System.out.println("История просмотров: " + taskManager.getHistory());

        // удалите эпик с тремя подзадачами и убедитесь,
        // что из истории удалился как сам эпик
        System.out.println("Удаляю эпик с тремя подзадачами...");
        taskManager.deleteByIdTask(epicFirst.getId());
        // так и все его подзадачи
        System.out.println("Удаляем Эпик 1 с 3-мя подзадачами: " + taskManager.getEpicById(epicFirst.getId()));
        System.out.println("Удалённая Подзадача 1 Эпика 1: " + taskManager.getSubTaskById(subTaskFirst.getId()));
        System.out.println("Удалённая Подзадача 2 Эпика 2: " + taskManager.getSubTaskById(subTaskSecond.getId()));
        System.out.println("Удалённая Подзадача 3 Эпика 3: " + taskManager.getSubTaskById(subTaskThird.getId()));

        System.out.println("История просмотров: " + taskManager.getHistory());

        System.out.println("Конец программы.");
    }
}
