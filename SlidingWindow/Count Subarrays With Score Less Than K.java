package SlidingWindow;

/**
    Last Attempt: 07 Feb 2026
    LeetCode Link: https://leetcode.com/problems/count-subarrays-with-score-less-than-k/
    Approach: Use two pointers (`lPtr` and `rPtr`) to represent a sliding window on the array.
        - Expand the window by moving `rPtr` and adding the current element to the `prefixSum`.
        - If the product of `prefixSum` and the window length (`len`) is >= k, move `lPtr` to shrink the window until it's valid.
        - For each `rPtr`, count how many subarrays (ending at `rPtr`) are valid.
    Time(n): O(n)
    Space(n): O(1)
*/

class Solution {
    public long countSubarrays(int[] nums, long k) {
        long validCount = 0;
        int n = nums.length;

        int lPtr = 0;
        long prefixSum = 0;

        // Iterate through the array using the right pointer
        for (int rPtr = 0; rPtr < n; rPtr++) {
            int len = rPtr - lPtr + 1;
            prefixSum += nums[rPtr];

            // Shrink the window if the product of prefixSum and len is >= k
            while (lPtr <= rPtr && prefixSum * len >= k) {
                prefixSum -= nums[lPtr];
                len--;
                lPtr++;
            }

            // Add the count of valid subarrays ending at rPtr
            validCount += len;
        }

        return validCount;
    }
}