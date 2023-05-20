import exception.IncorrectArgumentException;

import java.time.LocalDateTime;

public class YearlyTask extends Task {
    public YearlyTask(String title, String description, TaskType taskType, LocalDateTime date) throws IncorrectArgumentException {
        super(title, description, taskType, date);
    }

    @Override
    public boolean checkOccurrence(LocalDateTime requestedDate) {
        return (getFirstDate().getDayOfMonth() == requestedDate.getDayOfMonth() && getFirstDate().getMonth()== requestedDate.getMonth());
    }

}
