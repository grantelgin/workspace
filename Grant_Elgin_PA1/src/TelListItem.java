import java.io.Serializable;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * TelListItem is a doubly linkedList that defines the data stored in each node of a TelListManager instance.  
 * @author grantelgin
 *
 */

public class TelListItem implements Serializable {
	private String name;
	private String email;
	private String phoneNumber;
	
	private TelListItem next;
	private TelListItem prev;
	
	public TelListItem() {
		name = "";
		email = "";
		phoneNumber = "";
	}
	
	public void setListItem (TelListItem data) {
		setName(data.name);
		setEmail(data.email);
		setPhoneNumber(data.phoneNumber);
		setNext(data.next);
		setPrev(data.prev);
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public String getEmail() {
		return email;
	}
	public boolean setEmail(String email) {
		boolean isValid = true;
		
		if (validateEmail(email))
			this.email = email;
		else 
			isValid = false;
		
		return isValid;
	}
	
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public boolean setPhoneNumber(String phoneNumber) {
		boolean isValid = true;
		// phone number validation is turned off by default. The user confirms the record to be saved and can 
		// change the number format at their own discretion
		
		//if (validatePhoneNumber(phoneNumber))
			this.phoneNumber = phoneNumber;
		//else
		//	isValid = false;
		
		return isValid;
	}
	
	public TelListItem getNext() {
		return next;
	}

	public void setNext(TelListItem next) {
		this.next = next;
	}

	public TelListItem getPrev() {
		return prev;
	}

	public void setPrev(TelListItem prev) {
		this.prev = prev;
	}

	private boolean validateEmail(String email){
		boolean isValid = true;
		
		try {

			Pattern p = Pattern.compile("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@" + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");
		    Matcher m = p.matcher(email);
		    if (m.matches())
		    	isValid = true;
		    else 
		    	isValid = false;
		} 
		catch (Exception e) {
			isValid = false;
		}
		
		return isValid;
	}
	
	private boolean validatePhoneNumber(String phoneNumber) {
		boolean isValid = true;
		
		Pattern p = Pattern.compile("\\d{3}-\\d{7}");
		Matcher m = p.matcher(phoneNumber);
	 
	      if (m.matches()) 
	    	  isValid = true;
	      
	      else
	    	  isValid = false;
		
		return isValid;
	}
}
