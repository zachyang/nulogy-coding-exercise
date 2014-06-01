import static org.junit.Assert.*;

import org.junit.Test;


public class MarkupCalculatorTest {
	
	@Test
	public void testCalculatePrice() {
		double input = 100.0;
		double expectedPrice;
		
		// Testing base markup with only 1 person.
		expectedPrice = input*(1.0 + MarkupCalculator.BASE_MARKUP)*(1.0 + MarkupCalculator.PERSON_MARKUP);
		assertTrue(MarkupCalculator.calculatePrice(input, 1, "test") == roundMoneyValue(expectedPrice));
		
		// Testing multiple people on the same job.
		expectedPrice = input*(1.0 + MarkupCalculator.BASE_MARKUP)*(1.0 + MarkupCalculator.PERSON_MARKUP*3);
		assertTrue(MarkupCalculator.calculatePrice(input, 3, "test") == roundMoneyValue(expectedPrice));
		
		// Testing material markup.
		ProductMarkup testedMaterial = MarkupCalculator.NOTED_PRODUCTS[0];
		expectedPrice = input*(1.0 + MarkupCalculator.BASE_MARKUP)*(1.0 + MarkupCalculator.PERSON_MARKUP + testedMaterial.getMarkup());
		assertTrue(MarkupCalculator.calculatePrice(input, 1, testedMaterial.getName()) == roundMoneyValue(expectedPrice));
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void testInvalidBasePrice() {
		MarkupCalculator.calculatePrice(-1, 1, "test");
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void testInvalidPeopleNumber() {
		MarkupCalculator.calculatePrice(100, 0, "test");
	}
	
	@Test (expected = NullPointerException.class)
	public void testNullMaterial() {
		MarkupCalculator.calculatePrice(100, 1, null);
	}
	
	private double roundMoneyValue(double price) {
		 return Math.round(price*100.0)/100.0;
	}

}
