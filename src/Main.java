import Manager.Manager;
import Manager.Managers;
import Models.Epic;
import Models.Subtask;
import Models.Task;

import static Models.Status.NEW;

public class Main {
    public static void main(String[] args) {

        Manager manager = Managers.getDefault();

        // создайте две задачи,
        // эпик с тремя подзадачами
        System.out.println("Создаю эпик с 3-я подзадачами...");
        Epic epicFirst = new Epic(0,"Эпик", "Описание эпика",NEW);
        manager.addEpic(epicFirst);
        manager.updateEpicStatus(epicFirst);
        manager.getHistory();

        System.out.println("Создаю 1-ю подзадачу эпика...");
        Subtask subtaskFirst = new Subtask(1,"Первая подзадача", "Описание первой подзадачи",NEW, epic.getId());
        manager.addSubtask(subtaskFirst);
        manager.updateEpicStatus(epicFirst);
        manager.getHistory();

        System.out.println("Создаю 2-ю подзадачу эпика...");
        Subtask subtaskSecond = new Subtask(2,"Вторая подзадача", "Описание второй подзадачи", NEW, epic.getId());
        manager.addSubtask(subtaskSecond);
        manager.updateEpicStatus(epicFirst);
        manager.getHistory();

        System.out.println("Создаю 3-ю подзадачу эпика...");
        Subtask subtaskThird = new Subtask(3,"Третья подзадача", "Описание второй подзадачи", NEW, epic.getId());
        manager.addSubtask(subtaskSecond);
        manager.updateEpicStatus(epicFirst);
        manager.getHistory();

        // и эпик без подзадач
        System.out.println("Создаю эпик без подзадач...");
        Epic epicSecond = new Epic(4,"Эпик", "Описание эпика",NEW);
        manager.addEpic(epicSecond);
        manager.updateEpicStatus(epicSecond);
        manager.getHistory();

        // запросите созданные задачи несколько раз в разном порядке;
        manager.printAndGetEpics();

        // после каждого запроса выведите историю и убедитесь,
        // что в ней нет повторов;
        manager.getHistory();

        // удалите задачу, которая есть в истории,
        // и проверьте, что при печати она не будет выводиться;
        manager.deleteSubtaskById(2);
        manager.remove(2);
        manager.updateEpicStatus(epicFirst);
        manager.getHistory();

        // удалите эпик с тремя подзадачами и убедитесь,
        // что из истории удалился как сам эпик,
        // так и все его подзадачи.
        manager.deleteEpickById(0);
        manager.remove(0);
        manager.updateEpicStatus(epicFirst);
        manager.getHistory();
    }
}
