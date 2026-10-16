package Protocols;

import Person.Alice;
import Person.Bob;
import Person.Eve;

public abstract class Protocol {

	/*
	 *  This function only implements the protocol in its simplest form,
	 *  with no spies, as it will only serve as a foundation for the more realist simulations.
	 */
	public abstract void startProtocol();
	
	/*
	 * This function will be closer to real-world scenarios, as it will make the use of three persons;
	 * a sender, a receiver, and a spy, as well as a public channel communication that can be intercepted.
	 */
	
	/**
	 * 
	 * @param alice: Sender
	 * @param bob: Receiver
	 * @param eve: Spy
	 * @return 
	 * @throws Exception 
	 */
	public abstract String startProtocol(
			Alice alice, Bob bob,
			Eve eve) throws Exception;
	
	public abstract String getName();
}
