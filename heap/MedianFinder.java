package heap;

import java.util.PriorityQueue;

public class MedianFinder {

    PriorityQueue<Integer> minHeap;
    PriorityQueue<Integer> maxHeap;

    public MedianFinder() {

        minHeap = new PriorityQueue<>();
        maxHeap = new PriorityQueue<>((a, b) -> b - a);
    }


    // planed in that if both are not equal then answwer is in max heap
    public void addNum(int num) {

        // Add to maxHeap by default
        maxHeap.add(num);

        // Ensure the largest number in maxHeap is smaller than the smallest number in
        // minHeap
        if (!minHeap.isEmpty() && maxHeap.peek() > minHeap.peek()) {
            minHeap.add(maxHeap.poll());
        }

        // Balance the heaps if maxHeap has more than 1 extra element compared to
        // minHeap
        if (maxHeap.size() > minHeap.size() + 1) {
            minHeap.add(maxHeap.poll());
        }
        // Ensure minHeap doesn't get larger than maxHeap
        if (minHeap.size() > maxHeap.size()) {
            maxHeap.add(minHeap.poll());
        }
    }

    public double findMedian() {
        // If both heaps are balanced, the median is the average of the top elements
        if (maxHeap.size() == minHeap.size()) {
            return (maxHeap.peek() + minHeap.peek()) / 2.0;
        }
        // If maxHeap has more elements, its top element is the median
        else {
            return maxHeap.peek();
        }
    }
/**
 * Optimization Notes:
 *
 * 1. If all integers are in the range [0, 100]:
 *    - Explanation:
 *      - Use a frequency array `count[101]` to store the occurrence of each number.
 *      - Keep track of the total count of numbers with `totalCount`.
 *      - To find the median, iterate through the frequency array to locate the middle element(s).
 *      - This approach is highly efficient as the range is small and operations are constant time.
 *
 *    - Code:
 *      int[] count = new int[101];  // Frequency array for numbers in [0, 100]
 *      int totalCount = 0;  // Total count of numbers
 *
 *      public void addNum(int num) {
 *          count[num]++;  // Increment frequency for the number
 *          totalCount++;  // Increment total count
 *      }
 *
 *      public double findMedian() {
 *          int mid1 = (totalCount + 1) / 2;  // Position of the first median
 *          int mid2 = (totalCount % 2 == 0) ? mid1 + 1 : mid1;  // Second median if even count
 *          int cumulativeCount = 0, median1 = -1, median2 = -1;
 *
 *          for (int i = 0; i < 101; i++) {
 *              cumulativeCount += count[i];
 *              if (median1 == -1 && cumulativeCount >= mid1) median1 = i;
 *              if (median2 == -1 && cumulativeCount >= mid2) median2 = i;
 *          }
 *          return (median1 + median2) / 2.0;
 *      }
 *
 * 2. If 99% of integers are in the range [0, 100]:
 *    - Explanation:
 *      - Use a hybrid approach:
 *        - A frequency array `count[101]` for numbers in the range [0, 100].
 *        - A max-heap for numbers less than 0 and a min-heap for numbers greater than 100.
 *      - This ensures efficient processing of numbers in the common range, with heaps managing outliers.
 *      - When finding the median, check if it lies in [0, 100] using the frequency array.
 *        If not, retrieve it from the heaps.
 *
 *    - Code:
 *      int[] count = new int[101];  // Frequency array for numbers in [0, 100]
 *      int totalCount = 0;
 *      PriorityQueue<Integer> minHeap = new PriorityQueue<>();  // For numbers > 100
 *      PriorityQueue<Integer> maxHeap = new PriorityQueue<>((a, b) -> b - a);  // For numbers < 0
 *
 *      public void addNum(int num) {
 *          if (num >= 0 && num <= 100) {
 *              count[num]++;
 *          } else if (num > 100) {
 *              minHeap.add(num);
 *          } else {
 *              maxHeap.add(num);
 *          }
 *          totalCount++;
 *      }
 *
 *      public double findMedian() {
 *          int mid1 = (totalCount + 1) / 2;
 *          int mid2 = (totalCount % 2 == 0) ? mid1 + 1 : mid1;
 *          int cumulativeCount = 0, median1 = -1, median2 = -1;
 *
 *          for (int i = 0; i < 101; i++) {
 *              cumulativeCount += count[i];
 *              if (median1 == -1 && cumulativeCount >= mid1) median1 = i;
 *              if (median2 == -1 && cumulativeCount >= mid2) median2 = i;
 *          }
 *
 *          // Handle outliers if the median is not in [0, 100]
 *          if (median1 == -1 && !maxHeap.isEmpty()) {
 *              median1 = maxHeap.peek();
 *          }
 *          if (median2 == -1 && !minHeap.isEmpty()) {
 *              median2 = minHeap.peek();
 *          }
 *
 *          return (median1 + median2) / 2.0;
 *      }
 */



}
