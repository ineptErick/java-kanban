public class Epic extends Task{
    String name = "";
    int id = 0;
    String description = "";
    private String status = "";

    public String getEpicStatus() {
        return status;
    }

    public Epic(String name, int id, String description, String status) {
        super(name, id, description, status);
        this.name = name;
        this.id = id;
        this.description = description;
        this.status = getEpicStatus();
    }
}
