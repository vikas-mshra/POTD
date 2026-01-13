package BinarySearch;
/**
    Comments: Learn from the code, how to handle floating point numbers when comparing them.

    Last Attempt: 12 Jan 2026
    LeetCode Link: https://leetcode.com/problems/separate-squares-i/description/
    Approach: Since the area difference changes smoothly as the line moves up or down, we use binary search on the y-axis to find the balance point. The EPSILON (tolerance) value is necessary because we are working with floating-point numbers. Instead of checking for an exact equality (which is unreliable with doubles), we stop the binary search when the search range becomes very small, meaning we are "close enough" to the correct answer.
    Time(n): O(n * log(y)) {n: number Of Squares, y: the Range Of Possible Y Values}
    Space(n): O(1)
*/
class Solution {

    private static final double EPSILON = 1e-5;

    public double separateSquares(int[][] squares) {
        double lowY = Double.MAX_VALUE;
        double highY = Double.MIN_VALUE;

        // Determine the vertical search range
        for (int[] square : squares) {
            lowY = Math.min(lowY, square[1]);
            highY = Math.max(highY, square[1] + square[2]);
        }

        // Binary search for the y-coordinate that balances the areas
        while (highY - lowY > EPSILON) {
            double midY = lowY + (highY - lowY) / 2.0;

            int comparison = compareAreasAtY(midY, squares);
            if (comparison == 1) {
                highY = midY;
            } else {
                lowY = midY;
            }
        }

        return lowY;
    }

    private int compareAreasAtY(double yLine, int[][] squares) {
        double areaAbove = 0.0;
        double areaBelow = 0.0;

        for (int[] square : squares) {
            double sideLength = square[2];
            double squareBottomY = square[1];
            double squareTopY = square[1] + sideLength;

            if (squareTopY <= yLine) { // Entire square is below the line
                areaBelow += sideLength * sideLength;
            } else if (squareBottomY >= yLine) { // Entire square is above the line
                areaAbove += sideLength * sideLength;
            } else { // Square is split by the line
                areaBelow += sideLength * (yLine - squareBottomY);
                areaAbove += sideLength * (squareTopY - yLine);
            }
        }

        return areaBelow >= areaAbove ? 1 : 2;
    }
}
