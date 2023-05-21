import exception.IncorrectArgumentException;

import java.time.LocalDateTime;

public class YearlyTask extends Task {
    public YearlyTask(String title, String description, Type type, LocalDateTime date) throws IncorrectArgumentException {
        super(title, description, type, date);
    }

    @Override
    public boolean checkOccurrence(LocalDateTime requestedDate) {
        return (getFirstDate().getDayOfMonth() == requestedDate.getDayOfMonth() && getFirstDate().getMonth()== requestedDate.getMonth());
    }

}
