import java.util.regex.Matcher;
import java.util.regex.Pattern;




public class TelListItem {
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
		
		if (validatePhoneNumber(phoneNumber))
			this.phoneNumber = phoneNumber;
		else
			isValid = false;
		
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
			//InternetAddress emailAddr = new InternetAddress(email);
			//emailAddr.validate();
			//Pattern p = Pattern.compile(".+@.+\\.[a-z]+");
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
