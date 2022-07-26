
public class Subtask extends Epic {
    String name = "";
    int id = 0;
    String description = "";
    String status = "";


    public Subtask(String name, int id, String description, String status) {
        super(name, id, description, status);
        this.name = name;
        this.id = id;
        this.description = description;
        this.status = status;
    }
}
