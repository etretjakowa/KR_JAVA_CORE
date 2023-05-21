import exception.IncorrectArgumentException;
import exception.TaskNotFoundExseption;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.*;

public class TaskService {
    private static final Map<Integer, Task> actualTask = new HashMap<>();

    public static void addTask(Scanner scanner) {
        try {
            scanner.nextLine();
            System.out.println("Имя задачи: ");
            String title = Task.checkMessage(scanner.nextLine());
            System.out.println("описание задачи: ");
            String description = Task.checkMessage(scanner.nextLine());
            System.out.println("Тип задачи: 0-рабочая 1- личная ");
            Type type = Type.values()[scanner.nextInt()];
            System.out.println("Повторяемость задачи: 0 - Однократная, 1 - Ежедневная, 2 - Ежемесячная, 4 - Ежегодная");
            int occurrence = scanner.nextInt();
            System.out.println("ВВедите дату: dd.MM.yyyy HH:mm");
            scanner.nextLine();
            createEvent(scanner, title, description, type, occurrence);
            System.out.println("Для выхода нажмите Enter");
            scanner.nextLine();
        } catch (IncorrectArgumentException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void createEvent(Scanner scanner, String title, String description, Type type, int occurrence) throws IncorrectArgumentException {
        try {
            LocalDateTime eventDate = LocalDateTime.parse(scanner.nextLine(), DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm"));
            Task task;
            task = createTask(occurrence, title, description, type, eventDate);
            System.out.println("создана задача " + task);
        } catch (DateTimeParseException e) {
            System.out.println("Некорректнаый формат даты, введите заново ");
            createEvent(scanner, title, description, type, occurrence);
        }
    }

    public static void editTask(Scanner scanner) {
        try {
            System.out.println("Редиктирование задачи: введите id");
            printActualTask();
            int id = scanner.nextInt();
            if (!actualTask.containsKey(id)) {
                throw new TaskNotFoundExseption("Задача не найдена");
            }
            System.out.println("Редактирование 0-заголовок 1-описание 2-тип 3-дата");
            int menuCase = scanner.nextInt();
            switch (menuCase) {
                case 0 -> {
                    scanner.nextLine();
                    System.out.println("Название задачи: ");
                    String title = scanner.nextLine();
                    Task task = actualTask.get(id);
                    task.setTitle(title);
                }
                case 1 -> {
                    scanner.nextLine();
                    System.out.println("Описание задачи: ");
                    String description = scanner.nextLine();
                    Task task = actualTask.get(id);
                    task.setDescription(description);
                }
                case 2 -> {
                    scanner.nextInt();
                    System.out.println("Тип задачи: ");
                    int type = scanner.nextInt();
                    Task task = actualTask.get(id);
                    task.setType(Type.values()[type]);
                }
                case 3 -> {
                    scanner.nextLine();
                    System.out.println("Введмте новую дату в формате dd.MM.yyyy HH:mm: ");
                    String firstDate = scanner.nextLine();
                    Task task = actualTask.get(id);
                    LocalDateTime newDateTime = LocalDateTime.parse(firstDate, DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm"));
                    task.setFirstDate(newDateTime);
                }
            }
        } catch (TaskNotFoundExseption e) {
            System.out.println(e.getMessage());
        }

    }

    public static void deleteTask(Scanner scanner) {
        System.out.println("Удалить задачу - введите id");
        int id = scanner.nextInt();
        if (!actualTask.containsKey(id)) {
            try {
                throw new TaskNotFoundExseption("Задача не найдена");
            } catch (TaskNotFoundExseption e) {
                throw new RuntimeException(e);
            }
        }
        actualTask.remove(id);
    }

    public static void getTasksByDay(Scanner scanner) {
        System.out.println("Введите дату dd.MM.yyyy:");
        try {
            String date = scanner.next();
            DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
            LocalDate requestedDate = LocalDate.parse(date, dateFormatter);
            List<Task> foundEvents = findTaskByDate(requestedDate);
            System.out.println("События на " + requestedDate + ": ");
            for (Task task : foundEvents) {
                System.out.println(task);
            }
        } catch (DateTimeParseException e) {
            System.out.println("Некорректнаый формат даты, введите заново ");
        }
        scanner.nextLine();
        System.out.println("Для выхода нажмите Enter");
    }

    private static List<Task> findTaskByDate(LocalDate date) {
        List<Task> tasks = new ArrayList<>();
        for (Task task : actualTask.values()) {
            if (task.checkOccurrence(date.atStartOfDay())) {
                tasks.add(task);
            }
        }
        return tasks;
    }

    public static Task createTask(int occurrence, String title, String description, Type type, LocalDateTime localDateTime) throws IncorrectArgumentException {
        return switch (occurrence) {
            case 0 -> {
                OncelyTask oncelyTask = new OncelyTask(title, description, type, localDateTime);
                actualTask.put(oncelyTask.getId(), oncelyTask);
                yield oncelyTask;
            }
            case 1 -> {
                DailyTask task = new DailyTask(title, description, type, localDateTime);
                actualTask.put(task.getId(), task);
                yield task;
            }
            case 2 -> {
                WeeklyTask task = new WeeklyTask(title, description, type, localDateTime);
                actualTask.put(task.getId(), task);
                yield task;
            }
            case 3 -> {
                MonthlyTask task = new MonthlyTask(title, description, type, localDateTime);
                actualTask.put(task.getId(), task);
                yield task;
            }
            case 4 -> {
                YearlyTask task = new YearlyTask(title, description, type, localDateTime);
                actualTask.put(task.getId(), task);
                yield task;
            }
            default -> null;
        };
    }

    private static void printActualTask() {
        for (Task task : actualTask.values()) {
            System.out.println(task);
        }
    }
}