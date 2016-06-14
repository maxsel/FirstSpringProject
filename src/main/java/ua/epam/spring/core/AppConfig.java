package ua.epam.spring.core;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.*;
import org.springframework.core.env.Environment;
import ua.epam.spring.core.beans.Client;
import ua.epam.spring.core.loggers.CombinedEventLogger;
import ua.epam.spring.core.loggers.ConsoleEventLogger;
import ua.epam.spring.core.loggers.EventLogger;
import ua.epam.spring.core.loggers.LoggersConfig;

import java.text.DateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Maksim_Sialiuk on 6/13/2016.
 */
@Configuration
@Import(LoggersConfig.class)
@PropertySource("client.properties")
public class AppConfig {

    @Autowired
    private Environment environment;

    @Bean
    public Client client() {
        Client client = new Client(environment.getProperty("id"), environment.getProperty("name"));
        client.setGreeting(environment.getProperty("greeting"));
        return client;
    }

    @Autowired
    private CombinedEventLogger combinedLogger;

    @Autowired
    private ConsoleEventLogger consoleLogger;

    private Map loggerMap() {
        HashMap<EventType, EventLogger> map = new HashMap<EventType, EventLogger>();
        map.put(EventType.INFO, consoleLogger);
        map.put(EventType.ERROR, combinedLogger);
        return map;
    }

    @Bean
    public App app() {
        return new App(client(), consoleLogger, loggerMap());
    }

    @Bean
    @Scope("prototype")
    public Event event() {
        return new Event(new Date(), dateFormat());
    }

    @Bean
    public DateFormat dateFormat() {
        return DateFormat.getDateTimeInstance();
    }
}
