package dev.edu.javaee.spring.bean;

//属性值的类主要有两个属性 String name Object value

/**
两个属性：
1.String name
2.Object value 
**/
public class PropertyValue {
	public PropertyValue(String name, Object value,boolean reference) {
		this.name = name;
		this.value = value;
		this.reference=reference;
	}
	private String name;
	
	private Object value;
	private boolean reference;
	
	public boolean getReference(){
		return reference;
	}
	public void setReference(boolean reference){
		this.reference=reference;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Object getValue() {
		return value;
	}
	public void setValue(Object value) {
		this.value = value;
	}
}
