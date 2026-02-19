package DP.BottomUp;
/**
    Last Attempt: 17 Feb 2026
    LeetCode Link: https://leetcode.com/problems/palindromic-substrings/
    Approach: 4th
        - Expand Around Center
        - Start from each index and consider 1 element keep moving left and right until the string is valid and keep counting
        - Start from each index and consider 2 elements, keep moving left and right until the string is valid and keep counting
    Time(n): O(n^2)
    Space(n): O(1)
*/

class Solution {
    int count = 0;
    public int countSubstrings(String s) {
        int n = s.length();

        for (int i = 0; i < n; i++) {
            check(i, i, s); // consider the current element as the mid element
            check(i, i + 1, s); // consider the current and next element as the mid of substring
        }

        return count;
    }
    private void check(int start, int end, String s) {
        while (start >= 0 && end < s.length()) {
            if (s.charAt(start) == s.charAt(end)) {
                count++;
                start--;
                end++;
            } else {
                return;
            }
        }
    }
}

/**
    Approach: 3rd
        - Bottom Up
        - for each possible length (1 - n)
            - mark one length dp[i][j] = true
            - mark two length dp[i][j] = charAt(i) == charAt(i + 1)
            - else dp[i][j] = charAt(i) == charAt(j) && dp[i + 1][j - 1]          
*/
/**
class Solution {
    public int countSubstrings(String s) {
        int n = s.length();
        boolean[][] dp = new boolean[n][n];

        int count = 0;
        for (int len = 1; len <= n; len++) {
            for (int i = 0; i + len - 1 < n; i++) {
                int j = i + len - 1;
                if (i == j) { // 1 length string
                    dp[i][j] = true;
                } else if (i + 1 == j) { // 2 length string
                    dp[i][j] = s.charAt(i) == s.charAt(j);
                } else { // > 2 length string
                    dp[i][j] = s.charAt(i) == s.charAt(j) && dp[i + 1][j - 1];
                }

                if (dp[i][j])
                    count++;
            }
        }
        return count;
    }
}
*/

/**
   Approach: 2nd
    - NOTE: write the palindrome function using recursion
    - Generate all possible substring with two for-loops and for each combination, check if its palindrome or not.
    - Memoize it
   Time(n): O(n^3)
   Space(n): O(n^2)
*/
/**
class Solution {
    public int countSubstrings(String s) {
        int n = s.length();
        int[][] dp = new int[n][n]; // -1: not solved, 0: not palindrome, 1: palindrome
        for (int[] temp : dp)
            Arrays.fill(temp, -1);

        int count = 0;
        for (int i = 0; i < n; i++) {
            for (int j = i; j < n; j++) {
                if (isPalindrome(s, i, j, dp))
                    count++;
            }
        }
        return count;
    }

    private boolean isPalindrome(String s, int start, int end, int[][] dp) {
        if (start >= end)
            return true;

        if (dp[start][end] != -1)
            return dp[start][end] == 1;

        dp[start][end] = (s.charAt(start) == s.charAt(end) && isPalindrome(s, start + 1, end - 1, dp)) ? 1 : 0;

        return dp[start][end] == 1;
    }
}
*/

/**
   Approach: 1st
    - NOTE: write the palindrome function using recursion
    - Generate all possible substring with two for-loops and for each combination, check if its palindrome or not.
   Time(n): O(n^3)
   Space(n): O(n) {recursion depth}
*/
/**
class Solution {
    public int countSubstrings(String s) {
        int n = s.length();

        int count = 0;
        for (int i = 0; i < n; i++) {
            for (int j = i; j < n; j++) {
                if (isPalindrome(s, i, j))
                    count++;
            }
        }
        return count;
    }

    private boolean isPalindrome(String s, int start, int end) {
        if (start >= end)
            return true;

        return (s.charAt(start) == s.charAt(end) && isPalindrome(s, start + 1, end - 1));
    }
}
*/