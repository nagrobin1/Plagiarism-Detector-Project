package com.msd.team33.models.comparison.testDataFiles.set00.Student2;

import java.util.Scanner;

/**
 * Class to add two numbers.
 */
class AddNumbers2 {
    public static void main(String args[]) {
        System.out.println("This program adds two numbers ");
        int a, y, z;
        System.out.println("Enter two integers to calculate their sum ");
        Scanner in = new Scanner(System.in);
        a = in.nextInt();
        y = in.nextInt();
        z = a + y;
        // Print out the result
        System.out.println("Sum of entered integers = " + z);
    }
}