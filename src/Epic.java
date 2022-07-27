import java.util.ArrayList;

public class Epic extends Task{
    protected ArrayList<Integer> subtaskIds;

    public Epic(int id, String name, String description, String status) {
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

    @Override
    public String toString() {
        return "Epic{" +
                "subTaskIds=" + subtaskIds +
                ", id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}
