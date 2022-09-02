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
        Task taskFirst = new Epic(0,"Первая задача", "Описание первой задачи",NEW);
        manager.addTask(taskFirst);
        manager.updateTask(taskFirst);
        manager.getHistory();

        System.out.println("Создаю вторую задачу...");
        Task taskSecond = new Epic(1,"Вторая задача", "Описание второй задачи",NEW);
        manager.addTask(taskSecond);
        manager.updateTask(taskSecond);
        manager.getHistory();

        // эпик с тремя подзадачами
        System.out.println("Создаю эпик с 3-я подзадачами...");
        Epic epicFirst = new Epic(2,"Эпик с тремя подзадачами", "Описание эпика с тремя подзадачами",NEW);
        manager.addEpic(epicFirst);
        manager.updateEpicStatus(epicFirst);
        manager.getHistory();

        System.out.println("Создаю 1-ю подзадачу эпика...");
        Subtask subtaskFirst = new Subtask(3,"Первая подзадача первого эпика", "Описание первой подзадачи",NEW, epicFirst.getId());
        manager.addSubtask(subtaskFirst);
        manager.updateEpicStatus(epicFirst);
        manager.getHistory();

        System.out.println("Создаю 2-ю подзадачу эпика...");
        Subtask subtaskSecond = new Subtask(4,"Вторая подзадача первого эпика", "Описание второй подзадачи", NEW, epicFirst.getId());
        manager.addSubtask(subtaskSecond);
        manager.updateEpicStatus(epicFirst);
        manager.getHistory();

        System.out.println("Создаю 3-ю подзадачу эпика...");
        Subtask subtaskThird = new Subtask(5,"Третья подзадача первого эпика", "Описание второй подзадачи", NEW, epicFirst.getId());
        manager.addSubtask(subtaskSecond);
        manager.updateEpicStatus(epicFirst);
        manager.getHistory();

        // и эпик без подзадач
        System.out.println("Создаю эпик без подзадач...");
        Epic epicSecond = new Epic(6,"Эпик", "Описание эпика без подзадач",NEW);
        manager.addEpic(epicSecond);
        manager.updateEpicStatus(epicSecond);
        manager.getHistory();

        // запросите созданные задачи несколько раз в разном порядке;
        manager.getTaskById(6);
        manager.getTaskById(0);
        manager.getTaskById(2);
        manager.getTaskById(6);
        manager.getTaskById(2);

        // после каждого запроса выведите историю и убедитесь,
        // что в ней нет повторов;
        manager.getHistory();
        // должно быть 0 6 2

        // удалите задачу, которая есть в истории,
        // и проверьте, что при печати она не будет выводиться;
        manager.deleteSubtaskById(subtaskSecond.getId());
        manager.remove(subtaskSecond.getId());
        manager.updateEpicStatus(epicFirst);
        manager.getHistory();

        // удалите эпик с тремя подзадачами и убедитесь,
        // что из истории удалился как сам эпик,
        // так и все его подзадачи.
        manager.deleteEpicById(epicFirst.getId());
        manager.remove(epicFirst.getId());
        manager.updateEpicStatus(epicFirst);
        manager.getHistory();
    }
}
