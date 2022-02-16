import controller.Managers;
import controller.TaskManager;
import model.Epic;
import model.Status;
import model.Subtask;
import model.Task;

public class Main {

    public static void main(String[] args) {

        TaskManager taskManager = Managers.getDefault(Managers.getDefaultHistory());

        int id = taskManager.hashCode();
        Task task1 = new Task("Задача 1", "Описание 1", ++id, Status.NEW);
        taskManager.newTask(task1);
        Task task2 = new Task("Задача 2", "Описание 2", ++id, Status.NEW);
        taskManager.newTask(task2);

        Epic epic = new Epic("Эпик ", "Описание ", ++id, Status.NEW);
        taskManager.newEpic(epic);
        Subtask subTask1 = new Subtask("Подзадача 1", "Описание 1", ++id, Status.NEW, epic);
        taskManager.newSubtask(subTask1);
        Subtask subTask2 = new Subtask("Подзадача 2", "Описание 2", ++id, Status.NEW, epic);
        taskManager.newSubtask(subTask2);

        Epic epic2 = new Epic("Эпик ", "Описание ", ++id, Status.NEW);
        taskManager.newEpic(epic2);
        Subtask subtask3 = new Subtask("Подзадача 1", "Описание 1", ++id, Status.NEW, epic2);
        taskManager.newSubtask(subtask3);

        taskManager.getTask(task1.getId());
        taskManager.getEpic(epic.getId());
        taskManager.getSubtask(subTask1.getId());
        taskManager.getTask(task2.getId());
        taskManager.getEpic(epic2.getId());
        taskManager.getSubtask(subTask2.getId());
        taskManager.getSubtask(subtask3.getId());
        for (Task tmp : taskManager.history()) {
            System.out.println(tmp);
        }
    }
}