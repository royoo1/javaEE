package dev.edu.javaee.spring.util;

import java.lang.reflect.Method;
import java.util.Arrays;
//反射集ReflectionUtils 类
/**
类名：ReflectionUtils 反射集  通过方法findMethod 找到对应的方法
一个方法：
public static Method findMethod(Class<?> cls, String name, Class<?>... parameterTypes) static类型，返回 Method对象，参数类对象：cls，String name,无限类对象名字 parameterTypes 参数类型
Class<?> currentClass = cls; 类对象赋值 
Method[] allMethods = currentClass.getDeclaredMethods(); 得到所有方法
从中找到名字相同参数相同的方法 返回 
**/
public class ReflectionUtils {
	//static方法findMethod(Class<?> cls, String name, Class<?>... parameterTypes) 三个参数 类 String名字  返回Method对象
	public static Method findMethod(Class<?> cls, String name, Class<?>... parameterTypes)
	{
		Class<?> currentClass = cls;
		while(currentClass != null)
		{
			//Method类数组 allMethods所有方法currentClass.getDeclaredMethods();从Class 的实例currentClass 调用getDeclaredMethods()方法获得所有的方法
			Method[] allMethods = currentClass.getDeclaredMethods();
			for(Method method : allMethods)
			{
				//遍历所有的方法 如果传过来的参数String name是其中的一个方法名同时 parameterTypes参数类型是null或者Arrays.equals(parameterTypes, method.getParameterTypes())))
				//进行判断 参数类型是否与方法的参数类型一致，一致则返回method
				if(name.equals(method.getName()) &&
						(parameterTypes == null || 
						Arrays.equals(parameterTypes, method.getParameterTypes())))
						return method;
			}
			//currentClass一直向上得到父类知道变成interface再往上就是空了
			currentClass = currentClass.getSuperclass();
		}
		//否则返回空
		return null;
	}
	
	

}
