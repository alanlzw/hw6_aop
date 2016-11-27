package Maintest;

import dev.edu.javaee.spring.factory.BeanFactory;
import dev.edu.javaee.spring.factory.XMLBeanFactory;
import dev.edu.javaee.spring.resource.LocalFileResource;

public class AopTestMain {
	public static void main(String[] args) {
		    LocalFileResource resource = new LocalFileResource("aop.xml");
			BeanFactory beanFactory = new XMLBeanFactory(resource);
			ProxyFactoryBean foo = (ProxyFactoryBean)beanFactory.getBean("foo");
			
			FooInterface fooInterface=(FooInterface)foo.getObject();
			fooInterface.dummyFoo();
			fooInterface.printFoo();
	        
	}
}
