 package Person;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import Quantum.Communication;

class ParticipantTest {

    @Test
    void constructorStoresParticipantName() {
        Alice alice = new Alice();

        assertEquals("Alice", alice.getName());
    }

    @Test
    void toStringReturnsParticipantName() {
        Alice alice = new Alice();

        assertEquals("Alice", alice.toString());
    }

    @Test
    void broadcastSendsMessageThroughCommunicationChannel() {
        Alice alice = new Alice();
        Communication communication = new Communication("Public");

        String message = "Test message";

        alice.broadcast(communication, message);

        assertEquals(message, communication.getLastCommunicatedObject());
    }

    @Test
    void broadcastPreservesObjectReference() {
        Alice alice = new Alice();
        Communication communication = new Communication("Quantum");

        Object message = new Object();

        alice.broadcast(communication, message);

        assertSame(message, communication.getLastCommunicatedObject());
    }
}