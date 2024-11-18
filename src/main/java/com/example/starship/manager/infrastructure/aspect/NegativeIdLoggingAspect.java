package com.example.starship.manager.infrastructure.aspect;

import com.example.starship.manager.domain.exception.ResourceNotFoundException;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class NegativeIdLoggingAspect {

    private static final Logger logger = LoggerFactory.getLogger(NegativeIdLoggingAspect.class);

    @Around("execution(* com.example.starship.manager.presentation.controller.*.*(..)) && args(id, ..)")
    public Object logIfIdIsNegative(ProceedingJoinPoint joinPoint, Long id) throws Throwable {
        if (id != null && id < 0) {
            logger.warn("Negative ID detected: {}", id); // Log antes de la ejecución
        }
        try {
            return joinPoint.proceed();
        } catch (ResourceNotFoundException e) {
            throw e;
        }
    }
}
