package Arrays;

import java.util.*;
/**
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