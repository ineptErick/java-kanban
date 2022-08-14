package Models;

import java.util.ArrayList;
import java.util.List;

public class Epic extends Task{

    private List<Integer> subtaskIds = new ArrayList<>();

    public Epic(int id, String name, String description, Status status) {
        super(id, name, description, status);
    }

    public void addSubtask(int subId){
        this.subtaskIds.add(subId);
    }

    public ArrayList<Integer> getSubtasksId() {
        return (ArrayList<Integer>) subtaskIds;
    }

    public void setSubtasksId(ArrayList<Integer> subtasksId) {
        this.subtaskIds = subtasksId;
    }

    public ArrayList<Integer> getSubtaskIds() {
        return (ArrayList<Integer>) subtaskIds;
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
