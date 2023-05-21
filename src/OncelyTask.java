import exception.IncorrectArgumentException;

import java.time.LocalDateTime;

public class OncelyTask extends Task {
    public OncelyTask(String title, String description, Type type, LocalDateTime date) throws IncorrectArgumentException {
        super(title, description, type, date);
    }

    @Override
    public boolean checkOccurrence(LocalDateTime requestedDate) {
        return getFirstDate().toLocalDate().equals(requestedDate.toLocalDate());
    }

}
