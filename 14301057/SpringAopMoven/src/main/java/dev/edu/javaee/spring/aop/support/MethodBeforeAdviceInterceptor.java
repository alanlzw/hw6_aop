package dev.edu.javaee.spring.aop.support;

import java.lang.reflect.Method;

import dev.edu.javaee.spring.aop.MethodBeforeAdvice;
import dev.edu.javaee.spring.aop.MethodInterceptor;

public class MethodBeforeAdviceInterceptor implements MethodInterceptor {

	private MethodBeforeAdvice advice;
	
	public MethodBeforeAdviceInterceptor(MethodBeforeAdvice advice)
	{
		this.advice = advice;
	}
	
	@Override
	public Object invoke(Object target, Method method, Object[] args) throws Throwable {
		advice.before(method, args, target);
		return method.invoke(target, args);
	}

}
