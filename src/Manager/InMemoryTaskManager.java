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
    private final Map <Integer, Task> listTask = new HashMap<>();
    private final Map<Integer, Epic> listEpic = new HashMap<>();
    private final Map <Integer, Subtask> listSubtask = new HashMap<>();
    private final HistoryManager history = Managers.getDefaultHistory();

    // Создание задачи
    @Override
    public int addTask(Task task) {
        task.setId(id++);
        listTask.put(task.getId(), task);
        history.add(task);
        return task.getId();
    }

    // Создание эпика
    @Override
    public int addEpic(Epic epic) {
        epic.setId(id++);
        listEpic.put(epic.getId(),epic);
        history.add(epic);
        return epic.getId();
    }

    // Создание подзадачи
    @Override
    public int addSubtask(Subtask subtask) {
        int epicId = subtask.getEpicId();
        Epic epic = listEpic.get(epicId);
        subtask.setId(id++);
        listSubtask.put(subtask.getId(), subtask);
        epic.addSubtask(subtask.getId());
        history.add(epic);
        updateEpic(epic);
        return subtask.getId();
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
            return;
        }else{
            listTask.clear();
        }
    }

    @Override
    //Удаление всех эпиков.
    public void deleteAllEpics(){
        if(listEpic.isEmpty()){
            System.out.println("Нет доступных эпиков для удаления.");
            return;
        }else{
            listSubtask.clear();
            listEpic.clear();
        }
    }

    @Override
    //Удаление всех подзадач.
    public void deleteAllSubtasks(){
        if(listSubtask.isEmpty()){
            System.out.println("Нет доступных подзадач для удаления.");
            return;
        }
        listSubtask.clear();
    }

    @Override
    //Получение задачи по идентификатору.
    public Task getTaskById(int id){
        history.add(listTask.get(id));
        return listTask.get(id);
    }

    @Override
    //Получение эпика по идентификатору.
    public Epic getEpicById(int id){
        history.add(listEpic.get(id));
        return listEpic.get(id);
    }

    @Override
    //Получение подзадачи по идентификатору.
    public Subtask getSubtaskById(int id){
        history.add(listSubtask.get(id));
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
            updateEpicStatus(epic);
            System.out.println("Эпик обновлен!");
        }else{
            System.out.println("Эпика с таким id не существует.");
            System.out.println("Не удалось обновить эпик.");
        }
    }

    @Override
    // Обновление подзадачи.
    public void updateSubtask(Subtask subtask){
        if(listSubtask.containsKey(subtask.getId())){
            Epic epic = listEpic.get(subtask.getEpicId());
            if(listEpic.containsKey(epic.getId())){
                listSubtask.put(subtask.getId(),subtask);
                updateEpicStatus(epic);
                System.out.println("Подзадача обновлена!");
            }
        }else{
            System.out.println("Подзадачи с таким id не существует.");
            System.out.println("Не удалось обновить подзадачу.");
        }
    }

    @Override
    // Удаление задачи по идентификатору.
    public void deleteTaskById(int id){
        if(listTask.containsKey(id)){
            listTask.remove(id);
            history.remove(id);
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
            Epic currentEpic = listEpic.get(id);
            Epic epic = listEpic.get(id);
            for(int subtaskId : epic.getSubtaskIds()){
                listSubtask.remove(subtaskId);
                history.remove(subtaskId);
            }
            System.out.println("Эпик удален.");

        }else{
            System.out.println("Эпика с таким id не существует.");
            System.out.println("Не удалось удалить эпик.");
        }
    }

    @Override
    // Удаление подзадачи по идентификатору.
    public void deleteSubtaskById(int id){
            int epicId = listSubtask.get(id).getEpicId();
            Subtask subtask = listSubtask.get(id);
            Epic epic = this.getEpicById(subtask.getEpicId());
            if(listSubtask.containsKey(id)){
                if(listEpic.containsKey(epic.getId())){
                    listEpic.get(epicId).getSubtaskIds().remove(id);
                    updateEpicStatus(epic);
                    listSubtask.remove(id);
                    history.remove(id);
                    System.out.println("Подзадача удалена.");
                }
            }
    }

    @Override
    // Дополнительные методы:
    // Получение списка всех подзадач определённого эпика.
    public ArrayList<Subtask> getSubtaskListByEpic(int id){
        if (listEpic.containsKey(id)) {
            ArrayList<Subtask> subtasksNew = new ArrayList<>();
            Epic epic = listEpic.get(id);
            for(int i = 0; i < epic.getSubtaskIds().size(); i++){
                subtasksNew.add(listSubtask.get(epic.getSubtaskIds().get(i)));
            }
            return subtasksNew;
        } else {
            System.out.println("Эпика с таким id не существует.");
            System.out.println("Не удалось получить список подзадач.");
        }
        return new ArrayList<>();
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
            Subtask subtask = listSubtask.get(id);
            if(status==null){
                status = subtask.getStatus();
                continue;
            }
            if(status.equals(subtask.getStatus())
                    && !status.equals(Status.IN_PROGRESS)) {
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

    // История просмотров задач
    @Override
    public List<Task> getHistory() {
        return history.getHistory();
    }

    @Override
    public String toString() {
        return "InMemoryTaskManager{" +
                "history=" + history +
                '}';
    }


}
