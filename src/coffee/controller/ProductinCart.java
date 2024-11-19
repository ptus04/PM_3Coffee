package coffee.controller;

import java.util.ArrayList;
import java.util.List;

import coffee.entity.SanPham;


public class ProductinCart {
	private ArrayList<SanPham> listOfProduct = null;
	public ProductinCart() {
		listOfProduct = new ArrayList<SanPham>();
	}
	public boolean checkInCart(SanPham product ) {
		if(listOfProduct.contains(product)) {
			return false;
		}
		listOfProduct.add(product);
//		System.out.println(listOfProduct.size());
		return true;
	}
	public int getSize() {
		return listOfProduct.size();
	}
	public SanPham getProduct(int i) {
		return listOfProduct.get(i);
	}
}
