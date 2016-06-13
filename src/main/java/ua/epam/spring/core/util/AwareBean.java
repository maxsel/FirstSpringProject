package ua.epam.spring.core.util;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * Created by Maksim_Sialiuk on 6/13/2016.
 */
public class AwareBean implements ApplicationContextAware {
    public void setApplicationContext(ApplicationContext ctx) throws BeansException {
        // ...
    }

    public void init() {
        // ...
    }
}
