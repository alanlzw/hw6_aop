package Maintest;

import java.lang.reflect.Method;

import dev.edu.javaee.spring.aop.AfterReturningAdvice;
import dev.edu.javaee.spring.aop.MethodBeforeAdvice;

public class PrintBeforeAdvice implements MethodBeforeAdvice{

	@Override
	public void before(Method method, Object[] args, Object target) {
	}

}
