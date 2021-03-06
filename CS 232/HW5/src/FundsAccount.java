import java.util.Scanner;

/**
 * Aug 3, 2013
 * Grant Elgin
 * CS 232 HW5
 *  
 *  FundsAccount extends Account class and allows the user to create a new Account to be used for purchasing items within the application. 
 *  
 */

public class FundsAccount extends Account {

	private double availableFunds;
	private String accountName;
	
	private static Scanner keyboard = new Scanner( System.in );
	
	public FundsAccount() {
		super();
		availableFunds = -1;
		accountName = "Account name not defined";
	}
	
	public FundsAccount(int initialChartCode, String initialAccountType, double initialFunds, String initialAccountName) {
		super(initialChartCode, initialAccountType);
		availableFunds = initialFunds;
		accountName = initialAccountName;
	}
	
	public FundsAccount createFundsAccount(){
		FundsAccount nfa = new FundsAccount();
		int coa = -1;
		
		System.out.println("Create a new account. Enter the account name, account type, chart of accounts code and available balance when prompted.\n");
		System.out.print("Account name: ");
		String newAccountName = keyboard.nextLine();
		System.out.print("Account type (Enter 'Asset' or 'Liability'): ");
		String newAccountType = keyboard.nextLine();
		
		if (newAccountType.equalsIgnoreCase("Asset")) {
			System.out.print("Chart of Accounts Code (Select a code between 100 and 199): ");
			coa = keyboard.nextInt();
		}
		if (newAccountType.equalsIgnoreCase("Liability")) {
			System.out.print("Chart of Accounts Code (Select a code between 200 and 299): ");
			coa = keyboard.nextInt();
		}
		
		System.out.print("Available funds: $ ");
		nfa.setAvailableFunds(keyboard.nextDouble());
		
		nfa.accountName = newAccountName;
		nfa.setAccountType(newAccountType);
		nfa.setChartOfAccountsCode(coa);
		
		return nfa;
			
	}
	
	public boolean equals(FundsAccount otherAccount) {
		return (this.accountName.equalsIgnoreCase(otherAccount.accountName));
	}

	public double getAvailableFunds() {
		return availableFunds;
	}

	public void setAvailableFunds(double availableFunds) {
		this.availableFunds = availableFunds;
	}
	
	public void writeOutput() {
		System.out.println("account name: " + accountName + "\nChart of Accounts Code: " + super.getChartOfAccountsCode() + "\nAccount Type: " + super.getAccountType() + "\nAvailable Funds: $ " + availableFunds);
	}
	
}
