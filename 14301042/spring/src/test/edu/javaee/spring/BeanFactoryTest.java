package test.edu.javaee.spring;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import dev.edu.javaee.spring.bean.BeanDefinition;
import dev.edu.javaee.spring.bean.PropertyValue;
import dev.edu.javaee.spring.bean.PropertyValues;
import dev.edu.javaee.spring.factory.BeanFactory;
import dev.edu.javaee.spring.factory.XMLBeanFactory;
import dev.edu.javaee.spring.factory.AnnotationBeanFactory;
import dev.edu.javaee.spring.resource.LocalFileResource;
/**
类名：BeanFactoryTest 豆子工厂测试
一个方法：testBeanCreateAndGet() 测试豆子创建和得到
LocalFileResource resource = new LocalFileResource("beans.xml"); 创建对象文件路径
BeanFactory beanFactory = new XMLBeanFactory(resource);创建对象 
Dishes d = (Dishes) beanFactory.getBean("egg dishes"); 得到特定的豆子通过名字
assertEquals(d.printDishes(), "Dish Name: fry eggs. Dish Price: 20.");
**/
public class BeanFactoryTest {

	@Test
	public void testBeanCreateAndGet() {
		//指定配置文件
		LocalFileResource resource = new LocalFileResource("beans.xml");
		LocalFileResource resources = new LocalFileResource("bean.xml");
		//生成是spring对象
		BeanFactory beanFactory = new XMLBeanFactory(resource);
		BeanFactory AbeanFactory = new AnotationBeanFactory(resources);
		// the BeanDefinition doesn`t create the real bean yet
		//调用spring对象方法对配置文件里所有类进行操作
		Dishes d = (Dishes) beanFactory.getBean("egg dishes");
		//类名
		String beanName = String.format("Bean%s","dishes");
        Dishes dA = (Dishes) AbeanFactory.getBean(beanName);		
		assertEquals(d.printDishes(), "Dish Name: fry eggs. Dish Price: 20.");
		
	}

}
