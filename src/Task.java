import exception.IncorrectArgumentException;

import java.time.LocalDateTime;
import java.util.Objects;

public abstract class Task {
    private String title;
    private String description;
    private Type type;
    private LocalDateTime firstDate;
    private static Integer counter = 1;
    private final Integer id;

    public Task(String title, String description, Type type, LocalDateTime localDateTime) throws IncorrectArgumentException {
        this.title = checkMessage(title);
        this.description = checkMessage(description);
        this.type = type;
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

    public void setFirstDate(LocalDateTime firstDate) {
        this.firstDate = firstDate;
    }

    public void setType(Type type) {
        this.type = type;
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
        return Objects.equals(title, task.title) && Objects.equals(description, task.description) && type == task.type && Objects.equals(firstDate, task.firstDate) && Objects.equals(id, task.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, description, type, firstDate, id);
    }

    @Override
    public String toString() {
        return  "Название задачи: " + title +
                ", описание: " + description +
                ", тип: " + type +
                ", дата: " + firstDate +
                ", id: " + id;
    }
}
