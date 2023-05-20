import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            while (true) {
                System.out.println("Выберите пункт меню:");
                printMenu();
                if (scanner.hasNextInt()) {
                    int menu = scanner.nextInt();
                    switch (menu) {
                        case 1:
                            TaskService.addTask(scanner);
                            break;
                        case 2:
                            TaskService.editTask(scanner);
                            break;
                        case 3:
                            TaskService.deleteTask(scanner);
                            break;
                        case 4:
                            TaskService.getTasksByDay(scanner);
                            break;
                    }
                } else {
                    scanner.next();
                    System.out.println("Выберите пункт меню из списка: ");
                }
            }
        }
    }

    private static void printMenu() {
        System.out.println("""
                1. Добавить задачу
                2. Редактировать задачу
                3. Удалить задачу
                4. Получить задачи на заданный день
                5. Выход
                """
        );
    }
}