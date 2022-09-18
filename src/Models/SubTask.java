package Models;

import Enums.Status;

public class SubTask extends Task {
    protected int epicId;

    public SubTask(String name, String description, Status status, int epicId) {
        super(name, description, status);
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
                ", name='" + getName() + '\'' +
                ", description=" + getDescription() + '\'' +
                ", status='" + getStatus() +
                '}';
    }
}
