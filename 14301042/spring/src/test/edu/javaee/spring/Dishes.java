package test.edu.javaee.spring;
/**
类名：Dishes
两个属性：
1.private String dishName; 食物的名字
2.private Integer dishPrice;食物的价格
五个方法：
1.String printDishes()
return String.format("Dish Name: %s. Dish Price: %d." ,getDishName(),getDishPrice()); 返回String 名字 和价格
**/
public class Dishes {
	private String dishName;
	private Integer dishPrice;
	public String getDishName() {
		return dishName;
	}
	public void setDishName(String dishName) {
		this.dishName = dishName;
	}
	
	public Integer getDishPrice() {
		return dishPrice;
	}
	public void setDishPrice(Integer dishPrice) {
		this.dishPrice = dishPrice;
	}
	public String printDishes()
	{
		return String.format("Dish Name: %s. Dish Price: %d." ,getDishName(),getDishPrice());
	}
}
