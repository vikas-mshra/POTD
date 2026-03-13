package BinarySearch;

/**
    Last Attempt: 13 Mar 2026
    LeetCode Link: https://leetcode.com/problems/minimum-number-of-seconds-to-make-mountain-height-zero/
    Approach: Each worker would reduce the height by some height and we have to find the max time amoung all worker same applies for another round of worker work reduced by some height and we finding the max time amoung them. So the problem is find the min(max(time), max(time)) -> Binary Search
        - The worker who is taking the most time would be the upper limit and min time required would be 1 sec
        - check for a mid time, and the time is valid or not, by combining all worker work should be >= moutainHeight
    Time(n): O(log(m^2) * w)
    Space(n): O(1)
*/
class Solution {
    public long minNumberOfSeconds(int mountainHeight, int[] workerTimes) {
        // find the max worker time 
        int maxWorkerTime = 0;
        for (int workerTime : workerTimes)
            maxWorkerTime = Math.max(maxWorkerTime, workerTime);

        long maxTime = maxWorkerTime * ((mountainHeight * 1l * (mountainHeight + 1)) >> 1);
        long minTime = 1;
        long res = maxTime;

        // perform binary search on the limit
        while (minTime <= maxTime) {
            long midTime = minTime + ((maxTime - minTime) >> 1);
            if (isValidTime(workerTimes, midTime, mountainHeight)) {
                res = midTime;
                maxTime = res - 1;
            } else {
                minTime = midTime + 1;
            }
        }
        return res;
    }

    // for a specific time, we will find how much height can be reduced by a worker
    private boolean isValidTime(int[] workerTimes, long time, int mountainHeight) {
        for (int i = 0; i < workerTimes.length; i++) {
            mountainHeight -= workReduce(workerTimes[i], time);

            // if moutainHeight can be reduced to 0, its a valid time
            if (mountainHeight <= 0)
                return true;
        }
        return false;
    }

    // w * ((s) * (s + 1)/2) <= maxSec
    // (s) * (s + 1) <= (maxSec * 2)/w
    // s^2 + s <= (maxSec * 2)/w
    // s^2 + s + (1/2)^2 <= (maxSec * 2)/w + (1/2)^2
    // (s + 1/2)^2 <= (maxSec * 2)/w + (1/2)^2
    // s + 1/2 <= Math.sqrt((maxSec * 2)/w + (1/2)^2)
    // s + 0.5 <= Math.sqrt((maxSec * 2)/w + 0.5^2)
    // s <= Math.sqrt((maxSec * 2)/w + 0.25) - 0.5
    private int workReduce(int workerTime, long maxSeconds) {
        return (int) Math.floor(Math.sqrt(((2 * maxSeconds) / workerTime) + 0.25) - 0.5);
    }
}