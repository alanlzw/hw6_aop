package dev.edu.javaee.spring.aop.support;

import java.lang.reflect.Method;

import dev.edu.javaee.spring.aop.AfterReturningAdvice;
import dev.edu.javaee.spring.aop.MethodInterceptor;

public class methodInterceptorImp implements MethodInterceptor {

	private AfterReturningAdvice advice;
	
	public methodInterceptorImp(AfterReturningAdvice advice)
	{
		this.advice = advice;
	}
	
	@Override
	public Object invoke(Object target, Method method, Object[] args) throws Throwable {
		Object returnValue = method.invoke(target, args);
		advice.afterReturning(returnValue, method, args, target);
		return returnValue;
	}

}
