package Quantum;

/*
 * This class will simulate the act of exchanging information publicly. 
 * It will allow to implement interception, measurement failure, and real world.
 * Please refer to the abstract class Protocol for more info on the implementation.
 */

/*
 * This class can be seen as the fiber optic cable that transmits info, 
 * in more complex environments it can be a representative form of satellites,
 * void, receivers...
 */

public class Communication {

	/*
	 * Attributes
	 */
	
	private Object lastCommunicatedObject;
	
	private String name; // In case of having multiple broadcast channels.
	
	/*
	 * Constructor
	 */
	
	public Communication()
	{
		this.name = "";
		
		System.out.println("Public channel started.");
	}
	
	public Communication(String _name) {
		
		this.name = _name;
		
		System.out.println("Public channel " + this.name + " has started.");
	}

	/*
	 * Getters and Setters
	 */
	
	public Object getLastCommunicatedObject() {
		return lastCommunicatedObject;
	}

	public void setLastCommunicatedObject(Object lastCommunicatedObject) {
		this.lastCommunicatedObject = lastCommunicatedObject;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	
}
