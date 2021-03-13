package Wallet;
import static org.junit.Assert.assertEquals;

import org.junit.Test;

import Wallet.Wallet;

public class WalletTest {
	@Test
	public void testFor1DollarEqualTo74_85Rupees() {
		String currencyType1 = "Rupees";
		double currencyType1Value = 74.85;
		String currencyType2 = "Dollars";
		double currencyType2Value = 1;
		boolean expectedValue=true;
		
		Wallet wallet = new Wallet(currencyType1, currencyType1Value, currencyType2, currencyType2Value);		
		boolean actualValue = wallet.compareDollarsAndRupees();
		
		assertEquals(expectedValue,actualValue);
	}
}