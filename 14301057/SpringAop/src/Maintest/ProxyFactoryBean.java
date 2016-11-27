package Maintest;

import dev.edu.javaee.spring.aop.*;
import dev.edu.javaee.spring.aop.framework.JdkDynamicAopProxy;
import dev.edu.javaee.spring.aop.support.JdkRegexpMethodPointcutAdvisor;
import dev.edu.javaee.spring.factory.BeanFactory;
import dev.edu.javaee.spring.factory.XMLBeanFactory;
import dev.edu.javaee.spring.resource.LocalFileResource;

public class ProxyFactoryBean implements FactoryBean{
	//引用自
http://wangxinchun.iteye.com/blog/2079585	//代理接口
	private String proxyInterfaces;
	//缓存所用
	private Object target;
	//代理对象名称
	private String interceptorNames;
	//标志位
	public boolean flag=false;
	public String getProxyInterfaces() {
		return proxyInterfaces;
	}
	public void setProxyInterfaces(String proxyInterfaces) {
		this.proxyInterfaces = proxyInterfaces;
	}
	public Object getTarget() {
		return target;
	}
	public void setTarget(Object target) {
		this.target = target;
	}
	public String getInterceptorNames() {
		return interceptorNames;
	}
	public void setInterceptorNames(String interceptorNames) {
		this.interceptorNames = interceptorNames;
	}
	
	@Override
	//对象获取的输入
	public Object getObject() {
		LocalFileResource resource = new LocalFileResource("aop.xml");
		BeanFactory beanFactory = new XMLBeanFactory(resource);
		
		ProxyFactory proxyFactory = new ProxyFactory();
		proxyFactory.setTarget(target);
		try {
			proxyFactory.setInterfaces( Class.forName(proxyInterfaces));
		} catch (ClassNotFoundException e) {
			
			e.printStackTrace();  
		}
		JdkRegexpMethodPointcutAdvisor advisor = new JdkRegexpMethodPointcutAdvisor();
		advisor.setAdvice((Advice)beanFactory.getBean(interceptorNames));
		advisor.setPattern("\\w+");
		proxyFactory.setAdvisor(advisor);
		
		
		return proxyFactory.getProxy();
	}
	
	@SuppressWarnings("unchecked")
	public Class<Object> getObjectType()
	{
		return (Class<Object>)this.getTarget().getClass();
	}
	public boolean isSingleton() {
		return false;
	}
	
	
}
