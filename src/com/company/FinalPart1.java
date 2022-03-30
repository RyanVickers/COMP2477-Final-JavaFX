package com.company;

import java.util.Arrays;
import java.util.Scanner;

public class FinalPart1 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        //Getting rows and columns
        System.out.print("Enter Number of Rows: ");
        int rows = Integer.parseInt(scanner.nextLine());
        System.out.print("Enter Number of Columns: ");
        int columns = Integer.parseInt(scanner.nextLine());
        //array
        int[][] values = new int[rows][columns];
        System.out.println("Separate each Number in Row by a Space");
        //Getting each row of numbers in array
        for (int i = 0; i < rows; i++) {
            System.out.print("Enter Row " + (i + 1) + ": ");
            String row = scanner.nextLine();
            //splitting each row into individual numbers
            String[] numbers = row.split(" ");
            //putting number into array
            for (int j = 0; j < columns && j < numbers.length; j++) {
                values[i][j] = Integer.parseInt(numbers[j]);
            }
        }
        //printing out array
        System.out.println("\nThe Array is: ");
        for (int i = 0; i < rows; i++) {
            System.out.println(Arrays.toString(values[i]));
        }
        //calling isConsecutiveFour method
        System.out.println("Does the Array Contain Four Consecutive Numbers of the Same Value: " + isConsecutiveFour(values));
        scanner.close();
    }

    public static boolean isConsecutiveFour(int[][] values) {
        //row and column values
        int rows = values.length;
        int columns = values[0].length;

        //Checking Horizontally
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns - 3; j++) {
                if (values[i][j] == values[i][j + 1] &&
                        values[i][j] == values[i][j + 2] &&
                        values[i][j] == values[i][j + 3])
                    return true;
            }
        }

        //Checking vertical
        for (int i = 0; i < rows - 3; i++) {
            for (int j = 0; j < columns; j++) {
                if (values[i][j] == values[i + 1][j] &&
                        values[i][j] == values[i + 2][j] &&
                        values[i][j] == values[i + 3][j])
                    return true;
            }
        }

        //Checking diagonal top left to right
        for (int i = 0; i < rows - 3; i++) {
            for (int j = 0; j < columns - 3; j++) {
                if (values[i][j] == values[i + 1][j + 1] &&
                        values[i][j] == values[i + 2][j + 2] &&
                        values[i][j] == values[i + 3][j + 3])
                    return true;
            }
        }

        //checking diagonal top right to left
        for (int i = 0; i < rows - 3; i++) {
            for (int j = columns - 3; j < columns; j++) {
                if (values[i][j] == values[i + 1][j - 1] &&
                        values[i][j] == values[i + 2][j - 2] &&
                        values[i][j] == values[i + 3][j - 3])
                    return true;
            }
        }
        return false;
    }
}
