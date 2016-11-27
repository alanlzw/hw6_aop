package Maintest;

import dev.edu.javaee.spring.aop.framework.AdvisedSupport;
import dev.edu.javaee.spring.aop.framework.JdkDynamicAopProxy;

public class ProxyFactory extends AdvisedSupport{
	
	public Object getProxy() {
		return new JdkDynamicAopProxy(this).getProxy();
	}
}
