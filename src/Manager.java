import java.util.HashMap;
import java.util.Scanner;

public class Manager {
    protected int id = 1;

    // Возможность хранить задачи всех типов.
    protected HashMap<Integer, Task> listTask;
    protected HashMap <Integer, HashMap<Integer,Subtask>> listEpic;
    protected HashMap <Integer, Subtask> listSubtask;

    //Получение списка всех задач.
    public void printTask(){
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
    //Всех эпиков.
    void printEpic(){
        if(listEpic.isEmpty()){
            System.out.println("Нет доступных эпиков.");
        }else{
            for(Integer id : listEpic.keySet()){
                for(HashMap <Integer, Subtask> subtask : listEpic.values()){
                    System.out.println("Номер эпика: "+listEpic.get(id));
                    System.out.println(subtask.toString());
                }
            }
        }
    }
    //Всех подзадач.
    void printSubtask(){
        if(listSubtask.isEmpty()){
            System.out.println("Нет доступных подзадач.");
        }else{
            for(Integer id : listSubtask.keySet()){
                for(Task task : listSubtask.values()){
                    System.out.println("Номер подзадачи: "+id);
                    System.out.println(task.toString());
                }
            }
        }
    }

    //Удаление всех задач.
    void deleteAllTasks(){
        if(listTask.isEmpty()){
            System.out.println("Нет доступных задач для удаления.");
        }else{
            listTask.clear();
        }
    }
    //Всех эпиков.
    void deleteAllEpics(){
        if(listEpic.isEmpty()){
            System.out.println("Нет доступных эпиков для удаления.");
        }else{
            listEpic.clear();
        }
    }
    //Всех подзадач.
    void deleteAllSubtasks(){
        if(listSubtask.isEmpty()){
            System.out.println("Нет доступных подзадач для удаления.");
        }else{
            listSubtask.clear();
        }
    }

    //Получение задачи по идентификатору.
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
    //Получение эпика по идентификатору.
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
                        for (HashMap<Integer, Subtask> epic : listEpic.values()) {
                            System.out.println("Номер эпика: " + number);
                            System.out.println(epic.toString());
                        }
                    }
                }
            }else{
                System.out.println("Эпика под таким номером нет.");
            }

        }
    }
    //Получение подзадачи по идентификатору.
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

    //Создание задачи. Сам объект должен передаваться в качестве параметра.
    Task addTask(String name, String description, String status) {
        Task task = new Task(id,name,description,status);
        id++;
        listTask.put(id, task);
        return task;
    }
    //Эпика.
    Epic addEpic(String name, int id, String description, String status) {
        Epic epic = new Epic(id,name,description,status);
        HashMap<Integer,Subtask> hashMap = new HashMap<>();
        id++;
        listEpic.put(id, hashMap);
        return epic;
    }
    //Подзадачи.
    Subtask addSubtask(int id, String name, String description, String status, int epicId) {
        Subtask subtask = new Subtask(id,name,description,status,epicId);
        id++;
        listSubtask.put(id, subtask);
        return subtask;
    }

    //Обновление. Новая версия объекта с верным идентификатором передаётся в виде параметра.
    public void updateTask(String name, String description, String status){
        Task task = new Task(id, name, description, status);
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
    //Эпик.
    public void updateEpic(String name, String description, String status){
        Epic epic = new Epic(id, name, description, status);
        for (Integer key : listEpic.keySet()){
            for(Integer sub : listSubtask.keySet()){
                for(Subtask tsk : listSubtask.values()){
                    if(tsk.id==id){
                        tsk.description=description;
                        tsk.name=name;
                    }
                }
            }
        }
        System.out.println("Эпик обновлен!");
    }
    //Подзадача.
    public void updateSubtask(String name, String description, String status, int epicId){
        Subtask subtask = new Subtask(id, name, description, status, epicId);
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
    //Эпика.
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
                deleteEpicById();
            }
        }
    }
    //Подзадачи.
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
                deleteSubtaskById();
            }
        }
    }

    // Дополнительные методы:
    //Получение списка всех подзадач определённого эпика.
    public void getSubtaskListByEpic(){
            Scanner scanner = new Scanner(System.in);

            if(listSubtask.isEmpty()){
                System.out.println("Нет доступных подзадач.");
            }else {
                System.out.println("Список подзадач под каким номером эпика хотите получить?");
                int number = scanner.nextInt();

                if (listEpic.containsKey(number)) {
                    for (int id : listEpic.keySet()) {
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
}
