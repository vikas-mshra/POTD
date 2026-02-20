package DP.Recursion;

import java.util.*;

/**
    Last Attempt: 19 Feb 2026
    Explanation: A special binary string is defined such that it contains an equal number of 1s and 0s, and at every prefix of the string, the number of 1s is greater than or equal to the number of 0s. Because of this prefix condition, the string cannot start with 0, since that would immediately violate the rule (0 would make the count of 0s greater than 1s at the first character). Therefore, it must start with 1. Similarly, since the total number of 1s and 0s must be equal, and we can never have more 0s than 1s in any prefix, the final character must be the balancing 0 that brings the count back to zero. If it ended with 1, the counts would not be equal. Hence, every valid special binary string always starts with 1 and ends with 0.
    LeetCode Link: https://leetcode.com/problems/special-binary-string/
    Approach:
        - Traverse the string and split it into balanced special substrings (where the number of 1s equals the number of 0s).
        - For each balanced substring, recursively process its inner portion (excluding the outer 1 and 0) to make it lexicographically largest.
        - Collect all processed substrings, sort them in descending lexicographical order, concatenate them, and return the result.
    
    Time(n): O(n^2)
    Space(n): O(n) {recursive stack}
*/
class Solution {
    public String makeLargestSpecial(String s) {
        if (s.length() <= 2)
            return s;
        return buildLargestSpecial(s, 0, s.length() - 1);
    }

    private String buildLargestSpecial(String s, int left, int right) {
        if (right < left)
            return "";

        int balance = 0; // Tracks difference between '1's and '0's
        int segmentStart = left; // Start index of current special substring
        List<String> specialSegments = new ArrayList<>();

        while (left <= right) {
            balance += s.charAt(left) == '1' ? 1 : -1;

            // When balance becomes 0, we found a valid special substring
            if (balance == 0) {
                StringBuilder segment = new StringBuilder();
                segment.append(s.charAt(segmentStart)); // Leading '1'
                segment.append(buildLargestSpecial(s, segmentStart + 1, left - 1)); // Recursively optimize inner part
                segment.append(s.charAt(left)); // Trailing '0'

                specialSegments.add(segment.toString());
                segmentStart = left + 1; // Move to next segment
            }

            left++;
        }

        // Sort segments in descending lexicographical order
        Collections.sort(specialSegments, (a, b) -> b.compareTo(a));

        StringBuilder result = new StringBuilder();
        for (String segment : specialSegments) {
            result.append(segment);
        }

        return result.toString();
    }
}