package Quantum;

public class Util {

	public static void printSeq(int[] seq)
	{
		System.out.print("Integer sequence: [");
		
		for(int i = 0; i < seq.length; i++)
			System.out.print(String.valueOf(seq[i]) + ", ");
		
		System.out.println("]");
	}
	
	public static void printSeq(Qubit[] seq)
	{
		System.out.print("Qubit sequence: [");
		
		for(int i = 0; i < seq.length; i++)
			System.out.print(String.valueOf(seq[i]) + ", ");
		
		System.out.println("]");
	}
	
	public static void printSeq(Basis[] seq)
	{
		System.out.print("Basis sequence: [");
		
		for(int i = 0; i < seq.length; i++)
			System.out.print(String.valueOf(seq[i]) + ", ");
		
		System.out.println("]");
	}
}
