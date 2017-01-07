package todo;

import org.junit.Test;

import junit.framework.Assert;

public class CalculatorTest {

	@Test
	public void testinc_add_numbers() {

		CallApp callApp = new CallApp();
		Assert.assertEquals(5, callApp.add(2, 3));

	}

}
