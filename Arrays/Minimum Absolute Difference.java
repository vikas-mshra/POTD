package Arrays;

import java.util.*;

/**
    Last Attempt: 25 Jan 2026
    LeetCode Link: https://leetcode.com/problems/minimum-absolute-difference/
    Approach: Sort the array and find the minimum absolute difference between consecutive elements.
    Time(n): O(n logn)
    Space(n): O(n)
*/
class Solution {
    public List<List<Integer>> minimumAbsDifference(int[] nums) {
        Arrays.sort(nums);

        // find min abs difference
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < nums.length - 1; i++) {
            min = Math.min(min, Math.abs(nums[i + 1] - nums[i]));
        }

        List<List<Integer>> res = new ArrayList<>();
        for (int i = 0; i < nums.length - 1; i++) {
            if (Math.abs(nums[i + 1] - nums[i]) == min)
                res.add(Arrays.asList(nums[i], nums[i + 1]));
        }

        return res;
    }
}

/* PYTHON CODE:

class Solution:
    def minimumAbsDifference(self, arr: List[int]) -> List[List[int]]:
        arr.sort()

        min_diff = float('inf')
        for i in range(0, len(arr) - 1, 1):
            min_diff = min(min_diff, abs(arr[i + 1] - arr[i]))
        
        res = []
        for i in range(0, len(arr) - 1, 1):
            if abs((arr[i + 1] - arr[i]) == min_diff):
                res.append([arr[i], arr[i + 1]])
        
        return res

*/