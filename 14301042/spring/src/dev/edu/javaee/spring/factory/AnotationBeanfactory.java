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

//�̳��˳����ӹ���
/**
������XMLBeanFactory 
�̳��˳����� AbstractBeanFactory
AbstractBeanFactory ʵ����BeanFactory�ӿ�
һ�����ԣ�
private String xmlPath String ��������xml �ļ�·��
����������

����spring�����þ���ͨ���������ļ� ���������Ұ������ļ���ʵ���Ž�����
ref ������Ϊ�вο�����value�Ǹ�������������bean ֻ��Ҫ�ҵ���bean����

**/
public class AnotationBeanFactory extends AbstractBeanFactory{
	//xml��·��
	private String xmlPath;
	
	//Ҫһ��Resource ����
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
			//@Componentע��
			for(int i=0,i<fileName.getLennth();i++){
				try {
					
					Class<?> beanClass = Class.forName(beanClassName);
					
					if(beanClass!=null && beanClass.isAnnotationPresent(Component.class)){
						BeanDefinition beandef = new BeanDefinition();
				        String ClassName = String.format("path.%s",fileName[i]);
            	        String beanName = String.format("Bean%s",fileName[i]);
				
				        //1 �������� ������·��������
				
        		        beandef.setBeanClassName(ClassName);
						//2 ���� �����
					    beandef.setBeanClass(beanClass);
						
						//3.����propertyValues
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
			
		//@Autowareע��
		fieldAnnotation();
	}catch(exception e){
		
	}
	}

	//��д�˳��󷽷�GetCreatedBean
	@Override
	protected BeanDefinition GetCreatedBean(BeanDefinition beanDefinition) {
		
		try {
			
			Class<?> beanClass = beanDefinition.getBeanClass();
			Object bean = beanClass.newInstance();	
			
			List<PropertyValue> fieldDefinitionList = beanDefinition.getPropertyValues().GetPropertyValues();
			
			//��bean�����ʼ��
			for(PropertyValue propertyValue: fieldDefinitionList)
			{
				BeanUtil.invokeSetterMethod(bean, propertyValue.getName(), propertyValue.getValue());
			}
			//4. ����bean����
			beanDefinition.setBean(bean);
			
			//���ض���
			return beanDefinition;
			
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//���򷵻ؿ�
		return null;
	}
	
	
	public void fieldAnnotation(){  
	
	Iterator<Map.Entry<String, BeanDefinition>> entries = beanDefinitionMap.entrySet().iterator();  
  
            while (entries.hasNext()) {  
  
                Map.Entry<String, BeanDefinition> entry = entries.next();  
				Object bean =(entry.getValue()).getBean();
				try {  
            //��ȡ��ȫ�����ֶ�����  
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
                            //�жϵ�ǰ���������������Ƿ��������ļ��д���  
                            if(f.getType().isAssignableFrom(beanDefinitionMap.get(key).getBeanClass())){  
                                //��ȡ����ƥ���ʵ������  
                                value = beanDefinitionMap.get(key);  
                                break;  
                            }  
                        }  
                    }  
                    //�������private�ֶ�  
                    f.setAccessible(true);  
                    //�����ö���ע������  
                    f.set(bean, value);  
                }  
            }  
        } catch (Exception e) {  
            log.info("�ֶ�ע������쳣..........");  
        }  
            }  
    }  
	
	

}
