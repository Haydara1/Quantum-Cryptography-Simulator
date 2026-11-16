package Person;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import Quantum.Basis;
import Quantum.Communication;
import Quantum.Qubit;

class EveTest {

    @Test
    void zeroInterceptionProbabilityPreservesOriginalQubits() {
        Eve eve = new Eve(0.0);

        Communication communication = new Communication("Quantum");

        Qubit[] original = {
            new Qubit(0, Basis.PLUS),
            new Qubit(1, Basis.TIMES),
            new Qubit(0, Basis.TIMES)
        };

        communication.setLastCommunicatedObject(original);

        eve.intercept(communication);

        Qubit[] intercepted =
            (Qubit[]) communication.getLastCommunicatedObject();

        assertEquals(original.length, intercepted.length);

        for (int i = 0; i < original.length; i++) {
            assertSame(original[i], intercepted[i]);
        }
    }

    @Test
    void fullInterceptionCreatesNewQubitObjects() {
        Eve eve = new Eve(1.0);

        Communication communication = new Communication("Quantum");

        Qubit[] original = {
            new Qubit(0, Basis.PLUS),
            new Qubit(1, Basis.TIMES),
            new Qubit(0, Basis.TIMES)
        };

        communication.setLastCommunicatedObject(original);

        eve.intercept(communication);

        Qubit[] intercepted =
            (Qubit[]) communication.getLastCommunicatedObject();

        assertEquals(original.length, intercepted.length);

        for (int i = 0; i < original.length; i++) {
            assertNotNull(intercepted[i]);
            assertNotSame(original[i], intercepted[i]);
        }
    }

    @Test
    void interceptionPreservesSequenceLength() {
        Eve eve = new Eve(0.5);

        Communication communication = new Communication("Quantum");

        Qubit[] original = new Qubit[100];

        for (int i = 0; i < original.length; i++) {
            original[i] = new Qubit(i % 2, Basis.PLUS);
        }

        communication.setLastCommunicatedObject(original);

        eve.intercept(communication);

        Qubit[] intercepted =
            (Qubit[]) communication.getLastCommunicatedObject();

        assertEquals(original.length, intercepted.length);
    }
}