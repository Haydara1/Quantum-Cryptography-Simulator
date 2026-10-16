package Protocols;

/*
 * Source:
 * https://fr.wikipedia.org/wiki/Protocole_BB84
 */

import Quantum.Basis;
import Quantum.Qubit;
import Quantum.Util;

import Person.Alice;
import Person.Bob;
import Person.Eve;

import Quantum.Communication;

public class BB84 extends Protocol{

	/*
	 * Attributes
	 */
	
	private int qbitsNum;
	
	// The percentage of error allowed in punctured key verification.
	private double errorMargin;
	
	/*
	 * Getters and Setters
	 */
	
	public int getQbitsNum() {
		return qbitsNum;
	}


	public void setQbitsNum(int qbitsNum) {
		this.qbitsNum = qbitsNum;
	}


	/*
	 * Constructors:
	 */
	
	public BB84()
	{
		this.setQbitsNum(20); // Set 20 qbits as standard.
		
		this.errorMargin = 0.11;
	}
	
	/**
	 * Constructor of the class
	 * @param _qbits: the number of qbits to start the protocol with.
	 */
	public BB84(int _qbits) {
		this.setQbitsNum(_qbits);
		
		this.errorMargin = 0.11;
	}
	
	
	public BB84(int _qbits, double _errorMargin) {
		this.setQbitsNum(_qbits);
		
		assert _errorMargin <= 1 && _errorMargin >= 0;
		this.errorMargin = _errorMargin;
	}
	/*
	 * Public Methods
	 */
	
	/**
	 * Gets the name of the used protocol.
	 */
	@Override
	public String getName()
	{
		return "BB84";
	}
	
	/**
	 * Ideal protocol simulation.
	 * To be ignored.
	 */
	
	@Override
	public void startProtocol()
	{
		// Alice starts by selecting a random sequence of qubits.
		Qubit[] senderSeq = getRandomQubitSeq(this.getQbitsNum());
		
		Util.printSeq(senderSeq);
		
		// Bob creates a random sequence of measurement bases:
		Basis[] receiverSeq = getRandomBasisSeq(this.getQbitsNum());
		
		Util.printSeq(receiverSeq);
		
		// Bob measures Alice's qubit using his bases:
		int[] measuredBits = new int[this.getQbitsNum()];
		
		for(int i = 0; i < this.getQbitsNum(); i++)
		{
			measuredBits[i] = Qubit.Measure(senderSeq[i], receiverSeq[i]);
		}
		
		Util.printSeq(measuredBits);
		
		// Then, Bob sends his basis sequence and Alice tells him which ones are the correct ones.
		
		boolean[] correctBasis = new boolean[this.getQbitsNum()];
		
		for(int i = 0; i < this.getQbitsNum(); i++)
		{
			correctBasis[i] = senderSeq[i].getBasis() == receiverSeq[i];
		}
		
		// Alice checks that Bob's correct bits match the ones from the original sequence.
		// They only compare a subsequence that they have chosen initially, generally,
		// the first half of the sequence.
		
		int bitError = 0; 
		
		// A lot of bits are lost, since we divide the sequence into two, 
		// and not the correct bits sequence.
		for(int i = 0; i < (int)(this.getQbitsNum() / 2); i++)
		{
			if(correctBasis[i] && measuredBits[i] != senderSeq[i].getBit())
			{
				bitError += 1;
			}
		}
		
		// Here we should compare the bitError to detect spies
		
		// The private key will be the rest of the correct subsequence.
		
		String key = "";
		
		for(int i = (int)(this.getQbitsNum() / 2); i < this.getQbitsNum(); i++)
		{
			if(correctBasis[i])
				key += String.valueOf(measuredBits[i]);
		}
		
		
		System.out.println("Number of errors: " + String.valueOf(bitError)); // Should be zero.
		System.out.println("Private key: " + key);
		
 	}
	
	/*
	 * The main difference between this function and the one before it, is that participants
	 * can only communicate through channels, which simulates real-world applications.
	 */
	@Override
	public void startProtocol(Alice alice, Bob bob, Eve eve) throws Exception {
		
		/*
		 * Initialize:
		 */
		
		// This channel should be authenticated
		Communication publicChannel = new Communication("Public");
		Communication quantumChannel = new Communication("Quantum");
		
		
		// Initialize Alice's random qubit (bit and basis) sequence.
		alice.setQubitSequence(getRandomQubitSeq(this.getQbitsNum()));
		
		// Initialize Bob's random basis sequence.
		bob.setBasisSequence(getRandomBasisSeq(this.getQbitsNum()));
		
		// Alice broadcasts its sequence in the public channel so Bob can measure it.
		alice.broadcast(quantumChannel, alice.getQubitSequence());
		
		//Util.printSeq((Qubit[])quantumChannel.getLastCommunicatedObject());
		
		// Interception can happen here.
		eve.intercept(quantumChannel);
		
		//Util.printSeq((Qubit[])quantumChannel.getLastCommunicatedObject());
		
		/*
		 * Measurement:
		 */
		
		// Bob measures the Qubit Sequence with his basis sequence.
		
		Qubit[] receivedSequence;
		
		// Check if the last communicated item is a Qubit[]
		
		if(quantumChannel.getLastCommunicatedObject() instanceof Qubit[])
		{
			receivedSequence = (Qubit[])quantumChannel.getLastCommunicatedObject();
		}
		else {
			throw new Exception("Error in transmission, expected a Qubit sequence.");
		}
		
		int[] measuredBits = new int[this.getQbitsNum()];
		
		for(int i = 0; i < this.getQbitsNum(); i++)
		{
			measuredBits[i] = Qubit.Measure(receivedSequence[i], bob.getBasisSequence()[i]);
		}
		
		/*
		 * Establish punctured key.
		 */
		
		// Bob sends to Alice the bases 
		bob.broadcast(publicChannel, bob.getBasisSequence());
		
		// Alice compares bob's bases and hers to send him the correct chosen bases:
		boolean[] correctBasis = getCorrectBasisSeq(alice.getQubitSequence(),
				(Basis[])publicChannel.getLastCommunicatedObject());
		
		// Alice sends to bob the correct basis sequence: 
		alice.broadcast(publicChannel, correctBasis);
		
		// Bob compares his measured bit sequence (only for correct indices)
		// and then should compare it with errorMargin to detect spies.
		
		/*
		 * Begin Comparison
		 */
		
		double bitError = 0;
		
		correctBasis = (boolean[]) publicChannel.getLastCommunicatedObject();
		
		int semiCorrectBasisNum = getCorrectBasisNum(
				correctBasis) / 2;
		
		int index = 0, correctBasisIndex = semiCorrectBasisNum;
		
		while(correctBasisIndex > 0) {
			// Case where bob guessed a correct basis.
			if(correctBasis[index]) {
				
				// Case where the measured bit doesn't match the theoretical bit.
				if(alice.getQubitSequence()[index].getBit() != measuredBits[index])
				{
					bitError += 1;
				}
				
				correctBasisIndex -= 1;
			}
			
			index += 1;
		}
		
		// Compare bitError to MarginError to detect spies:
		bitError = bitError / semiCorrectBasisNum;
		
		if(bitError > this.errorMargin)
		{
			System.out.println("Possibility of a spy was detected, key is discarded.");
			System.out.println("Algorithm has failed.");
			System.out.println("Error rate: " + String.valueOf(bitError));
		}
		else {
		
			/*
			 * Establish private key
			 * If no spies are detected, the private key will be the rest of the correct bit sequence
			 * (the ones that were not used in the punctured key).
			 */
			System.out.println("Algorithm success, no spies were detected!");
			
			StringBuilder privateKey = new StringBuilder("Private key: ");
			
			for(int i = index; i < measuredBits.length; i++) {
				
				if(correctBasis[i])
					privateKey.append(String.valueOf(measuredBits[i]));
			}
			
			// End of the algorithm.
			System.out.println(privateKey);
			System.out.println("Error rate: " + String.valueOf(bitError));
		}
		/*
		 * End comparisons
		 */
		
	}
	
	
	/*
	 * Private Methods
	 */
	
	/**
	 * Returns a random sequence of qubits.
	 * @param qubitNum: number of qubits in the sequence.
	 * @return
	 */
	private static Qubit[] getRandomQubitSeq(int qubitNum) {
		
		Qubit[] seq = new Qubit[qubitNum];
		
		for(int i = 0; i < qubitNum; i++)
		{
			int bit = (int)(Math.random() * 2); // returns 0 or 1
			
			Basis basis; // Assign a random basis.
			
			if(Math.random() < 0.5) {
				basis = Basis.PLUS;
			}
			else {
				basis= Basis.TIMES;
			}
			
			seq[i] = new Qubit(bit, basis);
 		}
		
		return seq;
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
	
	/**
	 * Compares a basis sequence with a qubit sequence.
	 * @param qubitSeq: the qubit sequence
	 * @param basisSeq: the basis sequence
	 * @return a boolean sequence where each element at an index i, shows if the two bases in that index match.
	 */
	private static boolean[] getCorrectBasisSeq(Qubit[] qubitSeq, Basis[] basisSeq) {
		boolean[] seq = new boolean[qubitSeq.length];
		
		for(int i = 0; i < seq.length; i++)
			seq[i] = qubitSeq[i].getBasis() == basisSeq[i];
		
		return seq;
	}
	
	
	/**
	 * Returns the number of correct bases in a sequence.
	 * @param basisSeq a boolean sequence that represents the correct basis.
	 * @return the number of True elements in the above sequence.
	 */
	private static int getCorrectBasisNum(boolean[] basisSeq) {
		int output = 0;
		
		for(int i = 0; i < basisSeq.length; i++)
		{
			if(basisSeq[i]) {
				output += 1;
			}
		}
		
		return output;
	}
}
