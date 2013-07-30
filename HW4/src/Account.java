/**
 * July 25, 2013
 * Grant Elgin
 * CS 232 HW4
 * 
 * ShoppingList gets a list of available items, has user input items they want to purchase, verifies the items are available,
 * verifies funds are available and has user set a priority for each item if the total exceeds available funds. 
 * The goShopping method then loops through items in order of priority 1st, then by cost to show remaining balance after each item.  
 */	

public class Account implements AccountInterface {
private int chartOfAccountsCode;
private String AccountType;

public boolean verifyAccount(Account otherAccount) {
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
