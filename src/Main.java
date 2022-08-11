import Manager.InMemoryTaskManager;
import Models.Epic;
import Models.Subtask;
import Models.Task;

public class Main {
    public static void main(String[] args) {

        InMemoryTaskManager manager = new InMemoryTaskManager();

        System.out.println("Создаю простую задачу...");
        Task task = new Task(0,"Задача","Описание задачи","NEW");
        manager.addTask(task);
        manager.getHistory();

        System.out.println("Создаю эпик с 2-я подзадачами...");
        Epic epic = new Epic(0,"Эпик", "Описание эпика","NEW");
        manager.addEpic(epic);
        manager.updateEpicStatus(epic);
        manager.getHistory();

        System.out.println("Создаю 1-ю подзадачу эпика...");
        Subtask subtaskFirst = new Subtask(0,"Первая подзадача", "Описание первой подзадачи","NEW", epic.getId());
        manager.addSubtask(subtaskFirst);
        manager.updateEpicStatus(epic);
        manager.getHistory();

        System.out.println("Создаю 2-ю подзадачу эпика...");
        Subtask subtaskSecond = new Subtask(0,"Вторая подзадача", "Описание второй подзадачи", "NEW", epic.getId());
        manager.addSubtask(subtaskSecond);
        manager.updateEpicStatus(epic);
        manager.getHistory();

        System.out.println("Вывожу список всех задач, эпиков и его подзадач...");
        manager.printAndGetTasks();
        manager.printAndGetEpics();
        manager.updateEpicStatus(epic);

        System.out.println("Обновляю задачу...");
        Task taskUpdated = new Task(1,"Обновленная задача","Описание обновленной задачи","NEW");
        manager.updateTask(taskUpdated);

        System.out.println("Обновляю первую подзадачу...");
        Subtask subtaskFirstUpdated = new Subtask(2,"Обновленная первая подзадача","Обновленное описание первой задачи","NEW", epic.getId());
        manager.updateSubtask(epic, subtaskFirstUpdated);
        manager.updateEpicStatus(epic);

        System.out.println("Вывожу обновленный список всех задач, эпиков и подзадач...");
        manager.printAndGetTasks();
        manager.printAndGetEpics();
        manager.updateEpicStatus(epic);

        System.out.println("Удаляю вторую подзадачу...");
        manager.deleteSubtaskById(2);
        manager.updateEpicStatus(epic);
        manager.getHistory();

        System.out.println("Вывожу обновленный список всех задач, эпиков и подзадач...");
        manager.printAndGetTasks();
        manager.printAndGetEpics();
        manager.updateEpicStatus(epic);

        System.out.println("Удаляю все задачи...");
        manager.deleteAllEpics();
        manager.updateEpicStatus(epic);
        manager.getHistory();

        System.out.println("Вывожу обновленный список всех задач, эпиков и подзадач...");
        System.out.println("Тут должен быть только эпик с одной задачей, если не так, то все плохо.");
        manager.printAndGetTasks();
        manager.printAndGetEpics();
        manager.updateEpicStatus(epic);
    }
}
