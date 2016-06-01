package ua.epam.spring.core.loggers;

import ua.epam.spring.core.Event;

/**
 * Created by Maksim_Sialiuk on 5/31/2016.
 */
public class ConsoleEventLogger implements EventLogger {
    public void logEvent(Event event) {
        System.out.println(event.toString());
    }
}
