package model;

public class Subtask extends Task {

    private Epic parent;

    public Subtask(String title, String description, int id, Status status, Epic parent) {

        super(title, description, id, status);
        this.parent = parent;
    }

    public Epic getParent() {
        return parent;
    }

    @Override
    public String toString() {
        return "Subtask{" +
                "\n\t name= " + getName() +
                "\n\t description= " + getDescription() +
                "\n\t id= " + getId() +
                "\n\t status= " + getStatus() +
                "\n\t}";
    }
}