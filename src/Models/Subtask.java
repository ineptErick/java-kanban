package Models;

public class Subtask extends Task{
    protected int epicId;

    public Subtask(int id, String name, String description, String status,int epicId) {
        super(id, name, description, status);
        this.epicId = epicId;
    }

    public int getEpicId() {
        return epicId;
    }

    public void setEpicId(int epicId) {
        this.epicId = epicId;
    }

    @Override
    public String toString() {
        return "Product{" +
                "epicId=" + epicId + '\'' +
                "id=" + getId() +
                ", name='" + getName() + '\'' +
                ", description=" + getDescription() + '\'' +
                ", status='" + getStatus() +
                '}';
    }
}
