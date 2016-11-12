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
public class AnotationBeanFactory extends AbstractBeanFactory{
	//xml的路径
	private String xmlPath;
	
	//要一个Resource 对象
	public AnotationBeanFactory(Resource resource)
	{
		String path;
		    DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dbBuilder = dbFactory.newDocumentBuilder();
			Document document = dbBuilder.parse(resource.getInputStream());
			
            Node bean = document.getElementsByTagName("bean");
			String path = bean.getAttributes().getNamedItem("class").getNodeValue();
		try {
			
			File file = new File(path);
            String [] fileName = file.list();
			//@Component注解
			for(int i=0,i<fileName.getLennth();i++){
				try {
					
					Class<?> beanClass = Class.forName(beanClassName);
					
					if(beanClass!=null && beanClass.isAnnotationPresent(Component.class)){
						BeanDefinition beandef = new BeanDefinition();
				        String ClassName = String.format("path.%s",fileName[i]);
            	        String beanName = String.format("Bean%s",fileName[i]);
				
				        //1 设置类名 类名是路径加类名
				
        		        beandef.setBeanClassName(ClassName);
						//2 设置 类对象
					    beandef.setBeanClass(beanClass);
						
						//3.设置propertyValues
            	        beandef.setPropertyValues(null);
				        //4.bean
				        Object bean = beanClass.newInstance();	
				        beanDefinition.setBean(bean);
				       this.registerBeanDefinition(beanName, beandef);
						
					}
					
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
		//@Autoware注解
		fieldAnnotation();
	}catch(exception e){
		
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
	
	
	public void fieldAnnotation(){  
	
	Iterator<Map.Entry<String, BeanDefinition>> entries = beanDefinitionMap.entrySet().iterator();  
  
            while (entries.hasNext()) {  
  
                Map.Entry<String, BeanDefinition> entry = entries.next();  
				Object bean =(entry.getValue()).getBean();
				try {  
            //获取其全部的字段描述  
            Field[] fields = bean.getClass().getFields();  
            for(Field f : fields){  
                if(f!=null && f.isAnnotationPresent(Autoware.class)){
                    Autoware resource = f.getAnnotation(Autoware.class);  
                    String name ="";  
                    Object value = null;  
                    if(resource.name()!=null&&!"".equals(resource.name())){  
                        name = resource.name();  
                        value = beanDefinitionMap.get(name);  
                    }else{  
                        for(String key : sigletions.keySet()){  
                            //判断当前属性所属的类型是否在配置文件中存在  
                            if(f.getType().isAssignableFrom(beanDefinitionMap.get(key).getBeanClass())){  
                                //获取类型匹配的实例对象  
                                value = beanDefinitionMap.get(key);  
                                break;  
                            }  
                        }  
                    }  
                    //允许访问private字段  
                    f.setAccessible(true);  
                    //把引用对象注入属性  
                    f.set(bean, value);  
                }  
            }  
        } catch (Exception e) {  
            log.info("字段注解解析异常..........");  
        }  
            }  
    }  
	
	

}
