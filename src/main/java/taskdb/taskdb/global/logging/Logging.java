package taskdb.taskdb.global.logging;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

@Aspect
@Component
@Slf4j
public class Logging {
    @Pointcut("execution(* taskdb.taskdb.domain..presentation..*.*(..))")
    private void domainCut() {}

    private static final String STARTING_METHOD_MESSAGE = ">>>>>>>>>>starting method = {}";
    private static final String END_METHOD_MESSAGE = ">>>>>>>>>>end method = {}";
    private static final String TYPE_MESSAGE = "type = {}";
    private static final String NOT_RESPONSE_DATA_TYPE_MESSAGE = "type = void";
    private static final String VALUE_MESSAGE = "value = {}";

    @Before("domainCut()")
    public void domainBefore(JoinPoint joinPoint) {
        Method method = createMethodSignature(joinPoint);
        log.info(STARTING_METHOD_MESSAGE, method.getName());
        Object[] args = joinPoint.getArgs();
        printRequestLog(args);
    }

    @AfterReturning(value = "domainCut()", returning = "object")
    public void domainAfter(JoinPoint joinPoint, Object object) {
        Method method = createMethodSignature(joinPoint);
        log.info(END_METHOD_MESSAGE, method.getName());
        printResponseLog(object);
    }

    private void printRequestLog(Object[] args) {
        for(Object obj : args) {
            log.info(TYPE_MESSAGE, obj.getClass().getSimpleName());
            log.info(VALUE_MESSAGE, obj);
        }
    }

    private void printResponseLog(Object object) {
        if(object != null) {
            log.info(TYPE_MESSAGE, object.getClass().getSimpleName());
        }
        else {
            log.info(NOT_RESPONSE_DATA_TYPE_MESSAGE);
        }
    }

    private Method createMethodSignature(JoinPoint joinPoint) {
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        return methodSignature.getMethod();
    }
}
