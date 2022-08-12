package Models;

import java.util.ArrayList;

public class Epic extends Task{

    private ArrayList<Integer> subtaskIds;

    public Epic(int id, String name, String description, Status status) {
        super(id, name, description, status);
        subtaskIds=new ArrayList<>();
    }

    public void addSubtask(int subId){
        this.subtaskIds.add(subId);
    }

    public ArrayList<Integer> getSubtasksId() {
        return subtaskIds;
    }

    public void setSubtasksId(ArrayList<Integer> subtasksId) {
        this.subtaskIds = subtasksId;
    }

    public ArrayList<Integer> getSubtaskIds() {
        return subtaskIds;
    }

    @Override
    public String toString() {
        return "Models.Epic{" +
                "subTaskIds=" + subtaskIds +
                ", id=" + getId() +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", status='" + getStatus() + '\'' +
                '}';
    }
}
