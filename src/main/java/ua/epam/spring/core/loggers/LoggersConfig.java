package ua.epam.spring.core.loggers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;

/**
 * Created by Maksim_Sialiuk on 6/13/2016.
 */
@Configuration
public class LoggersConfig {

    @Autowired
    public ConsoleEventLogger consoleEventLogger;

    @Bean(initMethod = "init")
    public FileEventLogger fileEventLogger() {
        return new FileEventLogger("log.txt");
    }

    @Bean(initMethod = "init", destroyMethod = "destroy")
    public CacheFileEventLogger cacheFileEventLogger() {
        return new CacheFileEventLogger("log.txt", 1);
    }

    @Bean
    public CombinedEventLogger combinedEventLogger() {
        return new CombinedEventLogger(Arrays.asList(consoleEventLogger, cacheFileEventLogger()));
    }
}
