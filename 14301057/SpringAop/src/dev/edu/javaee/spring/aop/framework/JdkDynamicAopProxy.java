package dev.edu.javaee.spring.aop.framework;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

import dev.edu.javaee.spring.aop.AfterReturningAdvice;
import dev.edu.javaee.spring.aop.AopProxy;
import dev.edu.javaee.spring.aop.MethodBeforeAdvice;
import dev.edu.javaee.spring.aop.MethodInterceptor;
import dev.edu.javaee.spring.aop.ThrowsAdvice;
import dev.edu.javaee.spring.aop.support.methodInterceptorImp;
import dev.edu.javaee.spring.aop.support.MethodBeforeAdviceInterceptor;
import dev.edu.javaee.spring.aop.support.PointcutAdvisor;
import dev.edu.javaee.spring.aop.support.interceptor;

public class JdkDynamicAopProxy implements AopProxy, InvocationHandler{

	private AdvisedSupport advised;
	
	public JdkDynamicAopProxy(AdvisedSupport advised)
	{
		this.advised = advised;
	}
	
	public Object getProxy()
	{
		return Proxy.newProxyInstance(
				this.getClass().getClassLoader(),
				new Class[]{advised.getInterfaces()}, 
				this);
	}

	@Override
	public Object invoke(Object proxy, Method method, Object[] args)
			throws Throwable {
		
		PointcutAdvisor pointcutAdvisor = (PointcutAdvisor) advised.getAdvisor();
		
		if(pointcutAdvisor.getPointcut().getMethodMatcher().matches(method, advised.getTargetSource().getTarget().getClass()))
		{
			
			if(advised.getAdvisor().getAdvice() instanceof MethodBeforeAdvice)
			{
				MethodBeforeAdvice advice = (MethodBeforeAdvice)advised.getAdvisor().getAdvice();
				MethodInterceptor interceptor = new MethodBeforeAdviceInterceptor(advice);
				return interceptor.invoke(advised.getTargetSource().getTarget(), method, args);
			}
			if(advised.getAdvisor().getAdvice() instanceof AfterReturningAdvice)
			{
				AfterReturningAdvice advice = (AfterReturningAdvice)advised.getAdvisor().getAdvice();
				MethodInterceptor interceptor = new methodInterceptorImp(advice);
				return interceptor.invoke(advised.getTargetSource().getTarget(), method, args);
			}
			if(advised.getAdvisor().getAdvice() instanceof ThrowsAdvice)
			{
				ThrowsAdvice advice = (ThrowsAdvice)advised.getAdvisor().getAdvice();
				MethodInterceptor interceptor = new interceptor(advice);
				return interceptor.invoke(advised.getTargetSource().getTarget(), method, args);
			}
		}
			
		return method.invoke(advised.getTargetSource().getTarget(), args);
	}

}
