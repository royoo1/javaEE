package dev.edu.javaee.spring.bean;

import java.util.ArrayList;
import java.util.List;

//values多个值
/**
一个属性：List<PropertyValue>链表 propertyValues
两个方法：
1.AddPropertyValue(PropertyValue propertyValue) 添加PropertyValue对象到链表
2.List<PropertyValue> GetPropertyValues() 返回链表 
**/
public class PropertyValues {
	//链表存储PropertyValue对象
	private List<PropertyValue> propertyValues = new ArrayList<PropertyValue>();
	//添加对象类
	public void AddPropertyValue(PropertyValue propertyValue){
		propertyValues.add(propertyValue);
	}
	//返回链表
	public List<PropertyValue> GetPropertyValues()
	{
		return propertyValues;
	}
}
