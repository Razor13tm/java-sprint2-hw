package controller;

import model.Epic;
import model.Subtask;
import model.Task;

import java.util.ArrayList;
import java.util.List;

public interface TaskManager {

    Task getTask(int id);

    Subtask getSubtask(int id);

    Epic getEpic(int id);

    void newTask(Task task);

    Subtask newSubtask(Subtask subtask);

    Epic newEpic(Epic epic);

    void removeAllTasks();

    void updateTask(Task task);

    void updateSubtask(Subtask subtask);

    void updateEpic(Epic epic);

    void removeTask(int number);

    void removeSubtask(int number);

    void removeEpic(int number);

    List<Task> history();

    ArrayList<Subtask> getEpicSubtasks(Epic epic);

    List<Task> getAllTasks();

    List<Subtask> getAllSubtasks();

    List<Epic> getAllEpics();
}
