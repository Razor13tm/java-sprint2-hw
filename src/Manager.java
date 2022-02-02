import java.util.*;

public class Manager {

    private Map<Integer, Task> task;
    private Map<Integer, Subtask> subTask;
    private Map<Integer, Epic> epic;

    public Manager() {
        task = new HashMap();
        subTask = new HashMap();
        epic = new HashMap();
    }

    public Task getTask(int id) {
        return task.get(id);
    }

    public Subtask getSubtask(int id) {
        return subTask.get(id);
    }

    public Epic getEpic(int id) {
        return epic.get(id);
    }

    public void newTask(Task task) {
        this.task.put(task.getId(), task);
    }

    public Subtask newSubtask(Subtask subtask) {
        subTask.put(subtask.getId(), subtask);
        return subtask;
    }

    public Epic newEpic(Epic epic) {
        return this.epic.put(epic.getId(), epic);
    }

    public void removeAllTasks() {
        task.clear();
    }

    public void removeAllSubtasks() {
        subTask.clear();
    }

    public void removeAllEpics() {
        epic.clear();
    }

    public void updateTask(Task task) {
        this.task.put(task.getId(), task);
    }

    public void updateSubtask(Subtask subtask) {
        subTask.put(subtask.getId(), subtask);
    }

    public void updateEpic(Epic epic) {
        this.epic.put(epic.getId(), epic);
    }

    public void removeTask(int number) {
        task.remove(number);
    }

    public void removeSubtask(int number) {
        subTask.remove(number);
    }

    public void removeEpic(int number) {
        ArrayList<Integer> id_s = getEpic(number).getSubtaskId();
        for (Integer id : id_s) {
            subTask.remove(id);
        }
        epic.remove(number);
    }

    public ArrayList<Subtask> getEpicSubtasks(Epic epic) {
        return epic.getSubtasks();
    }

    private void setEpicStatus(Subtask subtask) {
        Epic epic = subtask.getFather();
        int newStatus = 0;
        int doneStatus = 0;
        int count = getEpicSubtasks(epic).size();
        for (Subtask lSubtask : getEpicSubtasks(epic)) {
            switch (lSubtask.getStatus()) {
                case NEW:
                    newStatus++;
                    break;
                case DONE:
                    doneStatus++;
                    break;
            }
        }
        if (count == 0) {
            epic.setStatus(Status.NEW);
        } else if (newStatus == count) {
            epic.setStatus(Status.NEW);
        } else if (doneStatus == count) {
            epic.setStatus(Status.DONE);
        } else epic.setStatus(Status.IN_PROGRESS);
    }

    public Collection<Task> getAllTasks() {
        return task.values();
    }

    public Collection<Subtask> getAllSubtasks() {
        return subTask.values();
    }

    public Collection<Epic> getAllEpics() {
        return epic.values();
    }

    public void setSubtaskStatus(Subtask subtask, Status status) {
        subtask.setStatus(status);
        setEpicStatus(subtask);
    }

    public void setTaskStatus(Task task, Status status) {
        task.setStatus(status);
    }
}