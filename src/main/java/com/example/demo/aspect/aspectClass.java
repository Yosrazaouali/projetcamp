package com.example.demo.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@Aspect
public class aspectClass {
	@AfterReturning("execution(* com.example.demo.services.*.addReservationtransport(..))")
	public void logEntry(JoinPoint jp) {
		String nom =jp.getSignature().getName();
		log.info("bien execution de la méthode" +nom);
	}
}
