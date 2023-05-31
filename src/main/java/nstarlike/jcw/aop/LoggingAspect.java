package nstarlike.jcw.aop;

import java.lang.reflect.Parameter;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LoggingAspect {
	private static final Logger logger = LoggerFactory.getLogger(LoggingAspect.class);
	
	public void logBefore(JoinPoint joinPoint) {
		MethodSignature signature = (MethodSignature)joinPoint.getSignature();
		
		logger.debug("start " + joinPoint.getTarget().getClass().getSimpleName() + "." + signature.getName());
		
		Parameter[] names = signature.getMethod().getParameters();
		Object[] args = joinPoint.getArgs();
		
		int size = names.length;
		for(int i=0; i<size; i++) {
			logger.debug(names[i] + " => " + args[i]);
		}
	}
}
