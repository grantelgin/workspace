/*
 * July 14, 2013
 * Grant Elgin
 * CS 232 HW3
 *  
 */

public class FundsAccount {

	private double availableFunds;
	private String accountName;
	
	public boolean equals(FundsAccount otherAccount) {
		return (this.accountName.equalsIgnoreCase(otherAccount.accountName));
	}

	public double getAvailableFunds() {
		return availableFunds;
	}

	public void setAvailableFunds(double availableFunds) {
		this.availableFunds = availableFunds;
	}
}
