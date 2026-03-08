package String;

import java.util.*;
/**
    Last Attempt: 7th March 2026
    Leetcode Link: https://leetcode.com/problems/find-unique-binary-string/description/
    Approach: Create a set to find a string in a constant time. Start from 0, convert 0 in binary and check if that binary is available in the set or not. Keep incrementing the number until you find a missing binary
    Time(n): 
    Space(n): O(n)
*/
class Solution {
    public String findDifferentBinaryString(String[] nums) {
        // check a string in constant time
        Set<String> set = new HashSet<>();
        for (String num : nums) {
            set.add(num);
        }
        
        // start from 0 and go until u find a missing binary
        int start = 0;
        StringBuilder sb = getBinary(start++, nums.length);
        while (set.contains(sb.toString())) {
            sb = getBinary(start++, nums.length);
        }
        return sb.toString();
    }

    private StringBuilder getBinary(int num, int n) {
        StringBuilder sb = new StringBuilder();
        while (num != 0) {
            sb.append(num & 1);
            num >>= 1;
        }

        while (sb.length() < n) {
            sb.append('0');
        }

        return sb.reverse();
    }

}