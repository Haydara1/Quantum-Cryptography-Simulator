package Person;

import Quantum.Basis;
import Quantum.Communication;
import Quantum.Qubit;

public class Eve extends Participant{

	/*
	 * Attributes
	 */
	private double interceptProbability;
	
	/*
	 * Constructors: 
	 */
	
	
	/*
	 * Getters and setters
	 */
	
	public double getInterceptProbability() {
		return interceptProbability;
	}

	public void setInterceptProbability(double interceptProbability) {
		this.interceptProbability = interceptProbability;
	}

	public Eve() {
		super("Eve");
		
		setInterceptProbability(Math.random());
	}
	
	public Eve(double _interceptProbability) {
		super("Eve");
		
		this.setInterceptProbability(_interceptProbability);
	}
	
	/*
	 * Public methods:
	 */
	
	public void intercept(Communication communication)
	{
		
		Object communatedObject = communication.getLastCommunicatedObject();
		
		if( communication.getLastCommunicatedObject() instanceof Qubit[]) {
			this.intercept((Qubit[]) communatedObject, communication);
		}
		
		return;
	}
	
	/*
	 * Private methods:
	 */
	
	private void intercept(Qubit[] qubitSequence, Communication communication) 
	{
		
		
		Basis[] randomChosenBasis = getRandomBasisSeq(qubitSequence.length);
		Qubit[] output = new Qubit[qubitSequence.length];
		
		for(int i = 0; i < qubitSequence.length; i++) {
			
			// Eve doesn't intercept all the qubits.
			if(Math.random() <= this.getInterceptProbability()) {
				int bit = Qubit.Measure(qubitSequence[i], randomChosenBasis[i]);
				
				output[i] = new Qubit(bit, randomChosenBasis[i]);
			}
			else 
			{
				output[i] = qubitSequence[i];
			}
		}
		
		this.broadcast(communication, output);
	}
	
	/**
	 * Returns a random sequence of measurement basis
	 * @param qubitNum; the desired number of bases.
	 * @return
	 */
	private static Basis[] getRandomBasisSeq(int qubitNum) {
		
		Basis[] seq = new Basis[qubitNum];
		
		for(int i = 0; i < qubitNum; i++)
		{
			Basis basis; // Assign a random basis.
			
			if(Math.random() < 0.5) {
				basis = Basis.PLUS;
			}
			else {
				basis= Basis.TIMES;
			}
			
			seq[i] = basis;
		}
		
		return seq;
	}
}
