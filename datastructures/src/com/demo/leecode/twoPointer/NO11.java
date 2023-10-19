package com.demo.leecode.twoPointer;

/**
 * @author Rebecca Jin
 * @date 2023/10/18 11:07
 *
 * 11. 盛最多水的容器

 * 给定一个长度为 n 的整数数组 height 。有 n 条垂线，第 i 条线的两个端点是 (i, 0) 和 (i, height[i]) 。
 *找出其中的两条线，使得它们与 x 轴共同构成的容器可以容纳最多的水。
 *返回容器可以储存的最大水量。
 *
 * 说明：你不能倾斜容器。
 * 示例 1：
 * 输入：[1,8,6,2,5,4,8,3,7]
 * 输出：49
 *
 * 解释：图中垂直线代表输入数组 [1,8,6,2,5,4,8,3,7]。在此情况下，容器能够容纳水（表示为蓝色部分）的最大值为 49。
 * 示例 2：
 * 输入：height = [1,1]
 * 输出：1
 *
 * 解题思路：
 * 1. 使用双指针，起始位置：左侧指针i指向最左端，右侧指针j指向最右端，能够节水的容量：（j-i）*min(height[i], height[j])
 * 2. 下来就是指针怎么移动的问题，如果i向右移，则min(height[i], height[j])可能变大，则面积会变大，
 *                           如果j向左移，则min(height[i], height[j]) 可能不变，或者更小， 则面试一低昂变小
 *    综上，每次移动高度最小的
 * 3. 移动后计算一次面积，并保存面积最大的值
 * 4. 移动至j,i重合
 * */
public class NO11 {
    public static void main(String[] args) {
        int[] height = {1,8,6,2,5,4,8,3,7};
        int result = maxArea(height);
        System.out.println(result);

    }

    public static int maxArea(int[] height) {
        int result = 0;
        int len = height.length;
        if (len <= 1 || height == null) {
            return result;
        }
        int i = 0;
        int j = len -1;
        while (i < j) {
            int area = Math.min(height[i], height[j]) * (j - i);
            result = Math.max(result, area);
            if (height[i] < height[j]) {
                i ++;
            } else {
                j --;
            }
        }
        return result;
    }
}
