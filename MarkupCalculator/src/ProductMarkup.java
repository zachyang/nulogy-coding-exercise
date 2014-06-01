public class ProductMarkup {

	private String productName;
	private double markup;
	
	public ProductMarkup(String productName, double markup) {
		this.productName = productName;
		this.markup = markup;
	}

	public String getName() {
		return productName;
	}
	
	public double getMarkup() {
		return markup;
	}
}
