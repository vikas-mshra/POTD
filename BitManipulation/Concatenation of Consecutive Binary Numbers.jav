package BitManipulation;

/**
    Last Attempt: 28 Feb 2026
    LeetCode Link: https://leetcode.com/problems/concatenation-of-consecutive-binary-numbers/
    Approach: Iterate through the numbers from 1 to n and calculate the number of bits required to represent the number in binary and then use that to shift the number by the number of bits to add i
    Time(n): O(n)
    Space(n): O(1)
*/
class Solution {
    private final int MOD = 1_000_000_007;

    public int concatenatedBinary(int n) {
        long result = 0;
        for (int i = 1; i <= n; i++) {
            int digits = bitsRequired(i);
            // shift the number by digits to add i
            result = ((result << digits) + i) % MOD;
        }
        return (int) result;
    }

    // calculate the number of bits required to represent a number in binary
    public static int bitsRequired(int n) {
        return Integer.SIZE - Integer.numberOfLeadingZeros(n);
    }
}