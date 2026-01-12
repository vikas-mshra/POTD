package Math;

/**
    Comments: There is an approach in the code to find all the factors of a number in O(sqrt(n)) time.


    Last Attempt: 11 Jan 2026
    LeetCode Link: https://leetcode.com/problems/sum-of-four-divisors/
    Approach: iterate the array and find all possible factors of the num, if it satisy the condition, add it to the total
    Time(n): O(n * sqrt(m)) {m: maximum number in nums}
    Space(n): O(1)
*/
class Solution {
    public int sumFourDivisors(int[] nums) {
        int totalSum = 0;

        for (int num : nums) {
            totalSum += sumIfFourDivisors(num);
        }

        return totalSum;
    }

    // Returns the sum of divisors if num has exactly four divisors, otherwise 0
    private int sumIfFourDivisors(int num) {
        int divisorCount = 0;
        int divisorSum = 0;
        int limit = (int) Math.sqrt(num);

        for (int d = 1; d <= limit; d++) {
            if (num % d == 0) {
                int pairedDivisor = num / d;

                divisorSum += d;
                divisorCount++;

                // Count the paired divisor if it's different
                if (pairedDivisor != d) {
                    divisorSum += pairedDivisor;
                    divisorCount++;
                }

                // More than four divisors means this number is invalid
                if (divisorCount > 4)
                    return 0;
            }
        }

        return divisorCount == 4 ? divisorSum : 0;
    }
}