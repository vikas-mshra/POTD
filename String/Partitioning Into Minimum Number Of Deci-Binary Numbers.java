package String;

/**
    Last Attempt: 01 Mar 2026
    LeetCode Link: https://leetcode.com/problems/partitioning-into-minimum-number-of-deci-binary-numbers/
    Approach: Subtract the largest possible deci-binary number from the current number and increment the partitions count. Repeat until the current number is 0.
    Time(n): O(n)
    Space(n): O(1)
*/
class Solution {
    public int minPartitions(String number) {
        StringBuilder current = new StringBuilder(number);
        int partitions = 0;

        // Keep subtracting the largest possible deci-binary number
        while (current.length() > 0) {
            partitions++;
            current = subtract(current, buildDeciBinary(current));
        }

        return partitions;
    }

    // Builds a deci-binary number (only 0s and 1s)
    // For each non-zero digit, place '1'; otherwise '0'
    private StringBuilder buildDeciBinary(StringBuilder value) {
        StringBuilder deciBinary = new StringBuilder();

        for (int i = 0; i < value.length(); i++) {
            deciBinary.append(value.charAt(i) == '0' ? '0' : '1');
        }

        return deciBinary;
    }

    // Subtracts deciBinary from value (no borrow needed since deciBinary is 0/1 only)
    private StringBuilder subtract(StringBuilder value, StringBuilder deciBinary) {

        for (int i = value.length() - 1; i >= 0; i--) {
            int digit = value.charAt(i) - '0';
            int subtractDigit = deciBinary.charAt(i) - '0';

            value.setCharAt(i, (char) ('0' + (digit - subtractDigit)));
        }

        // Remove leading zeros
        int firstNonZero = 0;
        while (firstNonZero < value.length() && value.charAt(firstNonZero) == '0') {
            firstNonZero++;
        }

        return (firstNonZero == value.length())
                ? new StringBuilder()
                : value.delete(0, firstNonZero);
    }
}