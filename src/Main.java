import Manager.Manager;
import Manager.Managers;
import Models.Epic;
import Models.Subtask;
import Models.Task;

import static Models.Status.NEW;

public class Main {
    public static void main(String[] args) {

        Manager manager = Managers.getDefault();

        // создайте две задачи
        System.out.println("Создаю первую задачу...");
        Task taskFirst = new Task("Первая задача", "Описание первой задачи",NEW);
        manager.addTask(taskFirst);
        manager.updateTask(taskFirst);

        System.out.println("Создаю вторую задачу...");
        Task taskSecond = new Task("Вторая задача", "Описание второй задачи",NEW);
        manager.addTask(taskSecond);
        manager.updateTask(taskSecond);

        // эпик с тремя подзадачами
        System.out.println("Создаю эпик с 3-я подзадачами...");
        Epic epicFirst = new Epic("Эпик с тремя подзадачами", "Описание эпика с тремя подзадачами",NEW);
        manager.addEpic(epicFirst);
        manager.updateEpicStatus(epicFirst);

        System.out.println("Создаю 1-ю подзадачу эпика...");
        Subtask subtaskFirst = new Subtask("Первая подзадача первого эпика", "Описание первой подзадачи",NEW, epicFirst.getId());
        manager.addSubtask(subtaskFirst);
        manager.updateEpicStatus(epicFirst);

        System.out.println("Создаю 2-ю подзадачу эпика...");
        Subtask subtaskSecond = new Subtask("Вторая подзадача первого эпика", "Описание второй подзадачи", NEW, epicFirst.getId());
        manager.addSubtask(subtaskSecond);
        manager.updateEpicStatus(epicFirst);

        System.out.println("Создаю 3-ю подзадачу эпика...");
        Subtask subtaskThird = new Subtask("Третья подзадача первого эпика", "Описание второй подзадачи", NEW, epicFirst.getId());
        manager.addSubtask(subtaskSecond);
        manager.updateEpicStatus(epicFirst);

        // и эпик без подзадач
        System.out.println("Создаю эпик без подзадач...");
        Epic epicSecond = new Epic("Эпик", "Описание эпика без подзадач",NEW);
        manager.addEpic(epicSecond);
        manager.updateEpicStatus(epicSecond);

        System.out.println("Вывожу списков всех задач, эпиков и подзадач:");
        System.out.println(manager.printAndGetTasks());
        System.out.println(manager.printAndGetEpics());
        System.out.println(manager.printAndGetSubtasks());

        System.out.println("Запрашиваю данные разных задач: ");
        // запросите созданные задачи несколько раз в разном порядке;
        System.out.println("Запрашиваю вторую задачу...");
        manager.getTaskById(taskSecond.getId());
        System.out.println("История просмотров: " + manager.getHistory());
        System.out.println("Запрашиваю третью подзадачу первого эпика...");
        manager.getSubtaskById(subtaskThird.getId());
        System.out.println("История просмотров: " + manager.getHistory());
        System.out.println("Запрашиваю первую задачу...");
        manager.getTaskById(taskFirst.getId());
        System.out.println("История просмотров: " + manager.getHistory());
        System.out.println("Запрашиваю первую подзадачу первого эпика...");
        manager.getSubtaskById(subtaskFirst.getId());
        System.out.println("История просмотров: " + manager.getHistory());
        System.out.println("Запрашиваю вторую задачу...");
        manager.getTaskById(taskSecond.getId());
        System.out.println("История просмотров: " + manager.getHistory());

        // после каждого запроса выведите историю и убедитесь,
        // что в ней нет повторов;

        // удалите задачу, которая есть в истории,
        // и проверьте, что при печати она не будет выводиться;
        System.out.println("Удаляю первую подзадачу первого эпика...");
        manager.deleteTaskById(taskFirst.getId());
        System.out.println("Первая подзадача первого эпика должна быть удалена.");
        System.out.println("История просмотров: " + manager.getHistory());

        // удалите эпик с тремя подзадачами и убедитесь,
        // что из истории удалился как сам эпик
        // так и все его подзадачи.
        System.out.println("Удаляю эпик с тремя подзадачами...");
        manager.deleteEpicById(epicFirst.getId());
        manager.getEpicById(epicFirst.getId());
        System.out.println("История просмотров: " + manager.getHistory());
        System.out.println("Эпик с тремя подзадачами должен быть удален.");

        System.out.println("Конец программы.");
    }
}
