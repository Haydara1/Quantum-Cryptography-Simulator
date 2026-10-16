package Protocols;

import Person.Alice;
import Person.Bob;
import Person.Eve;

public class Tests {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub

		BB84 protocolBB84 = new BB84(100000);
		
		Alice alice = new Alice();
		Bob bob = new Bob();
		Eve eve = new Eve(0.445);
		
	
		protocolBB84.startProtocol(alice, bob, eve);
		
	}

}
