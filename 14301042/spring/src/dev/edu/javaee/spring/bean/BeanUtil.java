package dev.edu.javaee.spring.bean;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import dev.edu.javaee.spring.util.ReflectionUtils;


/**
关键字：
invokeSetterMethod( Object obj, String propertyName, Object propertyValue)

String setMethodName = String.format("set%s", String.valueOf(tmp));

Class<?> cls = obj.getClass();
field = cls.getDeclaredField(propertyName);
Class<?> type = field.getType();

Method method = ReflectionUtils.findMethod(cls, setMethodName, type);
method.invoke(obj, propertyValue);

主要作用：找到对应方法并执行
**/
public class BeanUtil {
	
	//static的方法 唤醒。。。。setter method 对象，字符串，对象安放方法
 	public static void invokeSetterMethod( Object obj, String propertyName, Object propertyValue)
	{
		//变string到char数组
		char [] tmp = propertyName.toCharArray();
		if(tmp[0] >= 'a' && tmp[0] <= 'z')
		{
			tmp[0] -= 32;
		}
	
		String setMethodName = String.format("set%s", String.valueOf(tmp));
		
		Field field;
		
		Class<?> cls = obj.getClass();
		try {
		
			field = cls.getDeclaredField(propertyName);
		
			Class<?> type = field.getType();
			
			Method method = ReflectionUtils.findMethod(cls, setMethodName, type);
			
			method.invoke(obj, propertyValue);
		} catch (NoSuchFieldException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
