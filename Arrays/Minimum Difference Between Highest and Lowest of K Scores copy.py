'''
    Approach: Sort the array and for a range of k element find the diff between min and max of that sub array 
    Time(n): O(n)
    Space(n): O(1)
'''
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