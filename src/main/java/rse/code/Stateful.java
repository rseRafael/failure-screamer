package rse.code;

import java.util.HashMap;
import java.util.Map;

public class Stateful{

	private int state;
	private String startMsg = "Stateful object is created.";
	private Map<Integer, String> stateMsgs;
	
	public Stateful() {
		this( 0 );
	}
	public Stateful(int state) {
		this.setState(state);
		this.stateMsgs = new HashMap<>();
		this.stateMsgs.put( this.getState(),startMsg );
	}
	public int getState() {
		return this.state;
	}
	public void setState(int newState) throws UnsupportedOperationException{
		if(newState <  0) {
			String message = "State must be greater than zero.";
			RuntimeException error = new UnsupportedOperationException(message);
			throw error;
		}
		else {
			this.state = newState;
		}
	}
	public void next() {
		next("", false);
	}
	public void next(String message, boolean shouldPrint) {
		this.setState(this.state + 1);
		this.stateMsgs.put(this.getState(), message);
		if(shouldPrint) {
			System.out.println( this.getState() + " - " + stateMsgs.get( this.getState() ) );
		}
		
	}
}
