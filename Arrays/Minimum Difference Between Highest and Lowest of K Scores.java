package Arrays;

import java.util.*;
/**
    Last Attempt: 25 Jan 2026
    LeetCode Link: https://leetcode.com/problems/minimum-difference-between-highest-and-lowest-of-k-scores/description
    Approach: Sort the array and for a range of k element find the diff between min and max of that sub array 
    Time(n): O(n)
    Space(n): O(1)
*/
class Solution {
    public int minimumDifference(int[] nums, int k) {
        if (k == 1)
            return 0;
        int n = nums.length - 1;
        k--; // to adjust k for 0 index array
        Arrays.sort(nums);
        int min = Integer.MAX_VALUE;

        for (int i = n; i >= k; i--) {
            min = Math.min(min, nums[i] - nums[i - k]);
        }

        return min;
    }
}

/* PYTHON CODE:

class Solution:
    def minimumDifference(self, nums: List[int], k: int) -> int:
        if k == 1:
            return 0
        
        n = len(nums) - 1
        k -= 1 # to adjust for the array starting with 0

        nums.sort()
        min_diff = float('inf')
        for i in range(n, k - 1, -1):
            min_diff = min(min_diff, nums[i] - nums[i - k])
        return min_diff
*/