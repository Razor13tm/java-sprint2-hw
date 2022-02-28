package controller;

import model.Task;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InMemoryHistoryManager implements HistoryManager {

    private static final Map<Integer, Node> historyMap = new HashMap<>();
    Node tail;
    Node head;

    @Override
    public void add(Task task) {
        int taskId = task.getId();
        if (historyMap.containsKey(taskId)) {
            Node node = historyMap.get(taskId);
            removeNode(node);
        }
        Node lastNode = linkLast(task);
        historyMap.put(taskId, lastNode);
        tail = lastNode;
    }

    @Override
    public List<Task> getHistory() {
        return getTasks();
    }

    @Override
    public void remove(int id) {
        if (historyMap.containsKey(id)) {
            removeNode(historyMap.get(id));
        }
    }

    @Override
    public void update(Task updateTask) {
        Node oldNode = historyMap.get(updateTask.getId());
        if (oldNode != null) {
            Node newNode = new Node(oldNode.getPreviousNode(), oldNode.getNextNode(), updateTask);
            historyMap.put(updateTask.getId(), newNode);
            if (tail == oldNode) tail = newNode;
            if (head == oldNode) head = newNode;
        }
    }

    private Node linkLast(Task task) {
        Node node;
        if (tail == null) {
            node = new Node(null, null, task);
            head = node;
        } else {
            node = new Node(tail, null, task);
            tail.setNextNode(node);
        }
        return node;
    }

    private List<Task> getTasks() {
        if (head == null) {
            return new ArrayList<>();
        }
        ArrayList<Task> getTasks = new ArrayList<>(historyMap.size());
        getTasks.add(head.getTask());
        Node nextNode = head.getNextNode();
        while (nextNode != null) {
            getTasks.add(nextNode.getTask());
            nextNode = nextNode.getNextNode();
        }
        return getTasks;
    }

    private void removeNode(Node node) {
        Node previousNode = node.getPreviousNode();
        Node nextNode = node.getNextNode();
        historyMap.remove(node.getTask().getId());
        if ((previousNode == null) && (nextNode == null)) {
            head = null;
            tail = null;
        } else if (previousNode == null) {
            nextNode.setPreviousNode(null);
            head = nextNode;
        } else if (nextNode == null) {
            previousNode.setNextNode(null);
            tail = previousNode;
        } else {
            previousNode.setNextNode(nextNode);
            nextNode.setPreviousNode(previousNode);
        }
    }
}