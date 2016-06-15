package ua.epam.spring.core.aspects;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

import java.util.HashMap;

/**
 * Created by Maksim_Sialiuk on 6/15/2016.
 */
@Aspect
public class StatisticsAspect {

    private HashMap<String, Integer> count = new HashMap<String, Integer>();

    @Pointcut("execution(* ua.epam.spring.core.loggers.EventLogger+.logEvent(..))")
    private void allLogEventMethods() {}

    @AfterReturning(pointcut = "allLogEventMethods()")
    public void logAfter(JoinPoint point) {
        String clazz = point.getTarget().getClass().getSimpleName();
        if (!count.containsKey(clazz)) {
            count.put(clazz, 0);
        }
        count.put(clazz, count.get(clazz)+1);
    }

    public void destroy() {
        System.out.println("Count " + count);
    }
}
