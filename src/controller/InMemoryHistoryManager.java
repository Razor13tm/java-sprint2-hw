package controller;

import model.Task;

import java.util.ArrayList;
import java.util.List;

public class InMemoryHistoryManager implements HistoryManager {
    private final List<Task> history = new ArrayList<>();

    @Override
    public void add(Task task) {
        if (history.size() < 10) {
            history.add(task);
            return;
        }
        history.remove(0);
        history.add(task);
    }

    @Override
    public List<Task> getHistory() {
        return history;
    }
}