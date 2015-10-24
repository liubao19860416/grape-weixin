package com.saike.grape.postbean;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

/**
 * 这里起到一个拦截的作用
 * @author Liubao
 * @2015年6月18日
 *
 */
@Component
public class MyBeanPostProcessor implements BeanPostProcessor {

	// 使用spring的BeanPostProcessor 后处理器(钩子函数),进行相应的操作(AOP动态代理的核心位置)
	/**
	 * 初始化之后执行的函数 自动代理只能针对接口实现
	 */
	public Object postProcessAfterInitialization(final Object bean,
			String beanName) throws BeansException {
		if (beanName.equals("product2")) {
			System.out.println("BeanPostProcessor函数执行后执行的代码!" + beanName);
			return Proxy.newProxyInstance(bean.getClass().getClassLoader(),
					bean.getClass().getInterfaces(), new MyInvocationHandler(
							bean, beanName));
			// return Proxy.newProxyInstance(bean.getClass().getClassLoader(),
			// bean.getClass().getInterfaces(), new InvocationHandler() {
			// @Override
			// public Object invoke(Object proxy, Method method, Object[] args)
			// throws Throwable {
			// System.out.println("执行代理了");
			// return method.invoke(bean, args);
			// }
			// });
		} else{
		    System.out.println("BeanPostProcessor函数执行后执行的代码!" );
		}
			 return bean;
	}

	/**
	 * 初始化之前执行的函数
	 */
	public Object postProcessBeforeInitialization(Object arg0, String arg1)
			throws BeansException {
		if (arg1.equals("product2")) {
			// if (arg1.equals("product1")) {
			System.out.println("BeanPostProcessor函数执行前执行的代码!" + arg1);
			return arg0;
		}else{
		    System.out.println("BeanPostProcessor函数执行前执行的代码!" );
		}
		return arg0;
	}

	// 自定义的内部处理类(实现了MyInvocationHandler接口,这样在上面就不红使用匿名内部类了)
	// 这里的异常信息一定要抛出去!!!
	private class MyInvocationHandler implements InvocationHandler {
		// 注入需要的被代理对象信息
		private Object bean;
		private String beanName;

		public MyInvocationHandler(Object bean, String beanName) {
			super();
			this.bean = bean;
			this.beanName = beanName;
		}

		public Object invoke(Object proxy, Method method, Object[] args)
				throws Throwable {
			System.out.println("执行了代理过程proxy" + proxy.getClass().toString());
			return method.invoke(bean, args);
		}

	}

}
