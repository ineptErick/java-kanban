package Models;

import java.util.ArrayList;
import java.util.List;

public class Epic extends Task {

    private List<Integer> subtaskIds = new ArrayList<>();

    public Epic(String name, String description, Status status) {
        super(name, description, status);
    }

    public void addSubtask(int subId) {
        this.subtaskIds.add(subId);
    }

    public ArrayList<Integer> getSubtasksId() {
        return (ArrayList<Integer>) subtaskIds;
    }

    public void setSubtasksId(List<Integer> subtasksId) {
        this.subtaskIds = subtasksId;
    }

    public List<Integer> getSubtaskIds() {
        return subtaskIds;
    }

    @Override
    public String toString() {
        return "Models.Epic{" +
                "subTaskIds=" + subtaskIds +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", status='" + getStatus() + '\'' +
                '}';
    }
}
