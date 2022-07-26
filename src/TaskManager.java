import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class TaskManager {
    // Возможность хранить задачи всех типов.
    public HashMap <Integer, Task> listTask;
    public HashMap <Integer, ArrayList<Subtask>> listEpic;

    public int id = 0;

    //Методы для каждого из типа задач(Задача/Эпик/Подзадача)
    //Получение списка всех задач.
    void printTask(){
        if(listTask.isEmpty()){
            System.out.println("Нет доступных задач.");
        }else{
            for(Integer id : listTask.keySet()){
                for(Task task : listTask.values()){
                    System.out.println("Номер задачи: "+id);
                    System.out.println(task.toString());
                }
            }
        }
    }
    void printEpic(){
        if(listEpic.isEmpty()){
            System.out.println("Нет доступных эпиков.");
        }else{
            for(Integer id : listEpic.keySet()){
                for(ArrayList<Subtask> subtask : listEpic.values()){
                    System.out.println("Номер эпика: "+listEpic.get(id));
                    System.out.println(subtask.toString());
                }
            }
        }
    }
    void printSubtask(){
        if(listEpic.isEmpty()){
            System.out.println("Нет доступных подзадач.");
        }else{
            for(Integer id : listEpic.keySet()){
                for(ArrayList<Subtask> subtask : listEpic.values()){
                    System.out.println("Номер подзадачи: "+subtask.get(id));
                    System.out.println(subtask.toString());
                }
            }
        }
    }

    //Удаление всех задач.
    void deleteAllTasks(){
        Scanner scanner = new Scanner(System.in);
        if(listTask.isEmpty()){
            System.out.println("Нет доступных задач для удаления.");
        }else{
            listTask.clear();
        }
    }
    void deleteAllEpic(){
        Scanner scanner = new Scanner(System.in);
        if(listEpic.isEmpty()){
            System.out.println("Нет доступных эпиков для удаления.");
        }else{
            listEpic.clear();
        }
    }
    void deleteAllSubtask(){
        Scanner scanner = new Scanner(System.in);
        if(listSubtask.isEmpty()){
            System.out.println("Нет доступных подзадач для удаления.");
        }else{
            listSubtask.clear();
        }
    }

    //Получение по идентификатору.
    void getTaskById(){
        Scanner scanner = new Scanner(System.in);

        if(listTask.isEmpty()){
            System.out.println("Нет доступных задач.");
        }else {
            System.out.println("Задачу под каким номером хотите получить?");
            int number = scanner.nextInt();

            if (listTask.containsKey(number)) {
                for (int id : listTask.keySet()) {
                    if(id==number){
                        for (Task task : listTask.values()) {
                            System.out.println("Номер задачи: " + number);
                            System.out.println(task.toString());
                        }
                    }
                }
            }else{
            System.out.println("Задачи под таким номером нет.");
        }

        }
    }
    void getSubtaskById(){
        Scanner scanner = new Scanner(System.in);

        if(listSubtask.isEmpty()){
            System.out.println("Нет доступных подзадач.");
        }else {
            System.out.println("Подзадачу под каким номером хотите получить?");
            int number = scanner.nextInt();

            if (listSubtask.containsKey(number)) {
                for (int id : listSubtask.keySet()) {
                    if(id==number){
                        for (Task task : listSubtask.values()) {
                            System.out.println("Номер подзадачи: " + number);
                            System.out.println(task.toString());
                        }
                    }
                }
            }else{
                System.out.println("Подзадачи под таким номером нет.");
            }

        }
    }
    void getEpicById(){
        Scanner scanner = new Scanner(System.in);

        if(listEpic.isEmpty()){
            System.out.println("Нет доступного эпика.");
        }else {
            System.out.println("Эпик под каким номером хотите получить?");
            int number = scanner.nextInt();

            if (listEpic.containsKey(number)) {
                for (int id : listEpic.keySet()) {
                    if(id==number){
                        for (Task task : listEpic.values()) {
                            System.out.println("Номер эпика: " + number);
                            System.out.println(task.toString());
                        }
                    }
                }
            }else{
                System.out.println("Эпика под таким номером нет.");
            }

        }
    }

    //Создание. Сам объект должен передаваться в качестве параметра.
    Task addTask(String name, String description, String status) {
        Task task = new Task(name,id, description, status);
        id++;
        listTask.put(id, task);
        return task;
    }
    Subtask addSubtask(String name, String description, String status) {
        Subtask subtask = new Subtask(name,id, description, status);
        id++;
        listSubtask.put(id, subtask);
        return subtask;
    }
    Epic addEpic(String name, String description, String status) {
        Epic epic = new Epic(name,id, description, status);
        id++;
        listEpic.put(id, epic);
        return epic;
    }

    //Обновление. Новая версия объекта с верным идентификатором передаётся в виде параметра.
    public void updateTask(String name, String description, String status){
        Task task = new Task(name, id, description, status);
        for (Integer key : listTask.keySet()){
            for(Task tsk : listTask.values()){
                if(tsk.id==id){
                    tsk.status=status;
                    tsk.description=description;
                    tsk.name=name;
                }
            }
        }
        System.out.println("Задача обновлена!");
    }
    public void updateSubtask(String name, String description, String status){
        Subtask subtask = new Subtask(name, id, description, status);
        for (Integer key : listSubtask.keySet()){
            for(Subtask tsk : listSubtask.values()){
                if(tsk.id==id){
                    tsk.status=status;
                    tsk.description=description;
                    tsk.name=name;
                }
            }
        }
        System.out.println("Подзадача обновлена!");
    }
    public void updateEpic(String name, String description, String status){
        Epic epic = new Epic(name, id, description, status);
        for (Integer key : listEpic.keySet()){
            for(Epic tsk : listEpic.values()){
                if(tsk.id==id){
                    tsk.description=description;
                    tsk.name=name;
                }
            }
        }
        System.out.println("Эпик обновлен!");
    }

    //Удаление по идентификатору.
public void deleteTaskById(){
        Scanner scanner = new Scanner(System.in);

        if(listTask.isEmpty()){
            System.out.println("Такого пункта нет.");
        }else{
            System.out.println("Задачу под каким номером нужно удалить?");
            int number = scanner.nextInt();
            if(listTask.containsKey(number)){
                listTask.remove(number);
                System.out.println("Задача удалена.");
            }else{
                System.out.println("Задачи под таким номером нет.");
                deleteTaskById();
            }
        }
}
    public void deleteSubtaskById(){
        Scanner scanner = new Scanner(System.in);

        if(listSubtask.isEmpty()){
            System.out.println("Такого пункта нет.");
        }else{
            System.out.println("Подзадачу под каким номером нужно удалить?");
            int number = scanner.nextInt();
            if(listSubtask.containsKey(number)){
                listSubtask.remove(number);
                System.out.println("Подзадача удалена.");
            }else{
                System.out.println("Подзадачи под таким номером нет.");
                deleteTaskById();
            }
        }
    }
    public void deleteEpicById(){
        Scanner scanner = new Scanner(System.in);

        if(listEpic.isEmpty()){
            System.out.println("Такого пункта нет.");
        }else{
            System.out.println("Подзадачу под каким номером нужно удалить?");
            int number = scanner.nextInt();
            if(listEpic.containsKey(number)){
                listEpic.remove(number);
                System.out.println("Подзадача удалена.");
            }else{
                System.out.println("Подзадачи под таким номером нет.");
                deleteTaskById();
            }
        }
    }

    // Дополнительные методы:
    //Получение списка всех подзадач определённого эпика.
    public void getSubtaskListOfEpic(){

    }

    //Управление статусами осуществляется по следующему правилу:
    //Менеджер сам не выбирает статус для задачи. Информация о нём приходит менеджеру вместе с информацией о самой задаче. По этим данным в одних случаях он будет сохранять статус, в других будет рассчитывать.

    //Для эпиков:
    //если у эпика нет подзадач или все они имеют статус NEW, то статус должен быть NEW.
    //если все подзадачи имеют статус DONE, то и эпик считается завершённым — со статусом DONE.
    //во всех остальных случаях статус должен быть IN_PROGRESS.
}
