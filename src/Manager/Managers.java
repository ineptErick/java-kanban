package Manager;

import Models.Epic;
import Models.Subtask;
import Models.Task;

import java.util.ArrayList;

public class Managers {
    // Managers должен сам подбирать нужную реализацию TaskManager
    // и возвращать объект правильного типа

    Manager getDefault() {
        Manager manager = new Manager() {
            @Override
            public void addTask(Task task) {

            }

            @Override
            public void addEpic(Epic epic) {

            }

            @Override
            public void addSubtask(Subtask subtask) {

            }

            @Override
            public ArrayList<Task> printAndGetTasks() {
                return null;
            }

            @Override
            public ArrayList<Epic> printAndGetEpics() {
                return null;
            }

            @Override
            public ArrayList<Subtask> printAndGetSubtasks() {
                return null;
            }

            @Override
            public void deleteAllTasks() {

            }

            @Override
            public void deleteAllEpics() {

            }

            @Override
            public void deleteAllSubtasks(Epic epic) {

            }

            @Override
            public Task getTaskById(int id) {
                return null;
            }

            @Override
            public Epic getEpicById(int id) {
                return null;
            }

            @Override
            public Subtask getSubtaskById(int id) {
                return null;
            }

            @Override
            public void updateTask(Task task) {

            }

            @Override
            public void updateEpic(Epic epic) {

            }

            @Override
            public void updateSubtask(Epic epic, Subtask subtask) {

            }

            @Override
            public void deleteTaskById(int id) {

            }

            @Override
            public void deleteEpicById(int id) {

            }

            @Override
            public void deleteSubtaskById(int id) {

            }

            @Override
            public ArrayList<Task> getSubtaskListByEpic(Epic epic) {
                return null;
            }

            @Override
            public void updateEpicStatus(Epic epic) {

            }

            @Override
            public void updateEpicStatusSecondVar(Epic epic) {

            }

            @Override
            public void getHistory() {

            }
        };
        return manager;
    }

    public static HistoryManager getDefaultHistory(){
        InMemoryHistoryManager inMemHM = new InMemoryHistoryManager();
        return inMemHM;
    }
}
