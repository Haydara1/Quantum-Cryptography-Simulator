package Person;

import Quantum.Communication;

public abstract class Participant {

	/*
	 * Attributes
	 */
	
	private String name;
	
	/*
	 * Constructors
	 */
	
	public Participant(String _name) {
		this.name = _name;
	}

	/*
	 * Getters
	 */
	
	public String getName() {
		return name;
	}
	
	/*
	 * Public methods
	 */
	
	@Override
	public String toString()
	{
		return this.getName();
	}
	
	/**
	 * Publishes a message in a public broadcast channel.
	 * @param communication; the channel in which the person wants to broadcast his message.
	 * @param message; the message that the person wants to broadcast.
	 */
	public void broadcast(Communication communication, Object message)
	{
		communication.setLastCommunicatedObject(message);
	}
	
}
