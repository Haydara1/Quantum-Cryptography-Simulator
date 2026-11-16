package Quantum;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class CommunicationTest {

    @Test
    void defaultConstructorInitializesEmptyName() {
        Communication communication = new Communication();

        assertEquals("", communication.getName());
    }

    @Test
    void constructorStoresChannelName() {
        Communication communication = new Communication("Quantum");

        assertEquals("Quantum", communication.getName());
    }

    @Test
    void storesLastCommunicatedObject() {
        Communication communication = new Communication("Public");

        String message = "Hello";

        communication.setLastCommunicatedObject(message);

        assertEquals(message, communication.getLastCommunicatedObject());
    }

    @Test
    void replacesPreviousMessage() {
        Communication communication = new Communication("Public");

        communication.setLastCommunicatedObject("First");
        communication.setLastCommunicatedObject("Second");

        assertEquals("Second", communication.getLastCommunicatedObject());
    }
}