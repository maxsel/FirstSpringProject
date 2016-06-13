package ua.epam.spring.core.loggers;

import org.springframework.stereotype.Component;
import ua.epam.spring.core.Event;

/**
 * Created by Maksim_Sialiuk on 5/31/2016.
 */
@Component
public class ConsoleEventLogger implements EventLogger {
    public void logEvent(Event event) {
        System.out.println(event.toString());
    }
}
