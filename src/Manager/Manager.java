package Manager;

import Models.Epic;
import Models.Subtask;
import Models.Task;

import java.util.ArrayList;
import java.util.HashMap;

public class Manager {
    private int id = 1;

    // Возможность хранить задачи всех типов.
    public HashMap<Integer, Task> listTask = new HashMap<>();
    public HashMap <Integer, Epic> listEpic = new HashMap<>();
    public HashMap <Integer, Subtask> listSubtask = new HashMap<>();

    // Создание задачи
    public void addTask(Task task) {
        task.setId(id++);
        listTask.put(task.getId(), task);
    }

    // Создание эпика
    public void addEpic(Epic epic) {
        epic.setId(id++);
        listEpic.put(epic.getId(),epic);
    }

    // Создание подзадачи
    public void addSubtask(Subtask subtask) {
        int epicId = subtask.getEpicId();
        Epic epic = listEpic.get(epicId);
        subtask.setId(id++);
        listSubtask.put(subtask.getId(), subtask);
        epic.addSubtask(subtask.getId());
        // !!!
        updateEpic(epic);
    }

    //Получение списка всех задач.
    public ArrayList<Task> printAndGetTasks(){
        ArrayList<Task> tasksArray = new ArrayList<>();
        if(listTask.isEmpty()){
            System.out.println("Нет доступных задач.");
        }else{
            for(Integer id : listTask.keySet()){
                for(Task tsk : listTask.values()){
                    tasksArray.add(id,tsk);
                }
            }
        }
        return tasksArray;
    }

    //Получение списка всех эпиков.
    public ArrayList<Epic> printAndGetEpics(){
        ArrayList<Epic> epicsArray = new ArrayList<>();
        if(listEpic.isEmpty()){
            System.out.println("Нет доступных эпиков.");
        }else{
            for(Integer id : listEpic.keySet()){
                for(Epic subtask : listEpic.values()){
                    epicsArray.add(id,subtask);
                }
            }
        }
        return epicsArray;
    }

    //Получение списка всех подзадач.
    public ArrayList<Subtask> printAndGetSubtasks(){
        ArrayList<Subtask> subtasksArray = new ArrayList<>();
        if(listSubtask.isEmpty()){
            System.out.println("Нет доступных подзадач.");
        }else{
            for(Integer id : listSubtask.keySet()){
                for(Subtask subtask : listSubtask.values()){
                    subtasksArray.add(id,subtask);
                }
            }
        }
        return subtasksArray;
    }

    //Удаление всех задач.
    public void deleteAllTasks(){
        if(listTask.isEmpty()){
            System.out.println("Нет доступных задач для удаления.");
        }else{
            listTask.clear();
        }
    }

    //Удаление всех эпиков.
    public void deleteAllEpics(){
        if(listEpic.isEmpty()){
            System.out.println("Нет доступных эпиков для удаления.");
        }else{
            listEpic.clear();
        }
    }

    //Удаление всех подзадач.
    public void deleteAllSubtasks(Epic epic){
        if(listEpic.containsValue(epic)){
            ArrayList<Task> currentSubtasksList = getSubtaskListByEpic(epic);
            for(Task subtask : currentSubtasksList){
                int subtaskId = subtask.getId();
                deleteSubtaskById(subtaskId);
            }
        }else{
            System.out.println("Нет доступных подзадач для удаления.");
        }

        updateEpicStatus(epic);
    }

    //Получение задачи по идентификатору.
    public Task getTaskById(int id){
        return listTask.get(id);
    }

    //Получение эпика по идентификатору.
    public Epic getEpicById(int id){
      return listEpic.get(id);
    }

    //Получение подзадачи по идентификатору.
    public Subtask getSubtaskById(int id){
        return listSubtask.get(id);
    }

    // Обновление. Новая версия объекта с верным идентификатором передаётся в виде параметра.
    // Обновление задачи.
    public void updateTask(Task task){
        if(listTask.containsKey(task.getId())){
            listTask.put(task.getId(),task);
            System.out.println("Задача обновлена!");
        }else{
            System.out.println("Задачи с таким id не существует.");
            System.out.println("Не удалось обновить задачу.");
        }
    }

    // Обновление эпика.
    public void updateEpic(Epic epic){
        if(listEpic.containsKey(epic.getId())){
            listEpic.put(epic.getId(),epic);
            System.out.println("Эпик обновлен!");
        }else{
            System.out.println("Эпика с таким id не существует.");
            System.out.println("Не удалось обновить эпик.");
        }
        updateEpicStatus(epic);
    }

    // Обновление подзадачи.
    public void updateSubtask(Epic epic, Subtask subtask){
        if(listSubtask.containsKey(subtask.getId())){
            listSubtask.put(subtask.getId(),subtask);
            System.out.println("Подзадача обновлена!");
        }else{
            System.out.println("Подзадачи с таким id не существует.");
            System.out.println("Не удалось обновить подзадачу.");
        }
        updateEpicStatus(epic);
    }

    // Удаление задачи по идентификатору.
    public void deleteTaskById(int id){
        if(listTask.containsKey(id)){
            listTask.remove(id);
            System.out.println("Задача удалена.");
        }else{
            System.out.println("Задачи с таким id не существует.");
            System.out.println("Не удалось удалить задачу.");
        }
    }

    // Удаление эпика по идентификатору.
    public void deleteEpicById(int id){
        if(listEpic.containsKey(id)){
            Epic currentEpic = getEpicById(id);
            ArrayList<Task> currentSubtasksList = getSubtaskListByEpic(currentEpic);
            deleteAllSubtasks(currentEpic);
            listEpic.remove(id);
            System.out.println("Эпик удален.");

        }else{
            System.out.println("Эпика с таким id не существует.");
            System.out.println("Не удалось удалить эпик.");
        }
    }

    // Удаление подзадачи по идентификатору.
    public void deleteSubtaskById(int id){
        if(listSubtask.containsKey(id)){
            listTask.remove(id);
            System.out.println("Подзадача удалена.");
        }else{
            System.out.println("Подзадачи с таким id не существует.");
            System.out.println("Не удалось удалить подзадачу.");
        }
        updateEpicStatus(listEpic.get(id));
    }

    // Дополнительные методы:
    // Получение списка всех подзадач определённого эпика.
    public ArrayList<Task> getSubtaskListByEpic(Epic epic){
        ArrayList<Task> subListById = new ArrayList<>();
        if(listEpic.containsKey(epic.getId())) {
            for (int id : listEpic.keySet()) {
                if (id == epic.getId()) {
                    for (int subtaskId : epic.getSubtaskIds()) {
                        subListById.add(getSubtaskById(subtaskId));
                    }
                }
            }
        }else{
            System.out.println("Эпика с таким id не существует.");
            System.out.println("Не удалось получить список подзадач.");
        }
        return subListById;
    }

    // Обновление статуса эпика.
    public void updateEpicStatus(Epic epic){
        ArrayList<Integer> subs = epic.getSubtaskIds();
        if(subs.isEmpty()){
            epic.setStatus("NEW");
            return;
        }
        String status = null;
        for(int id : subs){
            final Subtask subtask = listSubtask.get(id);
            if(status==null){
                status = subtask.getStatus();
                continue;
            }
            if(status.equals(subtask.getStatus())&&!status.equals("IN_PROGRESS")){
                continue;
            }
            epic.setStatus("IN_PROGRESS");
            return;
        }
        epic.setStatus(status);
    }

    // облегченный вариант обновления статуса метода
    public void updateEpicStatusSecondVar(Epic epic){
        int newNum = 0;
        int doneNum = 0;

        ArrayList<Integer> subs = epic.getSubtaskIds();
        if(subs.isEmpty()){
            epic.setStatus("NEW");
            return;
        }
        String status = null;
        for(int id : subs){
            final Subtask subtask = listSubtask.get(id);
            status = subtask.getStatus();

                if(status.equals("NEW")){
                    newNum++;
                }
                if(status.equals("DONE")){
                    doneNum++;
                }

        }
        if(newNum==0||newNum==epic.getSubtaskIds().size()){
            epic.setStatus("NEW");
        } else if (doneNum==epic.getSubtaskIds().size()) {
            epic.setStatus("DONE");
        }else{
            epic.setStatus("IN_PROGRESS");
        }
    }
}
