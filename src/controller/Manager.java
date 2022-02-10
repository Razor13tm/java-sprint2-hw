package controller;

import model.Epic;
import model.Subtask;
import model.Task;

import java.util.*;

public class Manager {

    private Map<Integer, Task> tasks;
    private Map<Integer, Subtask> subTasks;
    private Map<Integer, Epic> epics;

    public Manager() {
        tasks = new HashMap();
        subTasks = new HashMap();
        epics = new HashMap();
    }

    public Task getTask(int id) {
        return tasks.get(id);
    }

    public Subtask getSubtask(int id) {
        return subTasks.get(id);
    }

    public Epic getEpic(int id) {
        return epics.get(id);
    }

    public void newTask(Task task) {
        if (tasks.containsKey(task.getId())) {
            return;
        }
        this.tasks.put(task.getId(), task);
    }

    public Subtask newSubtask(Subtask subtask) {
        if (subTasks.containsKey(subtask.getId())) {
            return null;
        }
        subTasks.put(subtask.getId(), subtask);
        epics.get(subtask.getParent().getId()).addSubtask(subtask);
        return subtask;
    }

    public Epic newEpic(Epic epic) {
        if (epics.containsKey(epic.getId())) {
            return null;
        }
        return epics.put(epic.getId(), epic);
    }

    public void removeAllTasks() {
        tasks.clear();
        subTasks.clear();
        epics.clear();
    }

    public void updateTask(Task task) {
        tasks.put(task.getId(), task);
    }

    public void updateSubtask(Subtask subtask) {
        epics.get(subtask.getParent().getId()).updateSubtask(subtask);
        subTasks.put(subtask.getId(), subtask);
    }

    public void updateEpic(Epic epic) {
        Epic newEpic = epics.get(epic.getId());
        if (newEpic == null) {
            return;
        }

        for (Subtask s : newEpic.getSubtasks()) {
            newEpic.addSubtask(s);
        }

        epics.put(epic.getId(), newEpic);
    }

    public void removeTask(int number) {
        tasks.remove(number);
    }

    public void removeSubtask(int number) {
        Subtask subtask = subTasks.get(number);
        subTasks.remove(number);
        epics.get(subtask.getParent().getId()).deleteSubtask(subtask);
    }

    public void removeEpic(int number) {

        ArrayList<Subtask> subtasks = getEpic(number).getSubtasks();
        for (Subtask s : subtasks) {
            subTasks.remove(s.getId());
        }
        epics.remove(number);
    }

    public ArrayList<Subtask> getEpicSubtasks(Epic epic) {
        return epic.getSubtasks();
    }

    public List<Task> getAllTasks() {
        return new ArrayList<Task>(tasks.values());
    }

    public List<Subtask> getAllSubtasks() {
        return new ArrayList<Subtask>(subTasks.values());
    }

    public List<Epic> getAllEpics() {
        return new ArrayList<Epic>(epics.values());
    }

}