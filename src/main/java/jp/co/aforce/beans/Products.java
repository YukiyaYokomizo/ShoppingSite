package jp.co.aforce.beans;

public class Products implements java.io.Serializable {

	private int productId;
	private String productName;
	private String category;
	private int price;
	private int stock;
	private String description;
	private String imagePath;
	private int salesCount;
	private boolean deleteFlag;

	public int getProductId() {
		return productId;
	}

	public String getProductName() {
		return productName;
	}

	public String getCategory() {
		return category;
	}

	public int getPrice() {
		return price;
	}

	public int getStock() {
		return stock;
	}

	public String getDescription() {
		return description;
	}

	public String getImagePath() {
		return imagePath;
	}

	public int getSalesCount() {
		return salesCount;
	}

	public boolean isDeleteFlag() {
		return deleteFlag;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public void setStock(int stock) {
		this.stock = stock;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}

	public void setSalesCount(int salesCount) {
		this.salesCount = salesCount;
	}

	public void setDeleteFlag(boolean deleteFlag) {
		this.deleteFlag = deleteFlag;
	}
}