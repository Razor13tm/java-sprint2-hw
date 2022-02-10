package model;

import java.util.ArrayList;

public class Epic extends Task {

    private final ArrayList<Subtask> subtasks = new ArrayList<>();

    public Epic(String name, String description, int id, Status status) {
        super(name, description, id, status);
    }

    public void addSubtask(Subtask subtask) {
        subtasks.add(subtask);
    }

    public void deleteSubtask(Subtask subtask) {
        subtasks.remove(subtask);
    }

    public void updateSubtask(Subtask subtask) {
        for (int i = 0; i < subtasks.size(); i++) {
            if (subtasks.get(i).getId() == subtask.getId()) {
                subtasks.set(i, subtask);
                return;
            }
        }
    }

    @Override
    public Status getStatus() {
        if (subtasks.isEmpty()) {
            return Status.NEW;
        }
        int newStatus = 0;
        int doneStatus = 0;

        for (Subtask lSubtask : subtasks) {
            switch (lSubtask.getStatus()) {
                case NEW:
                    newStatus++;
                    break;
                case DONE:
                    doneStatus++;
                    break;
            }
        }
        if (newStatus == subtasks.size()) {
            return Status.NEW;
        }
        if (doneStatus == subtasks.size()) {
            return Status.DONE;
        }
        return Status.IN_PROGRESS;
    }

    public ArrayList<Subtask> getSubtasks() {
        return subtasks;
    }

    @Override
    public String toString() {
        return "Epic{" +
                "\n\t name= " + getName() +
                "\n\t description= " + getDescription() +
                "\n\t id= " + getId() +
                "\n\t status= " + getStatus() +
                "\n\t subtasks= " + subtasks +
                "\n\t}";
    }
}