package dev.edu.javaee.spring.factory;

import dev.edu.javaee.spring.bean.BeanDefinition;

//豆子工厂接口 两个
/**
两个方法：
1.Object getBean(String beanName) 得到对象
2.void registerBeanDefinition(String beanName, BeanDefinition beanDefinition); 放入map
**/
public interface BeanFactory {
	Object getBean(String beanName);
	void registerBeanDefinition(String beanName, BeanDefinition beanDefinition);
}
