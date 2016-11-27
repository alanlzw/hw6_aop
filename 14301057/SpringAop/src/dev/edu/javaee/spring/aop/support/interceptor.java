package dev.edu.javaee.spring.aop.support;

import java.lang.reflect.Method;

import dev.edu.javaee.spring.aop.MethodInterceptor;
import dev.edu.javaee.spring.aop.ThrowsAdvice;

public class interceptor implements MethodInterceptor {

	private ThrowsAdvice advice;
	
	public interceptor(ThrowsAdvice advice)
	{
		this.advice = advice;
	}
	
	@Override
	public Object invoke(Object target, Method method, Object[] args) throws Throwable {
		try {
			Object returnValue = method.invoke(target, args);
			return returnValue;
		}
		catch (Throwable ex) {
			//We didn`t define our exceptionHandler for special exceptionClass, just simply throw the exception.
			throw ex;
		}
	}

}
