from typing import List, Tuple


def max_subarray(nums: List[int]) -> Tuple[int, List[int]]:
    """
    Return the maximum subarray sum and one subarray achieving it.

    The function implements Kadane's algorithm with tracking of start/end
    indices to reconstruct a subarray that attains the maximum sum.

    Args:
        nums: List of integers (can include negatives and positives).

    Returns:
        A tuple (max_sum, subarray) where:
          - max_sum is the maximum possible sum of a contiguous subarray
          - subarray is one contiguous subarray whose sum equals max_sum

    Raises:
        ValueError: If nums is empty.
    """

    if not nums:
        raise ValueError("Input list must not be empty")

    current_sum: int = nums[0]
    max_sum: int = nums[0]
    current_start: int = 0
    best_start: int = 0
    best_end: int = 0  # inclusive index

    for index in range(1, len(nums)):
        value = nums[index]

        # Decide whether to extend the current subarray or start anew at index
        if current_sum + value < value:
            current_sum = value
            current_start = index
        else:
            current_sum += value

        # Update the best (max) seen so far
        if current_sum > max_sum:
            max_sum = current_sum
            best_start = current_start
            best_end = index

    return max_sum, nums[best_start : best_end + 1]


if __name__ == "__main__":
    examples = [
        [1, -2, 3, 5, -1],           # expected: sum 8, subarray [3, 5]
        [1, -2, 3, -8, 5, 1],        # expected: sum 6, subarray [5, 1]
        [1, -2, 3, -2, 5, 1],        # expected: sum 7, subarray [3, -2, 5, 1]
    ]

    for nums in examples:
        maximum, sub = max_subarray(nums)
        print(f"nums={nums} -> max_sum={maximum}, subarray={sub}")


