package Wallet;

import javax.naming.LimitExceededException;

public class Wallet {
	private String currencyType1="Rupees";
	private double currencyType1Value;
	private String currencyType2="Dollars";
	private double currencyType2Value;
	
	public String getCurrencyType1() {
		return currencyType1;
	}

	public void setCurrencyType1(String currencyType1) {
		this.currencyType1 = currencyType1;
	}

	public double getCurrencyType1Value() {
		return currencyType1Value;
	}

	public void setCurrencyType1Value(double currencyType1Value) {
		this.currencyType1Value = currencyType1Value;
	}

	public String getCurrencyType2() {
		return currencyType2;
	}

	public void setCurrencyType2(String currencyType2) {
		this.currencyType2 = currencyType2;
	}

	public double getCurrencyType2Value() {
		return currencyType2Value;
	}

	public void setCurrencyType2Value(double currencyType2Value) {
		this.currencyType2Value = currencyType2Value;
	}

	public boolean compareDollarsAndRupees(String currencyType1, double currencyType1Value, String currencyType2,
			double currencyType2Value) {
		this.currencyType1 = currencyType1;
		this.currencyType1Value = currencyType1Value;
		this.currencyType2 = currencyType2;
		this.currencyType2Value = currencyType2Value;
		// TODO Auto-generated method stub
		if(currencyType1=="Rupees" &&  currencyType2=="Dollars") {
			if(currencyType1Value/74.85==currencyType2Value) {
				return true;
			}
		}
		return false;
	}
	private void checkForZeroCurrencyValueException() throws ZeroCurrencyValueException {
        throw new ZeroCurrencyValueException("Currency value cannot be zero");
    }

    private void checkForNegativeCurrencyValueException() throws NegativeCurrencyValueException {
    	throw new NegativeCurrencyValueException("Currency value cannot be Negative");
    }
	public Object depositCurrency(String currencyType,double currencyValue) throws LimitExceededException {
		double newValue=0;
		if(currencyValue>=Double.MAX_VALUE) {
			if(currencyType==currencyType1)
				throw new LimitExceededException("Total Rupees balance overflowed. Deposit unsuccessful!");
			else if(currencyType==currencyType2)
				throw new LimitExceededException("Total Dollars balance overflowed. Deposit unsuccessful!");
		}
		else if(currencyValue==0) {
			try {
				checkForZeroCurrencyValueException();
			} catch (ZeroCurrencyValueException e) {
				// TODO Auto-generated catch block
				return e.getMessage();
			}
		}
		else if(currencyValue<0) {
			try {
				checkForNegativeCurrencyValueException();
			} catch (NegativeCurrencyValueException e) {
				// TODO Auto-generated catch block
				return e.getMessage();
			}
		}
		if(currencyType==currencyType1) {
			currencyType1Value+=currencyValue;
			newValue=currencyType1Value;
		}
		else if(currencyType==currencyType2) {
			currencyType2Value+=currencyValue;
			newValue=currencyType2Value;
		}
		return newValue;
	}

	public Object withdrawCurrency(String currencyType, double currencyValue) throws LimitExceededException {
		double newValue=0;
		if(currencyType==currencyType1 && currencyValue>currencyType1Value)
			throw new LimitExceededException("Less Rupees Balance. Withdraw unsuccessful!");
		else if(currencyType==currencyType2 && currencyValue>currencyType2Value)
			throw new LimitExceededException("Less Dollars Balance. Withdraw unsuccessful!");
		else if(currencyValue==0) {
			try {
				checkForZeroCurrencyValueException();
			} catch (ZeroCurrencyValueException e) {
				// TODO Auto-generated catch block
				return e.getMessage();
			}
		}
		else if(currencyValue<0) {
			try {
				checkForNegativeCurrencyValueException();
			} catch (NegativeCurrencyValueException e) {
				// TODO Auto-generated catch block
				return e.getMessage();
			}
		}
		if(currencyType==currencyType1) {
			currencyType1Value-=currencyValue;
			newValue=currencyType1Value;
		}
		else if(currencyType==currencyType2) {
			currencyType2Value-=currencyValue;
			newValue=currencyType2Value;
		}
		return newValue;
		
	}
}