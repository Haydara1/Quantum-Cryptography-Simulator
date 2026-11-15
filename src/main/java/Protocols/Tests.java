package Protocols;

import Person.Alice;
import Person.Bob;
import Person.Eve;

import java.util.Scanner;

public class Tests {

	public static void main(String[] args) throws Exception {
		
		Scanner sc = new Scanner(System.in);
		
		System.out.print("Enter the number of the wanted testcases: ");
		int n = sc.nextInt();
		
		System.out.print("Enter the number of wanted qubits: ");
		int qubit = sc.nextInt();
		
		System.out.print("Enter the intercept rate : ");
		double interceptRate = sc.nextDouble();
		
		massTest(n, qubit, interceptRate);
		
		sc.close();
		
	}
	
	private static void massTest(int testCasesNum,
			int qubitNum, double interceptProbability) 
			throws Exception 
	{
		Alice alice = new Alice();
		Bob bob = new Bob();
		Eve eve = new Eve(interceptProbability);
		
		Protocol bb84 = new BB84(qubitNum);
		
		int success = 0;
		
		for(int i = 0; i < testCasesNum; i++) {
			
			if(bb84.startProtocol(alice, bob, eve) != null)
			{
				success++;
			}
			
		}
		
		System.out.println("The quantum key distribution has succeeded with a "
				+ String.valueOf(success * 100 / testCasesNum) + "% rate.");
	}

}
