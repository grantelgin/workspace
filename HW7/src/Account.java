/**
 * Aug 3, 2013
 * Grant Elgin
 * CS 232 HW6
 * 
 * The Account class allows the user to define a new Asset or Liability account, assign an account code, and verify the new account code is not already in use. 
 * 
 */	

public class Account implements AccountInterface {
	private int chartOfAccountsCode;
	private String AccountType;

	public boolean verifyAccount(Account otherAccount) {
		// accounts only need to be compared on their Chart of Accounts code. Account Type is not a unique property.
		return (this.chartOfAccountsCode == otherAccount.chartOfAccountsCode);
	}
	
	public Account() {
		setChartOfAccountsCode(-1);
		setAccountType("undefined Account");
	}
	
	public Account (int initialCode, String initialType) {
		setChartOfAccountsCode(initialCode);
		setAccountType(initialType);
	}

	public int getChartOfAccountsCode() {
		return chartOfAccountsCode;
	}

	public void setChartOfAccountsCode(int chartOfAccountsCode) {
		this.chartOfAccountsCode = chartOfAccountsCode;
	}

	public String getAccountType() {
		return AccountType;
	}

	public void setAccountType(String accountType) {
		AccountType = accountType;
	}
	
	public void writeOutput() {
		System.out.println("Chart of Accounts Code: " + chartOfAccountsCode + "\nAccount Type: " + AccountType);
	}
	
	public boolean equals(Account otherAccount) {
		return (this.AccountType == otherAccount.AccountType && this.chartOfAccountsCode == otherAccount.chartOfAccountsCode);
	}
	
	

}
