import exception.IncorrectArgumentException;

import java.time.LocalDateTime;

public class DailyTask extends Task{
    public DailyTask(String title, String description, Type type, LocalDateTime date) throws IncorrectArgumentException {
        super(title, description, type, date);

    }

    @Override
    public boolean checkOccurrence(LocalDateTime requestedDate) {
        return true;
    }
}
