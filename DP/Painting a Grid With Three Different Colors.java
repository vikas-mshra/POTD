package DP;

import java.util.*;

/**
 * Last Attempt: 03 Jan 2026
 * LeetCode:
 * https://leetcode.com/problems/painting-a-grid-with-three-different-colors/description/
 * Approach:
 * 1: Generate all the possible string for a single column since the max size of
 * m would be 5
 * 2: Now pick one string from generated string and check it against all other
 * string recusively and once the grid is filled, add one to the total and
 * repeat it for all the strings
 * 3: You will encounter TLE, think how you can reuse the previously calculated
 * iterations
 * Time(n): Refer code
 * Space(n): O(S * n)
 */

class Solution {
    private static final int MOD = 1_000_000_007;
    private static final char[] colors = { 'r', 'b', 'g' };
    private List<String> validColumnStates = new ArrayList<>();
    private int[][] dp;

    public int colorTheGrid(int m, int n) {
        int result = 0;

        // Generate all valid column color states for m rows
        generateColumnStates(new StringBuilder(), m, ' ');

        dp = new int[validColumnStates.size()][n];

        // Initialize DP table with -1 (unvisited states)
        for (int[] row : dp) {
            Arrays.fill(row, -1);
        }

        // Iterate through all valid column states and compute the total number of ways
        for (int i = 0; i < validColumnStates.size(); i++) {
            result = (result + solve(i, n - 1)) % MOD;
        }

        return result;
    }

    // T.C: O(3 * 2^(m-1))
    // Generate all valid column color states for m rows
    private void generateColumnStates(StringBuilder sb, int m, char lastColor) {
        if (sb.length() == m) {
            validColumnStates.add(sb.toString());
            return;
        }

        int len = sb.length();

        // Try all possible colors (r, b, g) but avoid repeating the last color
        for (char currentColor : colors) {
            if (currentColor == lastColor)
                continue;

            sb.append(currentColor);
            generateColumnStates(sb, m, currentColor);
            sb.setLength(len);
        }
    }

    // T.C: O(n * S * (S * m)) {S: validColumnStates.size}
    // Calculate the number of valid ways to color remaining columns from a given
    // column state
    private int solve(int prevStateIndex, int remainingColumns) {
        if (remainingColumns == 0)
            return 1;

        if (dp[prevStateIndex][remainingColumns] != -1)
            return dp[prevStateIndex][remainingColumns];

        String prevState = validColumnStates.get(prevStateIndex);
        int ways = 0;

        // Check all valid next column states
        for (int i = 0; i < validColumnStates.size(); i++) {
            if (i == prevStateIndex)
                continue;
            if (isValid(prevState, validColumnStates.get(i)))
                ways = (ways + solve(i, remainingColumns - 1)) % MOD;
        }

        return dp[prevStateIndex][remainingColumns] = ways;
    }

    // Check if two column states are valid (no adjacent cells have the same color)
    private boolean isValid(String prevState, String nextState) {
        for (int i = 0; i < prevState.length(); i++) {
            if (prevState.charAt(i) == nextState.charAt(i))
                return false;
        }
        return true;
    }
}