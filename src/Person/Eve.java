package Person;

import Quantum.Basis;
import Quantum.Communication;
import Quantum.Qubit;

public class Eve extends Participant{

	// TODO stock eve's basis sequence.
	
	/*
	 * Constructors: 
	 */
	
	public Eve() {
		super("Eve");
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
	
	// TODO eve doesn't intercept all the qubit, get a random number of qubits to be intercepted.
	private void intercept(Qubit[] qubitSequence, Communication communication) 
	{
		
		
		Basis[] randomChosenBasis = getRandomBasisSeq(qubitSequence.length);
		Qubit[] output = new Qubit[qubitSequence.length];
		
		for(int i = 0; i < qubitSequence.length; i++) {
			
			int bit = Qubit.Measure(qubitSequence[i], randomChosenBasis[i]);
			
			output[i] = new Qubit(bit, randomChosenBasis[i]);
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
