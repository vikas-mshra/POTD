package DP;

import java.util.*;

/**
 * Last Attempt: 02 Jan 2026
 * Leetcode Link:
 * https://leetcode.com/problems/number-of-ways-to-paint-n-3-grid/description/
 * 
 * Approach:
 * 1: Generate all the possible string for a single row
 * 2: Now pick one string from generated string and check it against all other
 * string recusively and once the grid is filled, add one to the total and
 * repeat it for all the strings
 * 3: You will encounter TLE, think how you can reuse the previously calculated
 * iterations
 * 
 * Time Complexity: O(6 * n * (6 * 3))
 * Space Complexity: O(12 * n) for memoization.
 */

class Solution {
    final int MOD = 1_000_000_007;
    final char[] COLORS = { 'R', 'Y', 'G' };

    int[][] memo;
    List<String> possibleStrings;

    public int numOfWays(int n) {
        possibleStrings = new ArrayList<>();
        generateValidRowConfigs(new StringBuilder());

        int possibleStringsCount = possibleStrings.size();
        memo = new int[possibleStringsCount][n];
        for (int[] row : memo)
            Arrays.fill(row, -1);

        int totalWays = 0;
        for (int i = 0; i < possibleStringsCount; i++) {
            totalWays = (totalWays + countWays(i, n - 1)) % MOD;
        }
        return totalWays;
    }

    // T.C(n): O(6 * n * (6 * 3)) {S: possibleStrings.size}
    private int countWays(int prevStateIndex, int rowsRemaining) {
        if (rowsRemaining == 0)
            return 1;

        if (memo[prevStateIndex][rowsRemaining] != -1)
            return memo[prevStateIndex][rowsRemaining];

        int ways = 0;
        String prevRow = possibleStrings.get(prevStateIndex);
        for (int currRowIndex = 0; currRowIndex < possibleStrings.size(); currRowIndex++) {
            if (currRowIndex == prevStateIndex)
                continue;

            String currRow = possibleStrings.get(currRowIndex);
            if (rowsAreCompatible(prevRow, currRow)) {
                ways = (ways + countWays(currRowIndex, rowsRemaining - 1)) % MOD;
            }
        }

        return memo[prevStateIndex][rowsRemaining] = ways;
    }

    private boolean rowsAreCompatible(String row1, String row2) {
        return row1.charAt(0) != row2.charAt(0) &&
                row1.charAt(1) != row2.charAt(1) &&
                row1.charAt(2) != row2.charAt(2);
    }

    // T.C: O(3 * 2 * 1)
    private void generateValidRowConfigs(StringBuilder string) {
        if (string.length() == 3) {
            possibleStrings.add(string.toString());
            return;
        }

        int len = string.length();
        for (char color : COLORS) {
            if (len == 0 || string.charAt(len - 1) != color) {
                string.append(color);
                generateValidRowConfigs(string);
                string.setLength(len); // backtrack
            }
        }
    }
}