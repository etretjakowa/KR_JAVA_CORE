import exception.IncorrectArgumentException;

import java.time.LocalDateTime;

public class WeeklyTask extends Task {
    public WeeklyTask(String title, String description, TaskType taskType, LocalDateTime date) throws IncorrectArgumentException {
        super(title, description, taskType, date);
    }

    @Override
    public boolean checkOccurrence(LocalDateTime requestedDate) {
        return getFirstDate().getDayOfWeek().equals(requestedDate.getDayOfWeek());
    }

}
