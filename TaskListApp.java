package gundu;

import java.util.ArrayList;
import java.util.Scanner;

public class TaskListApp {
    public static void main(String[] args) {
        TaskList taskList = new TaskList();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            displayMenu();
            int choice = getUserChoice(scanner);

            switch (choice) {
                case 1:
                    taskList.addTask(getTaskName(scanner));
                    break;
                case 2:
                    if (!taskList.isEmpty()) {
                        taskList.listTasks();
                        int taskNumber = getUserInput(scanner, "Enter the task number to remove: ");
                        if (taskList.isValidTaskNumber(taskNumber)) {
                            taskList.removeTask(taskNumber);
                        } else {
                            System.out.println("Invalid task number.");
                        }
                    } else {
                        System.out.println("No tasks to remove.");
                    }
                    break;
                case 3:
                    if (!taskList.isEmpty()) {
                        taskList.listTasks();
                    } else {
                        System.out.println("No tasks to list.");
                    }
                    break;
                case 4:
                    taskList.setDueDate(scanner);
                    break;
                case 5:
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }

    private static void displayMenu() {
        System.out.println("Task List Application");
        System.out.println("1. Add Task");
        System.out.println("2. Remove Task");
        System.out.println("3. List Tasks");
        System.out.println("4. Set Due Date for Task");
        System.out.println("5. Quit");
        System.out.print("Select an option: ");
    }

    private static int getUserChoice(Scanner scanner) {
        return scanner.nextInt();
    }

    private static String getTaskName(Scanner scanner) {
        System.out.print("Enter task name: ");
        return scanner.next();
    }

    private static int getUserInput(Scanner scanner, String prompt) {
        System.out.print(prompt);
        return scanner.nextInt();
    }
}

class TaskList {
    private ArrayList<Task> tasks = new ArrayList<>();

    public void addTask(String name) {
        Task task = new Task(name);
        tasks.add(task);
        System.out.println("Task added.");
    }

    public void removeTask(int taskNumber) {
        tasks.remove(taskNumber - 1);
        System.out.println("Task removed.");
    }

    public void listTasks() {
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println((i + 1) + ". " + tasks.get(i));
        }
    }

    public boolean isEmpty() {
        return tasks.isEmpty();
    }

    public boolean isValidTaskNumber(int taskNumber) {
        return taskNumber >= 1 && taskNumber <= tasks.size();
    }

    public void setDueDate(Scanner scanner) {
        if (!isEmpty()) {
            listTasks();
            int taskNumber = getUserInput(scanner, "Enter the task number to set due date: ");
            if (isValidTaskNumber(taskNumber)) {
                Task task = tasks.get(taskNumber - 1);
                task.setDueDate(getDueDate(scanner));
            } else {
                System.out.println("Invalid task number.");
            }
        } else {
            System.out.println("No tasks to set due date.");
        }
    }

    private int getUserInput(Scanner scanner, String string) {
		// TODO Auto-generated method stub
		return 0;
	}

	private String getDueDate(Scanner scanner) {
        System.out.print("Enter due date for the task (e.g., YYYY-MM-DD): ");
        return scanner.next();
    }
}

class Task {
    private String name;
    private String dueDate;

    public Task(String name) {
        this.name = name;
    }

    public void setDueDate(String dueDate) {
        this.dueDate = dueDate;
    }

    @Override
    public String toString() {
        return name + " (Due: " + dueDate + ")";
    }
}
