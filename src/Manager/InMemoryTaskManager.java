package Manager;

import Models.Epic;
import Models.Status;
import Models.Subtask;
import Models.Task;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static Models.Status.*;

public class InMemoryTaskManager implements Manager {

    private int id = 1;

    // Возможность хранить задачи всех типов.
    private Map <Integer, Task> listTask = new HashMap<>();
    private Map<Integer, Epic> listEpic = new HashMap<>();
    private Map <Integer, Subtask> listSubtask = new HashMap<>();
    private HistoryManager history = Managers.getDefaultHistory();

    // Создание задачи
    @Override
    public void addTask(Task task) {
        task.setId(id++);
        listTask.put(task.getId(), task);
        history.add(task);
    }

    // Создание эпика
    @Override
    public void addEpic(Epic epic) {
        epic.setId(id++);
        listEpic.put(epic.getId(),epic);
        history.add(epic);
    }

    // Создание подзадачи
    @Override
    public void addSubtask(Subtask subtask) {
        int epicId = subtask.getEpicId();
        Epic epic = listEpic.get(epicId);
        subtask.setId(id++);
        listSubtask.put(subtask.getId(), subtask);
        epic.addSubtask(subtask.getId());
        history.add(epic);
        updateEpic(epic);
    }

    //Получение списка всех задач.
    @Override
    public ArrayList<Task> printAndGetTasks(){
        if(listTask.isEmpty()){
            System.out.println("Нет доступных задач.");
            return null;
        }
        return new ArrayList<>(listTask.values());
    }

    @Override
    //Получение списка всех эпиков.
    public ArrayList<Epic> printAndGetEpics(){
        if(listEpic.isEmpty()) {
            System.out.println("Нет доступных эпиков.");
            return null;
        }
        return new ArrayList<>(listEpic.values());
    }

    @Override
    //Получение списка всех подзадач.
    public ArrayList<Subtask> printAndGetSubtasks(){
        if(listSubtask.isEmpty()){
            System.out.println("Нет доступных подзадач.");
            return null;
        }
        return new ArrayList<>(listSubtask.values());
    }

    @Override
    //Удаление всех задач.
    public void deleteAllTasks(){
        if(listTask.isEmpty()){
            System.out.println("Нет доступных задач для удаления.");
        }else{
            listTask.clear();
        }
    }

    @Override
    //Удаление всех эпиков.
    public void deleteAllEpics(){
        if(listEpic.isEmpty()){
            System.out.println("Нет доступных эпиков для удаления.");
        }else{
            listEpic.clear();
        }
    }

    @Override
    //Удаление всех подзадач.
    public void deleteAllSubtasks(Epic epic){
        if(listEpic.containsValue(epic)){
            List<Task> currentSubtasksList = getSubtaskListByEpic(epic);
            for(Task subtask : currentSubtasksList){
                int subtaskId = subtask.getId();
                deleteSubtaskById(subtaskId);
            }
        }else{
            System.out.println("Нет доступных подзадач для удаления.");
        }

        updateEpicStatus(epic);
    }

    @Override
    //Получение задачи по идентификатору.
    public Task getTaskById(int id){
        return listTask.get(id);
    }

    @Override
    //Получение эпика по идентификатору.
    public Epic getEpicById(int id){
        return listEpic.get(id);
    }

    @Override
    //Получение подзадачи по идентификатору.
    public Subtask getSubtaskById(int id){
        return listSubtask.get(id);
    }

    @Override
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

    @Override
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

    @Override
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

    @Override
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

    @Override
    // Удаление эпика по идентификатору.
    public void deleteEpicById(int id){
        if(listEpic.containsKey(id)){
            Epic currentEpic = getEpicById(id);
            deleteAllSubtasks(currentEpic);
            listEpic.remove(id);
            System.out.println("Эпик удален.");

        }else{
            System.out.println("Эпика с таким id не существует.");
            System.out.println("Не удалось удалить эпик.");
        }
    }

    @Override
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

    @Override
    // Дополнительные методы:
    // Получение списка всех подзадач определённого эпика.
    public List<Task> getSubtaskListByEpic(Epic epic){
        List<Task> subListById = new ArrayList<>();
        if (listEpic.containsKey(epic.getId())) {
            for(Integer subtaskId : listEpic.get(epic.getId()).getSubtasksId()) {
                subListById.add(getSubtaskById(subtaskId));
            }
        } else {
            System.out.println("Эпика с таким id не существует.");
            System.out.println("Не удалось получить список подзадач.");
        }
        return subListById;
    }

    @Override
    // Обновление статуса эпика.
    public void updateEpicStatus(Epic epic){
        List<Integer> subs = epic.getSubtaskIds();
        if(subs.isEmpty()){
            epic.setStatus(NEW);
            return;
        }
        Status status = null;
        for(int id : subs){
            final Subtask subtask = listSubtask.get(id);
            if(status==null){
                status = subtask.getStatus();
                continue;
            }
            if(status.equals(subtask.getStatus())&&!status.equals("IN_PROGRESS")){
                continue;
            }
            epic.setStatus(IN_PROGRESS);
            return;
        }
        epic.setStatus(status);
    }

    @Override
    // облегченный вариант обновления статуса метода
    public void updateEpicStatusSecondVar(Epic epic){
        int newNum = 0;
        int doneNum = 0;

        List<Integer> subs = epic.getSubtaskIds();
        if(subs.isEmpty()){
            epic.setStatus(NEW);
            return;
        }
        Status status = null;
        for(int id : subs){
            final Subtask subtask = listSubtask.get(id);
            status = subtask.getStatus();

            if(status.equals(NEW)){
                newNum++;
            }
            if(status.equals(DONE)){
                doneNum++;
            }

        }
        if(newNum==0||newNum==epic.getSubtaskIds().size()){
            epic.setStatus(NEW);
        } else if (doneNum==epic.getSubtaskIds().size()) {
            epic.setStatus(DONE);
        }else{
            epic.setStatus(IN_PROGRESS);
        }
    }

    @Override
    public ArrayList<Task> getHistory() {
        return history.getHistory();
    }


}
