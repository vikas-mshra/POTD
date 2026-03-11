package DP.BottomUp;

/**
    Last Attempt: 10 March 2026
    LeetCode Link: https://leetcode.com/problems/find-all-possible-stable-binary-arrays-ii
    Approach: Use recursion to build the solution then optimize it to use dp, then write the bottom up and after writing the bottom up logic. Try to find a pattern to reduce the complexity from O(limit) to O(1)
    Time(n): O(zero * one)
    Space(n): O(zero * one)
*/
class Solution {
    private int MOD = 1000_000_007;

    public int numberOfStableArrays(int zero, int one, int limit) {
        int[][][] dp = new int[zero + 1][one + 1][2]; // {zero count, one count, {1 : considering zero}

        // base case
        int min = Math.min(zero, limit);
        for (int i = 0; i <= min; i++)
            dp[i][0][1] = 1; // if the one is 0 times then only one way to write 0 i.e., 000

        min = Math.min(one, limit);
        for (int i = 0; i <= min; i++)
            dp[0][i][0] = 1; // if the zero is 0 times then only one way to write 1 i.e., 111

        for (int i = 0; i <= zero; i++) {
            for (int j = 0; j <= one; j++) {

                if (i == 0 || j == 0)
                    continue;

                dp[i][j][1] = (dp[i - 1][j][0] + dp[i - 1][j][1]) % MOD;
                if (i - 1 >= limit) {
                    dp[i][j][1] = (dp[i][j][1] - dp[i - 1 - limit][j][0] + MOD) % MOD;
                }
                
                dp[i][j][0] = (dp[i][j - 1][1] + dp[i][j - 1][0]) % MOD;
                if (j - 1 >= limit) {
                    dp[i][j][0] = (dp[i][j][0] - dp[i][j - 1 - limit][1] + MOD) % MOD;
                }   
            }
        }
        return (dp[zero][one][0] + dp[zero][one][1]) % MOD;
    }
}