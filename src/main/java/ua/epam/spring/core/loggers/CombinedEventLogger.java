package ua.epam.spring.core.loggers;

import ua.epam.spring.core.Event;

import java.util.Collection;

/**
 * Created by Maksim_Sialiuk on 6/3/2016.
 */
public class CombinedEventLogger implements EventLogger {
    private Collection<EventLogger> loggers;

    public CombinedEventLogger(Collection<EventLogger> loggers) {
        this.loggers = loggers;
    }

    public void logEvent(Event event) {
        for (EventLogger logger : loggers) {
            logger.logEvent(event);
        }
    }
}
