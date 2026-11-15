package Person;

import Quantum.Qubit;

// Sender

public class Alice extends Participant {

	/*
	 * Attributes
	 */
	
	// This sequence is used for the BB84 protocol.
	private Qubit[] qubitSequence;
	
	/*
	 * Constructor
	 */
	
	public Alice() {
		super("Alice");
	}
	
	/*
	 * Getters and Setters
	 */

	public Qubit[] getQubitSequence() {
		return qubitSequence;
	}

	public void setQubitSequence(Qubit[] qubitSequence) {
		this.qubitSequence = qubitSequence;
	}
	
	
	
	
}
