package com.demo.leecode.dynamic_programming;

import java.util.Arrays;

/**
 * @author Rebecca Jin
 * @date 2023/10/26 23:48
 * <p>
 * 53. 最大子数组和
 * 给你一个整数数组 nums ，请你找出一个具有最大和的连续子数组（子数组最少包含一个元素），返回其最大和。
 * 子数组 是数组中的一个连续部分。
 * <p>
 * 示例 1：
 * 输入：nums = [-2,1,-3,4,-1,2,1,-5,4]
 * 输出：6
 * 解释：连续子数组 [4,-1,2,1] 的和最大，为 6 。
 * <p>
 * 示例 2：
 * 输入：nums = [1]
 * 输出：1
 * <p>
 * 示例 3：
 * 输入：nums = [5,4,-1,7,8]
 * 输出：23
 * 思路：动态规划，数组的下标为i, 可以理解成以i结尾的数组前i项最大的和dp[i]，dp[i]取决于dp[i-1]和nums[i]
 * 一般情况下dp[i] = nums[i] + dp[i -1],以i结尾的最大的和分两种情况
 * 1. 如果dp[i -1 ] <= 0,则不用再加dp[i-1],因为会让结果变的更小
 * 2. 如果dp[i -1] > 0,则可以加上该值
 * 所以用一个数组存下所有以i结尾的最大值，然后再求数组的最大值就是结果需要的
 */
public class NO53 {
    public static void main(String[] args) {
        int[] input1 = {-2, 1, -3, 4, -1, 2, 1, -5, 4};
        System.out.println(maxSubArray(input1));

        int[] input2 = {1};
        System.out.println(maxSubArray(input2));

        int[] input3 = {5, 4, -1, 7, 8};
        System.out.println(maxSubArray(input3));
    }

    public static int maxSubArray(int[] nums) {
        int len = nums.length;
        if (nums == null || len == 0) {
            return len;
        }
        //instore the max sum which's index is end with i
        int[] dp = new int[len];
        dp[0] = nums[0];
        for (int i = 1; i < len; i++) {
            if (dp[i - 1] > 0) {
                dp[i] = nums[i] + dp[i - 1];
            } else {
                dp[i] = nums[i];
            }
        }
        Arrays.sort(dp);
        return dp[len - 1];
    }
}
