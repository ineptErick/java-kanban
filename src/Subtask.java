
public class Subtask extends Task {
    String name = "";
    String description = "";
    String status = "";


    public Subtask(String name, String description, String status, String name1, String description1, String status1) {
        super(name, description, status);
        this.name = name1;
        this.description = description1;
        this.status = status1;
    }
}
