
public class Entry {
	public static final int NEVER_USED = -1;
	public static final int PREVIOUSLY_USED = -2;
	public static final int IN_USE = -3;

	private String stuff;
	private int state;

	public Entry() {
		state = NEVER_USED;
	}
	
	public String getStuff() {
		return stuff;
	}
	public void setStuff(String stuff) {
		this.stuff = stuff;
	}
	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
	}

}
