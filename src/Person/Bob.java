package Person;

import Quantum.Basis;

public class Bob extends Participant {

	/*
	 * Attributes
	 */
	private Basis[] basisSequence;
	
	/*
	 * Constructor
	 */
	
	public Bob() {
		super("Bob");
	}

	/*
	 * Getters and Setters
	 */
	
	public Basis[] getBasisSequence() {
		return basisSequence;
	}

	public void setBasisSequence(Basis[] basisSequence) {
		this.basisSequence = basisSequence;
	}
	
}
