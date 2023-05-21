import exception.IncorrectArgumentException;

import java.time.LocalDateTime;

public class MonthlyTask extends Task {

    public MonthlyTask(String title, String description, Type type, LocalDateTime date) throws IncorrectArgumentException {
        super(title, description, type, date);
    }

    @Override
    public boolean checkOccurrence(LocalDateTime requestedDate) {
        return getFirstDate().getDayOfMonth() == (requestedDate.getDayOfMonth());
    }


}
