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

<<<<<<< HEAD
<<<<<<< HEAD
=======
>>>>>>> 58c33ab... after ABC
    @Bean
    public ConsoleEventLogger consoleEventLogger() {
        return new ConsoleEventLogger();
    }
<<<<<<< HEAD
=======
    @Autowired
    public ConsoleEventLogger consoleEventLogger;
>>>>>>> 7333fda... not understand @Component and @Bean
=======
>>>>>>> 58c33ab... after ABC

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
<<<<<<< HEAD
<<<<<<< HEAD
        return new CombinedEventLogger(Arrays.asList(consoleEventLogger(), cacheFileEventLogger()));
=======
        return new CombinedEventLogger(Arrays.asList(consoleEventLogger, cacheFileEventLogger()));
>>>>>>> 7333fda... not understand @Component and @Bean
=======
        return new CombinedEventLogger(Arrays.asList(consoleEventLogger(), cacheFileEventLogger()));
>>>>>>> 58c33ab... after ABC
    }
}
