package com.cicd.pipeline;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class MathUtilsTest {

	@Test
	void testAdd() {
        MathUtils mathUtils = new MathUtils();
        assertEquals(5, mathUtils.add(2, 3));
        assertEquals(-1, mathUtils.add(-2, 1));
    }

    @Test
    void testSubtract() {
        MathUtils mathUtils = new MathUtils();
        assertEquals(1, mathUtils.subtract(3, 2));
        assertEquals(-3, mathUtils.subtract(-2, 1));
    }

    @Test
    void testMultiply() {
        MathUtils mathUtils = new MathUtils();
        assertEquals(6, mathUtils.multiply(2, 3));
        assertEquals(0, mathUtils.multiply(0, 5));
    }

    @Test
    void testDivide() {
        MathUtils mathUtils = new MathUtils();
        assertEquals(2.0, mathUtils.divide(6, 3));
        assertEquals(-1.0, mathUtils.divide(5, 0));
    }
}
