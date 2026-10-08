package Quantum;

public class Qubit {

	/*
	 * Attributes
	 */
	
	private int bit;
	private Basis basis;
	
	
	/*
	 * Constructors
	 */
	
	public Qubit(int _bit, Basis _basis)
	{
		assert _bit == 0 || _bit == 1;
		
		this.setBasis(_basis);
		this.setBit(_bit);
	}

	/*
	 * Getters and Setters
	 */

	public int getBit() {
		return bit;
	}


	public void setBit(int bit) {
		this.bit = bit;
	}


	public Basis getBasis() {
		return basis;
	}


	public void setBasis(Basis basis) {
		this.basis = basis;
	}
	
	/*
	 * Methods
	 */
	
	/**
	 * If the qubit is measured with the same basis it is polarized in,
	 * then you get its polarization with 100% chance. 
	 * If not, then you get either polarization states with 50% chance.
	 * 
	 * @param qubit: Desired qubit to get measured
	 * @param measurementBasis: The chosen basis to measure the qubit with.
	 * @return the measurement output.
	 */
	public static int Mesure(Qubit qubit, Basis measurementBasis) {
		
		if(qubit.getBasis() == measurementBasis)
		{
			return qubit.getBit();
		}
		
		return (int)(Math.random() * 2); // Returns 0 or 1 with the same probability.
 	}
	
	@Override
	public String toString()
	{
		return "<" + String.valueOf(this.getBit()) + "|" + String.valueOf(this.getBasis()) + ">";
	}
}
