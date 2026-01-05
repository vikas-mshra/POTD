package Arrays;

import java.util.*;

/**
 * Last Attempt: 04 Jan 2026
 * LeetCode Link: https://leetcode.com/problems/maximum-matrix-sum
 * Approach: If you have even number of -ve numbers or atleast one 0 then all
 * -ve can be convert to positive number else remove the smallest number
 * Time(n): O(n logn)
 * Space(n): O(n)
 */
class Solution {
    public long maxMatrixSum(int[][] matrix) {
        int size = matrix.length;

        List<Integer> negAbsValues = new ArrayList<>(); // Absolute values of negative elements
        List<Integer> nonNegativeValues = new ArrayList<>(); // Zero and positive elements

        boolean containsZero = false;
        long maxSum = 0;

        // Separate negatives (as absolute values) and non-negatives
        for (int r = 0; r < size; r++) {
            for (int c = 0; c < size; c++) {
                int value = matrix[r][c];
                if (value < 0) {
                    negAbsValues.add(Math.abs(value));
                } else {
                    if (value == 0)
                        containsZero = true;
                    nonNegativeValues.add(value);
                }
                maxSum += Math.abs(value);
            }
        }

        if (containsZero)
            return maxSum;

        maxSum = 0;

        Collections.sort(nonNegativeValues);
        Collections.sort(negAbsValues);

        // If negatives count is odd, one smallest value must stay negative
        boolean canFlipAll = negAbsValues.size() % 2 == 0;

        int posIndex = 0, negIndex = 0;
        if (!canFlipAll) {
            // Exclude the smallest absolute value from the final sum
            if (!nonNegativeValues.isEmpty() && nonNegativeValues.get(0) <= negAbsValues.get(0)) {
                maxSum -= nonNegativeValues.get(0);
                posIndex = 1;
            } else {
                maxSum -= negAbsValues.get(0);
                negIndex = 1;
            }
        }

        // Add remaining values
        while (posIndex < nonNegativeValues.size() && negIndex < negAbsValues.size()) {
            maxSum += nonNegativeValues.get(posIndex++);
            maxSum += negAbsValues.get(negIndex++);
        }

        while (posIndex < nonNegativeValues.size())
            maxSum += nonNegativeValues.get(posIndex++);

        while (negIndex < negAbsValues.size())
            maxSum += negAbsValues.get(negIndex++);

        return maxSum;
    }
}
