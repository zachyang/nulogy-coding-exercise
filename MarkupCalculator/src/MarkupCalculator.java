public class MarkupCalculator {

   public static final double BASE_MARKUP = 0.05;
   public static final double PERSON_MARKUP = 0.012;
   public static final ProductMarkup[] NOTED_PRODUCTS = {new ProductMarkup("drugs", 0.075), 
	   													  new ProductMarkup("food", 0.13),
	   													  new ProductMarkup("electronics", 0.02)};

   public static double calculatePrice(double basePrice, int numberOfPeople, String material) {
	   if (basePrice < 0) {
		   throw new IllegalArgumentException("ERROR: Base price cannot be negative.");
	   } else if (numberOfPeople < 1) {
		   throw new IllegalArgumentException("ERROR: There must be at least one person involved.");
	   } else if (material == null) {
		   throw new NullPointerException("ERROR: No material specified.");
	   }
	   
	   double newPrice = applyMarkup(basePrice, BASE_MARKUP);
	   double secondaryMarkup = numberOfPeople * PERSON_MARKUP;
	   for (ProductMarkup notedProduct : NOTED_PRODUCTS) {
		   if (material.toLowerCase().contains(notedProduct.getName())) {
			   secondaryMarkup += notedProduct.getMarkup();
		   }
	   }
	   
	   newPrice = applyMarkup(newPrice, secondaryMarkup);
	   newPrice = Math.round(newPrice*100.0)/100.0;
	   return newPrice;
   }
   
   private static double applyMarkup(double input, double markup) {
	   return input*(1.0 + markup);
   }
}
