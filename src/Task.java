import exception.IncorrectArgumentException;

import java.time.LocalDateTime;
import java.util.Objects;

public abstract class Task {
    public static TaskType TaskType;
    private String title;
    private String description;
    private TaskType taskType;
    private LocalDateTime firstDate;
    private static Integer counter = 1;
    private final Integer id;

    public Task(String title, String description, TaskType taskType, LocalDateTime localDateTime) throws IncorrectArgumentException {
        this.title = checkMessage(title);
        this.description = checkMessage(description);
        this.taskType = taskType;
        this.firstDate = localDateTime;
        id = counter++;

    }

    public LocalDateTime getFirstDate() {
        return firstDate;
    }

    public Integer getId() {
        return id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setTaskType(TaskType taskType) {
        this.taskType = taskType;
    }

    public void setFirstDate(LocalDateTime firstDate) {
        this.firstDate = firstDate;
    }

    public abstract boolean checkOccurrence(LocalDateTime localDateTime);

    public static String checkMessage(String s) throws IncorrectArgumentException {
        if (s == null || s.isEmpty() || s.isBlank()) {
            throw new IncorrectArgumentException("Некорректное значение");
        } else {
            return s;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Task task = (Task) o;
        return Objects.equals(title, task.title) && Objects.equals(description, task.description) && Objects.equals(taskType, task.taskType) && Objects.equals(firstDate, task.firstDate) && Objects.equals(id, task.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, description, taskType, firstDate, id);
    }

    @Override
    public String toString() {
        return "Task{" +
                "title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", taskType=" + taskType +
                ", firstDate=" + firstDate +
                ", id=" + id +
                '}';
    }

    public enum TaskType {
        WORK(0),
        PERSONAL(1);
        public final int value;

        TaskType(final int value) {
            this.value = value;
        }
    }

}
