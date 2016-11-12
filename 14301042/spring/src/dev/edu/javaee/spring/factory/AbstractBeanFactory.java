package dev.edu.javaee.spring.factory;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import dev.edu.javaee.spring.bean.BeanDefinition;
//抽象类 豆子工厂 实现了豆子工厂
/**
类名：AbstractBeanFactory 抽象豆子工厂 实现了  BeanFactory
一个属性：
 Map<String, BeanDefinition> beanDefinitionMap 图 String对象和BeanDefinition对象
 三个方法
 1.Object getBean(String beanName)获得豆子 
 2. registerBeanDefinition(String beanName, BeanDefinition beanDefinition) 豆子名和豆子定义对象 放到map中
 3.抽象abstract BeanDefinition GetCreatedBean(BeanDefinition beanDefinition); 改造BeanDefinition
**/
public abstract class AbstractBeanFactory implements BeanFactory{
	//定义图map 第一个字段String 第二个字段对象 BeanDefinition 名字beanDefinitionMap
	private Map<String, BeanDefinition> beanDefinitionMap = new ConcurrentHashMap<String, BeanDefinition>();
	//返回特定豆子定义
	public Object getBean(String beanName)
	{
		//调用BeanDefinition的getBean() 得到bean
		return this.beanDefinitionMap.get(beanName).getBean();
	}
	//对ref属性的处理
	public void addRef(){
		    Iterator<Map.Entry<String, BeanDefinition>> entries = beanDefinitionMap.entrySet().iterator();  
  
            while (entries.hasNext()) {  
  
                Map.Entry<String, BeanDefinition> entry = entries.next();  
				List<PropertyValue> propertyValues=(entry.getValue()).getPropertyValues().GetPropertyValues();
				for(int i=0;i<propertyValues.size(),i++){
					if(propertyValues.get(i).getReference()==true){
						//更改value值
						propertyValues.get(i).setValue(beanDefinitionMap.get((propertyValues.get(i).getName()).getBean()));
						propertyValues.get(i).setReference()=false;
					}
				}
            }  
	}
	//方法放豆子名和豆子定义到图里 这个图也是这个父类中的东西
	public void registerBeanDefinition(String beanName, BeanDefinition beanDefinition)
	{
		
		beanDefinition = GetCreatedBean(beanDefinition);
	
		this.beanDefinitionMap.put(beanName, beanDefinition);
	}
	
	protected abstract BeanDefinition GetCreatedBean(BeanDefinition beanDefinition);
}
