package ua.epam.spring.core;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ua.epam.spring.core.beans.Client;
import ua.epam.spring.core.loggers.EventLogger;

import java.util.Date;

/**
 * Created by Maksim_Sialiuk on 5/31/2016.
 */
public class App {
    private Client client;
    private EventLogger eventLogger;

    public App(Client client, EventLogger eventLogger) {
        this.client = client;
        this.eventLogger = eventLogger;
    }

    public void logEvent(Event event) {
        //String message = msg.replaceAll(
        //        client.getId(), client.getFullName());
        eventLogger.logEvent(event);
    }

    public static void main(String[] args) {
        ApplicationContext ctx =
                new ClassPathXmlApplicationContext(
                        "spring.xml");

        App app = (App) ctx.getBean("app");
        Event event1 = (Event) ctx.getBean("event");
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Event event2 = (Event) ctx.getBean("event");

        app.logEvent(event1);
        app.logEvent(event2);
    }
}
