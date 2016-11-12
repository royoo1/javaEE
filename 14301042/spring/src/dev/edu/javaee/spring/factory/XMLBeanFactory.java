package dev.edu.javaee.spring.factory;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.xml.sax.SAXException;

import dev.edu.javaee.spring.bean.BeanDefinition;
import dev.edu.javaee.spring.bean.BeanUtil;
import dev.edu.javaee.spring.bean.PropertyValue;
import dev.edu.javaee.spring.bean.PropertyValues;
import dev.edu.javaee.spring.resource.Resource;

//继承了抽象豆子工厂
/**
类名：XMLBeanFactory 
继承了抽象类 AbstractBeanFactory
AbstractBeanFactory 实现了BeanFactory接口
一个属性：
private String xmlPath String 用来保存xml 文件路径
两个方法：

所以spring的作用就是通过读配置文件 创建对象并且把配置文件中实例放进对象
ref 属性因为有参考所以value是个对象而对象就是bean 只需要找到该bean就行

**/
public class XMLBeanFactory extends AbstractBeanFactory{
	//xml的路径
	private String xmlPath;
	
	//要一个Resource 对象
	public XMLBeanFactory(Resource resource)
	{
		try {
			
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dbBuilder = dbFactory.newDocumentBuilder();
			Document document = dbBuilder.parse(resource.getInputStream());
			
            NodeList beanList = document.getElementsByTagName("bean");
			NodeList beanListCopy=beanList.clone();
			
            for(int i = 0 ; i < beanList.getLength(); i++)
            {
				
            	Node bean = beanList.item(i);
				
            	BeanDefinition beandef = new BeanDefinition();
				
            	String beanClassName = bean.getAttributes().getNamedItem("class").getNodeValue();
            	String beanName = bean.getAttributes().getNamedItem("id").getNodeValue();
            	
				//1 设置类名 类名是路径加类名
        		beandef.setBeanClassName(beanClassName);
        		
				try {
					
					Class<?> beanClass = Class.forName(beanClassName);
					//2 设置 类对象
					beandef.setBeanClass(beanClass);
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
        		
				
        		PropertyValues propertyValues = new PropertyValues();
        		
				//子节点集
        		NodeList propertyList = bean.getChildNodes();
				
				//操作每个子节点
            	for(int j = 0 ; j < propertyList.getLength(); j++)
            	{
					
            		Node property = propertyList.item(j);
					
            		if (property instanceof Element) {
						
						//1.获得name属性
        				Element ele = (Element) property;
        				String name = ele.getAttribute("name");
						
						//定义一个类 type
        				Class<?> type;
						try {
							
							type = beandef.getBeanClass().getDeclaredField(name).getType();
							
							//2.获得value属性
							Object value = ele.getAttribute("value");
							if(value==null){
								Object ref = ele.getAttribute("ref");
								propertyValues.AddPropertyValue(new PropertyValue(
							   name,true));
							   
							}
	        				else{
							//2.1若value为int 转换为 int
	        				if(type == Integer.class)
	        				{
	        					value = Integer.parseInt((String) value);
	        				}
	        				//3，添加入propertyValues
	        				propertyValues.AddPropertyValue(new PropertyValue(
							name,value,false));
						    }
						} catch (NoSuchFieldException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (SecurityException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
        				
        				
        			}
            	}
				
				//3.设置propertyValues
            	beandef.setPropertyValues(propertyValues);
            	//放进地图 此方法在父类中已经实现 
            	this.registerBeanDefinition(beanName, beandef);
            }
			
			/**
			加入参考对象
			**/
			
		 addRef();
			
            
		} catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
		
	}

	//重写了抽象方法GetCreatedBean
	@Override
	protected BeanDefinition GetCreatedBean(BeanDefinition beanDefinition) {
		
		try {
			
			Class<?> beanClass = beanDefinition.getBeanClass();
			Object bean = beanClass.newInstance();	
			
			List<PropertyValue> fieldDefinitionList = beanDefinition.getPropertyValues().GetPropertyValues();
			
			//给bean对象初始化
			for(PropertyValue propertyValue: fieldDefinitionList)
			{
				BeanUtil.invokeSetterMethod(bean, propertyValue.getName(), propertyValue.getValue());
			}
			//4. 设置bean对象
			beanDefinition.setBean(bean);
			
			//返回对象
			return beanDefinition;
			
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//否则返回空
		return null;
	}
	
	
	

}
