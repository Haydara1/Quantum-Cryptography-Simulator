package Quantum;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

class QubitTest {

    @Test
    void measurementWithSameBasisReturnsOriginalBit() {

        Qubit qubit = new Qubit(1, Basis.PLUS);

        int result = Qubit.Measure(qubit, Basis.PLUS);

        assertEquals(1, result);
    }

    @Test
    void measurementWithSameBasisReturnsZero() {

        Qubit qubit = new Qubit(0, Basis.TIMES);

        int result = Qubit.Measure(qubit, Basis.TIMES);

        assertEquals(0, result);
    }

    @Test
    void measurementWithDifferentBasisReturnsValidBit() {

        Qubit qubit = new Qubit(1, Basis.PLUS);

        int result = Qubit.Measure(qubit, Basis.TIMES);

        assertTrue(result == 0 || result == 1);
    }
}
