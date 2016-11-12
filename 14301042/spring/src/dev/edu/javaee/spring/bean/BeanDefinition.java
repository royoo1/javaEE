package dev.edu.javaee.spring.bean;
/**
四个属性 ：
1. Object 对象叫 bean豆子 
2. class 对象 beanClass 豆子类对象
3.String 对象 beanClassName 豆子类名字
4.  PropertyValues 属性集 对象
作用：他的对象用来存储 特定bean的相关信息 并且有对应属性的get set  方法

**/
public class BeanDefinition {
	private Object bean;
	
	private Class<?> beanClass;
	
	private String beanClassName;
	
	private PropertyValues propertyValues;
	

	public PropertyValues getPropertyValues() {
		return propertyValues;
	}

	public void setPropertyValues(PropertyValues propertyValues) {
		this.propertyValues = propertyValues;
	}

	public Class<?> getBeanClass() {
		return beanClass;
	}

	public void setBeanClass(Class<?> beanClass) {
		this.beanClass = beanClass;
	}

	public String getBeanClassName() {
		return beanClassName;
	}

	public void setBeanClassName(String beanClassName) {
		this.beanClassName = beanClassName;
	}

	public Object getBean() {
		return bean;
	}

	public void setBean(Object bean) {
		this.bean = bean;
	}
	
}
