package SlidingWindow;

/**
 * Last Attempt: 7th March 2026
 * Leetcode Link:
 * https://leetcode.com/problems/minimum-number-of-flips-to-make-the-binary-string-alternating/description/
 * Approach: There is only two possible ways you can build the string i.e., it
 * would either start with 0 or 1
 * - Trick: when you have the check all the possible strings in a circular way,
 * concate the original string two times
 * - Use a sliding window technique to find for a size of n length substring,
 * how many flips are needed and keep track of min
 * Time(n): O(n)
 * Space(n): O(n)
 */
class Solution {
    public int minFlips(String s) {
        // concat the original string two times to check the string in a rotating
        // fashion
        StringBuilder modString = new StringBuilder(s);
        modString.append(s);

        // build both the possible string one starting with 1 and other starting with 0
        StringBuilder startsWithOne = new StringBuilder("1");
        StringBuilder startsWithZero = new StringBuilder("0");

        for (int i = 1; i < modString.length(); i++) {
            char prev1 = startsWithOne.charAt(i - 1);
            char prev0 = startsWithZero.charAt(i - 1);

            startsWithOne.append(prev1 == '1' ? '0' : '1');
            startsWithZero.append(prev0 == '1' ? '0' : '1');
        }

        // variables to track the minimum
        int minFlip = modString.length(); // maxFlip would be the string length itselff
        int minFlipWithFirstString = 0;
        int minFlipWithSecondString = 0;

        // start the window
        int start = 0;
        int end = modString.length();
        while (start < end) {
            if (start < s.length()) {
                // first check for the n length string
                while (start < s.length()) {
                    minFlipWithFirstString += s.charAt(start) == startsWithOne.charAt(start) ? 0 : 1;
                    minFlipWithSecondString += s.charAt(start) == startsWithZero.charAt(start) ? 0 : 1;
                    start++;
                }
            } else {
                // slide the window by removing one index and adding one index
                int index = start - s.length();

                // undo for index
                minFlipWithFirstString -= modString.charAt(index) == startsWithOne.charAt(index) ? 0
                        : 1;
                minFlipWithSecondString -= modString.charAt(index) == startsWithZero.charAt(index) ? 0
                        : 1;

                // check currentIndex
                minFlipWithFirstString += modString.charAt(start) == startsWithOne.charAt(start) ? 0 : 1;
                minFlipWithSecondString += modString.charAt(start) == startsWithZero.charAt(start) ? 0 : 1;

                // move right
                start++;
            }
            // track the min
            minFlip = Math.min(Math.min(minFlipWithFirstString, minFlipWithSecondString), minFlip);
        }

        return minFlip;
    }
}