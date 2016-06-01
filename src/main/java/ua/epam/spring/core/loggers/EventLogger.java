package ua.epam.spring.core.loggers;

import ua.epam.spring.core.Event;

/**
 * Created by Maksim_Sialiuk on 6/1/2016.
 */
public interface EventLogger {
    void logEvent(Event event);
}
