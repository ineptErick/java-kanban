import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class TaskManager {
    // Возможность хранить задачи всех типов.
    HashMap <Integer, ArrayList<Task>> listTask;
    HashMap <Integer, ArrayList<Epic>> listEpic;
    HashMap <Integer, ArrayList<Subtask>> listSubtask;

    //Методы для каждого из типа задач(Задача/Эпик/Подзадача)
    //Получение списка всех задач.
    void printTask(HashMap <Integer, ArrayList<Task>> listTask){
        if(listTask.isEmpty()){
            System.out.println("Нет доступных задач.");
        }else{
            for(Integer id : listTask.keySet()){
                for(ArrayList<Task> task : listTask.values()){
                    System.out.println("Номер задачи: "+id);
                    System.out.println(task.toString());
                }
            }
        }
    }
    void printEpic(HashMap <Integer, ArrayList<Task>> listEpic){
        if(listEpic.isEmpty()){
            System.out.println("Нет доступных задач.");
        }else{
            for(Integer id : listEpic.keySet()){
                for(ArrayList<Task> task : listEpic.values()){
                    System.out.println("Номер задачи: "+id);
                    System.out.println(task.toString());
                }
            }
        }
    }
    void printSubtask(HashMap <Integer, ArrayList<Task>> listSubtask){
        if(listSubtask.isEmpty()){
            System.out.println("Нет доступных задач.");
        }else{
            for(Integer id : listSubtask.keySet()){
                for(ArrayList<Task> task : listSubtask.values()){
                    System.out.println("Номер задачи: "+id);
                    System.out.println(task.toString());
                }
            }
        }
    }

    //Удаление всех задач.
    void deleteTasks(HashMap <Integer, ArrayList<Task>> listTask){
        Scanner scanner = new Scanner(System.in);

        if(listTask.isEmpty()){
            System.out.println("Нет доступных задач для удаления.");
        }else{
            System.out.println("Задачу под каким номером нужно удалить?");
            int number = scanner.nextInt();
            if(listTask.containsKey(number)){
                listTask.remove(number);
                System.out.println("Задача удалена.");
            }else{
                System.out.println("Задачи под таким номером нет.");
                deleteTasks(listTask);
            }
        }
    }
    void deleteEpic(HashMap <Integer, ArrayList<Task>> listEpic){
        Scanner scanner = new Scanner(System.in);

        if(listEpic.isEmpty()){
            System.out.println("Нет доступных задач для удаления.");
        }else{
            System.out.println("Задачу под каким номером нужно удалить?");
            int number = scanner.nextInt();
            if(listEpic.containsKey(number)){
                listEpic.remove(number);
                System.out.println("Задача удалена.");
            }else{
                System.out.println("Задачи под таким номером нет.");
                deleteTasks(listEpic);
            }
        }
    }
    void deleteSubtask(HashMap <Integer, ArrayList<Task>> listSubtask){
        Scanner scanner = new Scanner(System.in);

        if(listSubtask.isEmpty()){
            System.out.println("Нет доступных задач для удаления.");
        }else{
            System.out.println("Задачу под каким номером нужно удалить?");
            int number = scanner.nextInt();
            if(listSubtask.containsKey(number)){
                listSubtask.remove(number);
                System.out.println("Задача удалена.");
            }else{
                System.out.println("Задачи под таким номером нет.");
                deleteTasks(listSubtask);
            }
        }
    }

    //Получение по идентификатору.
    void getTaskById(HashMap <Integer, ArrayList<Task>> listTask){

    }

    //Создание. Сам объект должен передаваться в качестве параметра.
    //Обновление. Новая версия объекта с верным идентификатором передаётся в виде параметра.
    //Удаление по идентификатору.
    Task addTask(String name, String description, String status) {
        id++;
        Task task = new Task(name, id, description, status);
        tableTasks.put(id, task);
        return task;
    }

    // Дополнительные методы:
    //Получение списка всех подзадач определённого эпика.

    //Управление статусами осуществляется по следующему правилу:
    //Менеджер сам не выбирает статус для задачи. Информация о нём приходит менеджеру вместе с информацией о самой задаче. По этим данным в одних случаях он будет сохранять статус, в других будет рассчитывать.

    //Для эпиков:
    //если у эпика нет подзадач или все они имеют статус NEW, то статус должен быть NEW.
    //если все подзадачи имеют статус DONE, то и эпик считается завершённым — со статусом DONE.
    //во всех остальных случаях статус должен быть IN_PROGRESS.
}
