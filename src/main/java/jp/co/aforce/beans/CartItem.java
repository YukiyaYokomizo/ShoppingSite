package jp.co.aforce.beans;

public class CartItem implements java.io.Serializable {

	private Products product;
	private int quantity;

	public Products getProduct() {
		return product;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setProduct(Products product) {
		this.product = product;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public int getSubtotal() {
		return product.getPrice() * quantity;
	}
}