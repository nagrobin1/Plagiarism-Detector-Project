package com.msd.team33.models.comparison.testDataFiles.set00.Student1;

import java.util.Scanner;

/**
 * Class to add two numbers.
 */
class AddNumbers {
    public static void main(String args[]) {
        int x, y, z;
        System.out.println("Enter two integers to calculate their sum ");
        Scanner in = new Scanner(System.in);
        x = in.nextInt();
        y = in.nextInt();
        // Add the two numbers
        z = x + y;
        System.out.println("Sum of entered integers = " + z);
    }
}