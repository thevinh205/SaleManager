package sale.model;

import java.io.Serializable;
import java.util.List;

public class ShopView implements Serializable{
	private int id;
	private Shop shop;
	private List<Product> listProduct;
	private List<Member> listEmployee;
	
	public int getId() {
		return id;
	}
	
	public Shop getShop() {
		return shop;
	}
	
	public List<Product> getListProduct() {
		return listProduct;
	}
	
	public List<Member> getListEmployee() {
		return listEmployee;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public void setShop(Shop shop) {
		this.shop = shop;
	}
	
	public void setListProduct(List<Product> listProduct) {
		this.listProduct = listProduct;
	}
	
	public void setListEmployee(List<Member> listEmployee) {
		this.listEmployee = listEmployee;
	}
	
	
}
