import controller.Manager;
import model.Epic;
import model.Status;
import model.Subtask;
import model.Task;

import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {

        Manager manager = new Manager();
        int id = manager.hashCode();

        manager.newTask(new Task("Задача 1", "Описание 1", ++id, Status.NEW));
        manager.newTask(new Task("Задача 2", "Описание 2", ++id, Status.NEW));

        Epic epic = new Epic("Эпик ", "Описание ", ++id, Status.NEW);
        manager.newEpic(epic);
        manager.newSubtask(new Subtask("Подзадача 1", "Описание 1", ++id, Status.NEW, epic));
        manager.newSubtask(new Subtask("Подзадача 2", "Описание 2", ++id, Status.NEW, epic));

        Epic epic2 = new Epic("Эпик ", "Описание ", ++id, Status.NEW);
        manager.newEpic(epic2);
        manager.newSubtask(new Subtask("Подзадача 1", "Описание 1", ++id, Status.NEW, epic2));


        for (Epic epi : manager.getAllEpics()) {
            ArrayList<Subtask> list = epi.getSubtasks();
            System.out.println(list.size());
        }

        for (Epic epi : manager.getAllEpics()) {
            System.out.println(epi);
        }

        for (Task task : manager.getAllTasks()) {
            System.out.println(task);
        }

        for (Subtask subtask : manager.getAllSubtasks()) {
            System.out.println(subtask);
        }

        System.out.println("\nМеняем статус задач");
        for (Task task : manager.getAllTasks()) {
            manager.updateTask(new Task(task.getName(), task.getDescription(), task.getId(), Status.IN_PROGRESS));

        }

        System.out.println("\nНовый список задач с новым статусом");
        for (Task task : manager.getAllTasks()) {
            System.out.println(task);
        }

        System.out.println("\nНовый список подзадач и эпиков с новым статусом");
        for (Epic epi : manager.getAllEpics()) {
            System.out.println(epi);
        }

        System.out.println("\nМеняем статус подзадач");
        for (Subtask subtask : manager.getAllSubtasks()) {
            Epic epicNew = subtask.getParent();
            manager.updateSubtask(new Subtask(subtask.getName(), subtask.getDescription(), subtask.getId(), Status.IN_PROGRESS, epicNew));
            manager.updateEpic(new Epic(epicNew.getName(), epicNew.getDescription(), epicNew.getId(), Status.IN_PROGRESS));
        }

        for (Subtask subtask : manager.getAllSubtasks()) {
            System.out.println(subtask);
        }

        System.out.println("\nУдаляем задачу");
        for (Task task : manager.getAllTasks()) {
            System.out.println(task);
            manager.removeTask(task.getId());
            break;
        }

        System.out.println("\nУдалить эпик");
        for (Epic epi : manager.getAllEpics()) {
            System.out.println(epi);
            manager.removeEpic(epi.getId());
            break;
        }

        System.out.println("\nНовый список задач, эпиков и подзадач");
        for (Epic epi : manager.getAllEpics()) {
            System.out.println(epi);
        }

        for (Task task : manager.getAllTasks()) {
            System.out.println(task);
        }

        for (Subtask subtask : manager.getAllSubtasks()) {
            System.out.println(subtask);
        }
    }
}