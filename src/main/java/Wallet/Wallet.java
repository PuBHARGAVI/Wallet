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
				return e.getMessage();
			}
		}
		else if(currencyValue<0) {
			try {
				checkForNegativeCurrencyValueException();
			} catch (NegativeCurrencyValueException e) {
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

		if(currencyValue==0) {
			try {
				checkForZeroCurrencyValueException();
			} catch (ZeroCurrencyValueException e) {
				return e.getMessage();
			}
		}
		else if(currencyValue<0) {
			try {
				checkForNegativeCurrencyValueException();
			} catch (NegativeCurrencyValueException e) {
				return e.getMessage();
			}
		}
		double totalAmount=checkBalanceForPreferredCurrencyType(currencyType);
		if(totalAmount>=currencyValue) {
			if(currencyType=="Rupees") {
				if(currencyType1Value>=currencyValue) {
					currencyType1Value-=currencyValue;
				}
				else {
					currencyValue-=currencyType1Value;
					currencyType1Value=0;
					double totalBalance=checkBalanceForPreferredCurrencyType("Rupees");
					double remainingBalance=totalBalance-currencyValue;
					currencyType2Value=remainingBalance/74.85;
				}
			}
			else if(currencyType=="Dollars"){
				if(currencyType2Value>=currencyValue) {
					currencyType2Value-=currencyValue;
				}
				else {
					currencyValue-=currencyType2Value;
					currencyType2Value=0;
					double totalBalance=checkBalanceForPreferredCurrencyType("Dollars");
					double remainingBalance=totalBalance-currencyValue;
					currencyType1Value=remainingBalance*74.85;
				}
			}
		}
		else {
			throw new LimitExceededException("Less Balance. Withdraw unsuccessful!");
		}
		return newValue;
		
	}

	public double checkBalanceForPreferredCurrencyType(String currencyType) {
		if(currencyType=="Rupees") {
			return currencyType1Value+currencyType2Value*74.85;
		}
		else {
			return currencyType1Value/74.85+currencyType2Value;
		}
	}
	}