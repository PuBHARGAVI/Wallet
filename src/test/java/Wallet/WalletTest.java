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
		
		Wallet wallet = new Wallet();		
		boolean actualValue = wallet.compareDollarsAndRupees(currencyType1, currencyType1Value, currencyType2, currencyType2Value);
		
		assertEquals(expectedValue,actualValue);
	}
	@Test
	public void testFor1XDollarEqualTo74_85XRupees() {
		String currencyType1 = "Rupees";
		double currencyType1Value = 74.85*3;
		String currencyType2 = "Dollars";
		double currencyType2Value = 3;
		boolean expectedValue=true;
		
		Wallet wallet = new Wallet();		
		boolean actualValue = wallet.compareDollarsAndRupees(currencyType1, currencyType1Value, currencyType2, currencyType2Value);
		
		assertEquals(expectedValue,actualValue);
	}
	
	@Test
	public void testFor1XDollarNotEqualTo74_85YRupees() {
		int dollarFactor=3;
		int rupeeFactor=4;
		String currencyType1 = "Rupees";
		double currencyType1Value = 74.85*rupeeFactor;
		String currencyType2 = "Dollars";
		double currencyType2Value = dollarFactor;
		boolean expectedValue=false;
		
		Wallet wallet = new Wallet();		
		boolean actualValue = wallet.compareDollarsAndRupees(currencyType1, currencyType1Value, currencyType2, currencyType2Value);
		
		assertEquals(expectedValue,actualValue);
	}
	
	@Test
	public void testIsSuccessfulForDepositingRupees(){
		String currencyType="Rupees";
		double currencyValue=100;
		
		Wallet wallet=new Wallet();
		double currentValue=wallet.getCurrencyType1Value();
		wallet.depositCurrency(currencyType, currencyValue);
		double newValue=wallet.getCurrencyType1Value();
		double actualValue=currencyValue+currentValue;
		double expectedValue=newValue;
		
		assertEquals(expectedValue,actualValue,0.00001);
	}
	
	@Test
	public void testIsSuccessfulForDepositDollars(){
		String currencyType="Dollars";
		double currencyValue=1;
		
		Wallet wallet=new Wallet();
		double currentValue=wallet.getCurrencyType2Value();
		wallet.depositCurrency(currencyType, currencyValue);
		double newValue=wallet.getCurrencyType2Value();
		double actualValue=currencyValue+currentValue;
		double expectedValue=newValue;
		
		assertEquals(expectedValue,actualValue,0.00001);
	}
}