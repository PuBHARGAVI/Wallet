package Wallet;
import static org.junit.Assert.assertEquals;

import javax.naming.LimitExceededException;

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
		double newValue=0;
		try {
			wallet.depositCurrency(currencyType, currencyValue);
		} catch (LimitExceededException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		newValue=wallet.getCurrencyType1Value();
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
		try {
			wallet.depositCurrency(currencyType, currencyValue);
		} catch (LimitExceededException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		double newValue=wallet.getCurrencyType2Value();
		double actualValue=currencyValue+currentValue;
		double expectedValue=newValue;
		
		assertEquals(expectedValue,actualValue,0.00001);
	}
	
	@Test
	public void testThrowsExceptionForDepositingRupeesWhenValueOverflows(){
		String currencyType="Rupees";
		double currencyValue=Double.MAX_VALUE;
		String expectedExceptionMessage="Total Rupees balance overflowed. Deposit unsuccessful!";
		String actualExceptionMessage="";
		
		Wallet wallet=new Wallet();
		try {
			wallet.depositCurrency(currencyType, currencyValue);
		} catch (LimitExceededException e) {
			// TODO Auto-generated catch block
			actualExceptionMessage=e.getMessage().toString();
		}
		
		assertEquals(expectedExceptionMessage,actualExceptionMessage);
	}
	
	@Test
	public void testThrowsExceptionForDepositingDollarsWhenOverflows(){
		String currencyType="Dollars";
		double currencyValue=Double.MAX_VALUE;
		String expectedExceptionMessage="Total Dollars balance overflowed. Deposit unsuccessful!";
		String actualExceptionMessage="";
		
		Wallet wallet=new Wallet();
		try {
			wallet.depositCurrency(currencyType, currencyValue);
		} catch (LimitExceededException e) {
			// TODO Auto-generated catch block
			actualExceptionMessage=e.getMessage().toString();
		}
		
		assertEquals(expectedExceptionMessage,actualExceptionMessage);		
	}
	
	@Test
	public void testThrowsExceptionForDepositingCurrencyWhenValueIsZero() {
		String currencyType="Dollars";
		double currencyValue=0;
		String expectedExceptionMessage="Currency value cannot be zero";
		String actualExceptionMessage="";
		
		Wallet wallet=new Wallet();
		try {
			actualExceptionMessage=(String) wallet.depositCurrency(currencyType, currencyValue);
		} catch (LimitExceededException e) {
			// TODO Auto-generated catch block
		}
		
		assertEquals(expectedExceptionMessage,actualExceptionMessage);		
	}
	
	@Test
	public void testThrowsExceptionForDepositingCurrencyWhenValueIsNegative() {
		String currencyType="Dollars";
		double currencyValue=-2;
		String expectedExceptionMessage="Currency value cannot be Negative";
		String actualExceptionMessage="";
		
		Wallet wallet=new Wallet();
		try {
			actualExceptionMessage=(String) wallet.depositCurrency(currencyType, currencyValue);
		} catch (LimitExceededException e) {
			// TODO Auto-generated catch block
		}
		
		assertEquals(expectedExceptionMessage,actualExceptionMessage);		
	}
	
	
	@Test
	public void testIsSuccessfulForWithdrawingRupees(){
		String currencyType="Rupees";
		double currencyValue=100;
		double expectedValue=0;
		double currentValue=0;
		
		Wallet wallet=new Wallet();
		double newValue=0;
		try {
			wallet.depositCurrency(currencyType, currencyValue);
			currentValue=wallet.getCurrencyType1Value();
			wallet.withdrawCurrency(currencyType, currencyValue);
		} catch (LimitExceededException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		newValue=wallet.getCurrencyType1Value();
		double actualValue=currentValue-currencyValue;
		
		assertEquals(expectedValue,actualValue,0.00001);
	}
	
	@Test
	public void testIsSuccessfulForWithdrawDollars(){
		String currencyType="Dollars";
		double currencyValue=1;
		double expectedValue=0;
		double currentValue=0;
		
		Wallet wallet=new Wallet();
		double newValue=0;
		try {
			wallet.depositCurrency(currencyType, currencyValue);
			currentValue=wallet.getCurrencyType2Value();
			wallet.withdrawCurrency(currencyType, currencyValue);
		} catch (LimitExceededException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		newValue=wallet.getCurrencyType2Value();
		double actualValue=currentValue-currencyValue;
		
		assertEquals(expectedValue,actualValue,0.00001);	
		}
	
}