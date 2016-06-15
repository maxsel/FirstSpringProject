package ua.epam.spring.core.aspects;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import ua.epam.spring.core.Event;
import ua.epam.spring.core.loggers.EventLogger;

/**
 * Created by Maksim_Sialiuk on 6/15/2016.
 */
@Aspect
public class LimitAspect {

    public static final int MAX_ALLOWED = 1;
    private int count;

    public void setOtherLogger(EventLogger otherLogger) {
        this.otherLogger = otherLogger;
    }

    private EventLogger otherLogger;

    @Pointcut("execution(* ua.epam.spring.core.loggers.ConsoleEventLogger.logEvent(..))")
    private void consoleLoggerMethods() {}

    @Around("consoleLoggerMethods() && args(evt)") // why not @Instead?
    public void aroundLogEvent(ProceedingJoinPoint jp, Object evt) {
        count++;
        if (count < MAX_ALLOWED) {
            try {
                jp.proceed(new Object[] {evt});
            } catch (Throwable throwable) {
                throwable.printStackTrace();
            }
        } else {
            otherLogger.logEvent((Event)evt);
        }
    }
}
