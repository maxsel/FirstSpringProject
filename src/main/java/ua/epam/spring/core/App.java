package ua.epam.spring.core;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ua.epam.spring.core.beans.Client;
import ua.epam.spring.core.loggers.EventLogger;

import java.util.Map;

/**
 * Created by Maksim_Sialiuk on 5/31/2016.
 */
public class App {

    private Client client;

    private EventLogger defaultLogger;

    private Map<EventType, EventLogger> loggers;

    public App(Client client,
               EventLogger defaultLogger,
               Map<EventType, EventLogger> loggers) {
        this.client = client;
        this.defaultLogger = defaultLogger;
        this.loggers = loggers;
    }

    public void logEvent(Event event, EventType type) {
        EventLogger logger = loggers.get(type);
        if (logger == null) {
            logger = defaultLogger;
        }
        System.out.println(client.getGreeting());
        logger.logEvent(event);
    }

    public static void main(String[] args) {
        AnnotationConfigApplicationContext ctx =
                new AnnotationConfigApplicationContext(AppConfig.class);

        App app = (App) ctx.getBean("app");
        Event event1 = (Event) ctx.getBean("event");
        event1.setMsg("One");
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Event event2 = (Event) ctx.getBean("event");
        event2.setMsg("Two");

        app.logEvent(event1, EventType.INFO);
        app.logEvent(event2, EventType.ERROR);
    }
}
