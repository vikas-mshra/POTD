package DP.Recursion;

import java.util.Arrays;

/**
    Approach: Bottom Up: We have implemented the same logic that we have in the recursion logic in the previous submission
        - Fill the base case in the dp before start
        - Iterate the DP and fill based on what we had in the recursion logic
    Time(n): O(zero * one * limit)
    Space(n): O(zero * one)
*/
class Solution {
    private int MOD = 1000_000_007;

    public int numberOfStableArrays(int zero, int one, int limit) {
        // create a dp to memoize the repeating problems
        int[][][] dp = new int[zero + 1][one + 1][2];
        dp[0][0][0] = 1;
        dp[0][0][1] = 1;

        for (int zeroLeft = 0; zeroLeft <= zero; zeroLeft++) {
            for (int oneLeft = 0; oneLeft <= one; oneLeft++) {
                if (zeroLeft == 0 && oneLeft == 0)
                    continue;

                // fill the zero's
                int consecutive = Math.min(zeroLeft, limit);
                int result = 0;
                for (int i = 1; i <= consecutive; i++) {
                    result = (result + dp[zeroLeft - i][oneLeft][0]) % MOD;
                }
                dp[zeroLeft][oneLeft][1] = result;

                // fill the ones
                consecutive = Math.min(oneLeft, limit);
                result = 0;
                for (int i = 1; i <= consecutive; i++) {
                    result = (result + dp[zeroLeft][oneLeft - i][1]) % MOD;
                }
                dp[zeroLeft][oneLeft][0] = result;
            }
        }
        return (dp[zero][one][0] + dp[zero][one][1]) % MOD;
    }
}
/**
    Last Attempt: 9th March 2026
    LeetCode Link: https://leetcode.com/problems/find-all-possible-stable-binary-arrays-i/
    Approach: the limit is saying that we should not have more than limit (1 or 0) consecutively
        - Start building the possible string starting from 0
        - Inside the loop, append one 0 then reduce the count and recursively call helper and add 1 then 0 and so on.
        - The loop will continue min(limit, zero) times, and in second iteration and append two 0
        - Repeat above steps for 1 as well
    Time(n): O(zero * one * limit)
    Space(n): O(zero * one * 2)        
*/
class Solution {
    private int MOD = 1000_000_007;
    public int numberOfStableArrays(int zero, int one, int limit) {
        // create a dp to memoize the repeating problems
        int[][][] dp = new int[zero + 1][one + 1][2];
        for (int[][] row : dp)
            for (int[] temp: row)
                Arrays.fill(temp, -1);

        // start an iteration from 0 and 1
        int startWithZero = helper(zero, one, limit, true, dp);
        int startWithOne = helper(zero, one, limit, false, dp);
        return (startWithZero + startWithOne) % MOD;
    }

    private int helper(int zeroRem, int oneRem, int consecutive, boolean isLastWasZero, int[][][] dp) {
        if (zeroRem == 0 && oneRem == 0) {
            return 1; // all counts are exhausted
        }

        int value = isLastWasZero ? 0 : 1;
        if (dp[zeroRem][oneRem][value] != -1)
            return dp[zeroRem][oneRem][value];

        int result = 0;
        if (isLastWasZero) {
            // max consecutive we can take is the min of available or the limit
            int limit = Math.min(zeroRem, consecutive);
            
            // explore zero
            for (int i = 1; i <= limit; i++) {
                result = (result + helper(zeroRem - i, oneRem, consecutive, false, dp)) % MOD;
            }
        } else {
            // max consecutive we can take is the min of available or the limit
            int limit = Math.min(oneRem, consecutive);
            
            // explore one
            for (int i = 1; i <= limit; i++) {
                result = (result + helper(zeroRem, oneRem - i, consecutive, true, dp)) % MOD;
            }
        }
        return dp[zeroRem][oneRem][value] = result;
    }
}