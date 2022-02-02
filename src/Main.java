import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {

        Manager manager = new Manager();
        ArrayList<Subtask> subtaskList = new ArrayList<>();
        int id = manager.hashCode();

        manager.newTask(new Task("Задача ", "Описание ", ++id));
        manager.newTask(new Task("Задача ", "Описание ", ++id));

        subtaskList.add(manager.newSubtask(new Subtask("Подзадача ", "Описание ", ++id)));
        subtaskList.add(manager.newSubtask(new Subtask("Подзадача ", "Описание ", ++id)));
        manager.newEpic(new Epic("Эпик ", "Описание ", ++id, subtaskList));

        subtaskList.clear();
        subtaskList.add(manager.newSubtask(new Subtask("Подзадача ", "Описание ", ++id)));
        manager.newEpic(new Epic("Эпик ", "Описание ", ++id, subtaskList));

        for (Epic epic : manager.getAllEpics()) {
            ArrayList<Subtask> list = epic.getSubtasks();
            System.out.println(list.size());
        }

        for (Epic epic : manager.getAllEpics()) {
            System.out.println(epic);
        }

        for (Task task : manager.getAllTasks()) {
            System.out.println(task);
        }

        for (Subtask subtask : manager.getAllSubtasks()) {
            System.out.println(subtask);
        }

        System.out.println("\nМеняем статус задач");
        for (Task task : manager.getAllTasks()) {
            manager.setTaskStatus(task, Status.IN_PROGRESS);
        }

        System.out.println("\nНовый список задач с новым статусом");
        for (Task task : manager.getAllTasks()) {
            System.out.println(task);
        }

        System.out.println("\nНовый список подзадач и эпиков с новым статусом");
        for (Epic epic : manager.getAllEpics()) {
            System.out.println(epic);
        }

        System.out.println("\nМеняем статус подзадач");
        for (Subtask subtask : manager.getAllSubtasks()) {
            manager.setSubtaskStatus(subtask, Status.DONE);
        }

        for (Subtask subtask : manager.getAllSubtasks()) {
            System.out.println(subtask);
        }

        System.out.println("\nУдаляем задачу");
        for (Task task : manager.getAllTasks()) {
            System.out.println(task);
            manager.removeTask(task.id);
            break;
        }

        System.out.println("\nУдалить эпик");
        for (Epic epic : manager.getAllEpics()) {
            System.out.println(epic);
            manager.removeEpic(epic.id);
            break;
        }

        System.out.println("\nНовый список задач, эпиков и подзадач");
        for (Epic epic : manager.getAllEpics()) {
            System.out.println(epic);
        }

        for (Task task : manager.getAllTasks()) {
            System.out.println(task);
        }

        for (Subtask subtask : manager.getAllSubtasks()) {
            System.out.println(subtask);
        }
    }
}