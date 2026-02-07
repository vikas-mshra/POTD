package Stack;

import java.util.*;
/**
    Last Attempt: 7 Feb 2026
    LeetCode Link: https://leetcode.com/problems/minimum-deletions-to-make-string-balanced
    Approach: In problems like this, where you have to delete characters, try with stack. Keep adding the characters in the stack and if you can't insert the next character (e.g., ba) then pop the top and increment the count
    Time(n): O(n)
    Space(n): O(1)
*/
class Solution {
    public int minimumDeletions(String s) {
        int deletions = 0; // Number of characters deleted
        Stack<Character> charStack = new Stack<>();

        for (char currentChar : s.toCharArray()) {

            // If stack is not empty, check for imbalance
            if (!charStack.isEmpty()) {

                // If top is 'a', or same as current, it stays balanced
                if (charStack.peek() == 'a' || charStack.peek() == currentChar) {
                    charStack.push(currentChar);
                } else {
                    // Found a "ba" pattern → delete one character
                    charStack.pop(); // Remove the 'b'
                    deletions++; // Count the deletion
                }

            } else {
                // First character always goes into the stack
                charStack.push(currentChar);
            }
        }

        return deletions;
    }
}
