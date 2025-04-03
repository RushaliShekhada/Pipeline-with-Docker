package com.cicd.pipeline;

public class MathUtils {

	public int add(int a, int b) {
        return a + b;
    }

    public int subtract(int a, int b) {
        return a - b;
    }

    public int multiply(int a, int b) {
        return a * b;
    }

    public double divide(int a, int b) {
        if (b == 0) {
            return -1.0;
        }
        return (double) a / b;
    }
    
    public static void main(String[] args) {
        MathUtils mathUtils = new MathUtils();
        System.out.println("Addition (2 + 3): " + mathUtils.add(2, 3));
        System.out.println("Subtraction (3 - 2): " + mathUtils.subtract(3, 2));
        System.out.println("Multiplication (2 * 3): " + mathUtils.multiply(2, 3));
        System.out.println("Division (6 / 3): " + mathUtils.divide(6, 3));
    }
}
