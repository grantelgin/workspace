	
public class Account {
private int chartOfAccountsCode;
private String AccountType;

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
