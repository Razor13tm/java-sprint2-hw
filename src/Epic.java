import java.util.ArrayList;

public class Epic extends Task {

    private final ArrayList<Subtask> subtask = new ArrayList<>();

    public Epic(String name, String description, int id, ArrayList<Subtask> subtasks) {
        super(name, description, id);
        for (Subtask subtask : subtasks) {
            subtask.setFather(this);
            this.subtask.add(subtask);
        }
    }

    public Epic(String name, String description, int id) {
        super(name, description, id);
    }

    public ArrayList<Integer> getSubtaskId() {
        ArrayList<Integer> id = new ArrayList<>();
        for (Subtask subtask : subtask) {
            id.add(subtask.getId());
        }
        return id;
    }

    public ArrayList<Subtask> getSubtasks() {
        return subtask;
    }

    @Override
    public String toString() {
        return "Epic{" +
                "\n\t name= " + name +
                "\n\t description= " + description +
                "\n\t id= " + id +
                "\n\t status= " + status +
                "\n\t subtasks= " + subtask +
                "\n\t}";
    }
}