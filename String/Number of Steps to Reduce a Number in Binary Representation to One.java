package String;

/**
    Last Attempt: 28 Feb 2026
    LeetCode Link: https://leetcode.com/problems/number-of-steps-to-reduce-a-number-in-binary-representation-to-one/
    Approach: Convert the input string to a mutable StringBuilder and use a while loop to iterate through the string. If the last bit is '1', add 1 to the number. If the last bit is '0', divide the number by 2. Repeat this process until the length of the string is 1.
    Time(n): O(n)
    Space(n): O(1)
*/
class Solution {
    public int numSteps(String binaryString) {
        // Convert input string to a mutable StringBuilder
        StringBuilder currentBinary = new StringBuilder(binaryString);

        int stepCount = 0;

        while (currentBinary.length() != 1) {

            // Each loop iteration represents one operation
            stepCount++;

            int lastBitIndex = currentBinary.length() - 1;

            // If the number is odd (last bit is '1'), add 1
            if (currentBinary.charAt(lastBitIndex) == '1') {
                currentBinary = addOneToBinary(currentBinary);
            } else {
                // If the number is even (last bit is '0'), divide by 2
                // Dividing by 2 in binary = removing the last bit
                currentBinary.setLength(lastBitIndex);
            }
        }

        return stepCount;
    }

    // Helper method to add 1 to a binary number
    private StringBuilder addOneToBinary(StringBuilder binaryValue) {

        int carry = 1; // We are adding 1, so initial carry is 1
        StringBuilder result = new StringBuilder();

        // Traverse from right to left (least significant bit to most)
        for (int i = binaryValue.length() - 1; i >= 0; i--) {

            char currentBit = binaryValue.charAt(i);

            if (carry == 1) {
                if (currentBit == '1') {
                    // 1 + 1 = 0 (with carry)
                    result.append('0');
                } else {
                    // 0 + 1 = 1 (no more carry)
                    result.append('1');
                    carry = 0;
                }
            } else {
                // If no carry, just append the current bit
                result.append(currentBit);
            }
        }

        // If carry remains after processing all bits, append '1'
        if (carry == 1) {
            result.append('1');
        }

        // Reverse because we built the result from LSB to MSB
        return result.reverse();
    }
}