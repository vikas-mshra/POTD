package DP.Recursion;

import java.util.Arrays;

/**
    Last Attempt: 14 Feb 2026
    LeetCode Link: https://leetcode.com/problems/champagne-tower
    Approach: Start from the row and column from which you want to find, and there are only two ways, a glass can receive some champagne is from upper_left and upper_right glass. we would recursively solve it.
    Time(n): O(n) {n: height of the traingle query_row}
    Space(n): O(1)
*/
class Solution {

    public double champagneTower(int poured, int query_row, int query_glass) {
        // dp[i][j] = total champagne reaching glass (i, j)
        double[][] dp = new double[100][100];

        // mark all cells as uncomputed
        for (double[] row : dp)
            Arrays.fill(row, -1.0);

        // a glass can hold at most 1 cup
        return Math.min(1.0, helper(query_row, query_glass, poured, dp));
    }

    private double helper(int i, int j, double poured, double[][] dp) {

        // invalid position in triangle (i < j is invalid because a row i can't have more than i columns)
        if (i < 0 || j < 0 || i < j)
            return 0;

        // top glass
        if (i == 0 && j == 0)
            return poured;

        // return cached value
        if (dp[i][j] != -1.0)
            return dp[i][j];

        // overflow from upper-left glass
        double upLeft = (helper(i - 1, j - 1, poured, dp) - 1) / 2;

        // overflow from upper-right glass
        double upRight = (helper(i - 1, j, poured, dp) - 1) /  2;

        // only positive overflow contributes
        if (upLeft < 0) upLeft = 0;
        if (upRight < 0) upRight = 0;

        return dp[i][j] = upLeft + upRight;
    }
}
