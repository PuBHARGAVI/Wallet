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
		double newValue=0;
		
		Wallet wallet=new Wallet();
		double currentValue=wallet.getCurrencyType1Value();
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
		String currencyType1 = "Rupees";
		double currencyType1Value = 74.85;
		String currencyType2 = "Dollars";
		double currencyType2Value = 1;
		double currencyValue = 100;
		double expectedType1Value=0;
		double expectedType2Value=0.66399;
		double actualType1Value=0;
		double actualType2Value=0;
		
		Wallet wallet=new Wallet();
		try {
			wallet.depositCurrency(currencyType1, currencyType1Value);
			wallet.depositCurrency(currencyType2, currencyType2Value);
			wallet.withdrawCurrency(currencyType1, currencyValue);
			actualType1Value=wallet.getCurrencyType1Value();
			actualType2Value=wallet.getCurrencyType2Value();
		} catch (LimitExceededException e) {
			// TODO Auto-generated catch block
		}
		
		
		assertEquals(expectedType1Value,actualType1Value,0.00001);
		assertEquals(expectedType2Value,actualType2Value,0.00001);
	}
	
	@Test
	public void testIsSuccessfulForWithdrawDollars(){
		String currencyType1 = "Rupees";
		double currencyType1Value = 74.85;
		String currencyType2 = "Dollars";
		double currencyType2Value = 1;
		double currencyValue = 2;
		double expectedType1Value=0;
		double expectedType2Value=0;
		double actualType1Value=0;
		double actualType2Value=0;
		
		Wallet wallet=new Wallet();
		try {
			wallet.depositCurrency(currencyType1, currencyType1Value);
			wallet.depositCurrency(currencyType2, currencyType2Value);
			wallet.withdrawCurrency(currencyType2, currencyValue);
			actualType1Value=wallet.getCurrencyType1Value();
			actualType2Value=wallet.getCurrencyType2Value();
		} catch (LimitExceededException e) {
			// TODO Auto-generated catch block
		}
		
		
		assertEquals(expectedType1Value,actualType1Value,0.00001);
		assertEquals(expectedType2Value,actualType2Value,0.00001);
	
		}
	@Test
	public void testThrowsExceptionForWithdrawRupeesWhenValueUnderflows(){
		String currencyType1 = "Rupees";
		double currencyType1Value = 74.85;
		String currencyType2 = "Dollars";
		double currencyType2Value = 1;
		double currencyValue = 500;
		String expectedExceptionMessage="Less Balance. Withdraw unsuccessful!";
		String actualExceptionMessage="";
		
		Wallet wallet=new Wallet();
		try {
			wallet.depositCurrency(currencyType1, currencyType1Value);
			wallet.depositCurrency(currencyType2, currencyType2Value);
			wallet.withdrawCurrency(currencyType1, currencyValue);
		} catch (LimitExceededException e) {
			// TODO Auto-generated catch block
			actualExceptionMessage=e.getMessage().toString();
		}
		
		assertEquals(expectedExceptionMessage,actualExceptionMessage);
	}
	
	
	
	@Test
	public void testThrowsExceptionForWithdrawDollarsWhenValueUnderflows(){
		String currencyType1 = "Rupees";
		double currencyType1Value = 74.85;
		String currencyType2 = "Dollars";
		double currencyType2Value = 1;
		double currencyValue = 3;
		String expectedExceptionMessage="Less Balance. Withdraw unsuccessful!";
		String actualExceptionMessage="";
		
		Wallet wallet=new Wallet();
		try {
			wallet.depositCurrency(currencyType1, currencyType1Value);
			wallet.depositCurrency(currencyType2, currencyType2Value);
			wallet.withdrawCurrency(currencyType2, currencyValue);
		} catch (LimitExceededException e) {
			// TODO Auto-generated catch block
			actualExceptionMessage=e.getMessage().toString();
		}
		
		assertEquals(expectedExceptionMessage,actualExceptionMessage);
	}
	
	@Test
	public void testThrowsExceptionForWithdrawCurrencyWhenValueIsZero() {
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
	public void testThrowsExceptionForWithdrawCurrencyWhenValueIsNegative() {
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
	public void testToCheckBalanceForPreferredCurrencyTypeRupees(){
		double rupeesValue=100;
		double dollarsValue=1;
		double expectedRupeesValue=174.85;
	
		Wallet wallet=new Wallet();
		try {
			wallet.depositCurrency("Rupees", rupeesValue);
			wallet.depositCurrency("Dollars", dollarsValue);
		} catch (LimitExceededException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		double actualRupeesValue=wallet.checkBalanceForPreferredCurrencyType("Rupees");
		
		assertEquals(expectedRupeesValue,actualRupeesValue,0.00001);
	}
	
	@Test
	public void testToCheckBalanceForPreferredCurrencyTypeDollars(){
		double rupeesValue=100;
		double dollarsValue=1;
		double expectedDollarsValue=2.33600;
	
		Wallet wallet=new Wallet();
		try {
			wallet.depositCurrency("Rupees", rupeesValue);
			wallet.depositCurrency("Dollars", dollarsValue);
		} catch (LimitExceededException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		double actualDollarsValue=wallet.checkBalanceForPreferredCurrencyType("Dollars");
		
		assertEquals(expectedDollarsValue,actualDollarsValue,0.00001);
	}
}