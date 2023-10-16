package com.demo.leecode.hashMapRelated;

import java.util.HashSet;
import java.util.Set;

/**
 * @author Rebecca Jin
 * @date 2023/10/16 15:19
 * <p>
 * 128. 最长连续序列
 * 中等
 * <p>
 * 给定一个未排序的整数数组 nums ，找出数字连续的最长序列（不要求序列元素在原数组中连续）的长度。
 * <p>
 * 请你设计并实现时间复杂度为 O(n) 的算法解决此问题。
 * <p>
 * 示例 1：
 * <p>
 * 输入：nums = [100,4,200,1,3,2]
 * 输出：4
 * 解释：最长数字连续序列是 [1, 2, 3, 4]。它的长度为 4。
 * 示例 2：
 * <p>
 * 输入：nums = [0,3,7,2,5,8,4,6,0,1]
 * 输出：9
 * <p>
 * 思路 一： 对数组进行排序，遍历数组中的每个数x，匹配看下一个数是否是x+1，一直到x+y，其长度就是 y+1,时间复杂度o(n*2)
 * <p>
 * 思路 二： 使用hashset去重，如果set里面包含了x + 1，就一直向后循环，长度就一直加1
 */
public class NO128 {
    public static void main(String[] args) {
        int[] input1 = {100, 4, 200, 1, 3, 2};
        System.out.println(longestConsecutive(input1));

    }


    public static int longestConsecutive(int[] nums) {
        Set<Integer> set = new HashSet<>();
        for (int num : nums) {
            set.add(num);
        }
        int longestLen = 0;
        for (int num : set) {
            int currentNum = num;
            if (!set.contains(currentNum -1 )) {
                int currentLen = 1;
                while (set.contains(currentNum + 1)) {
                    currentNum += 1;
                    currentLen++;
                }
                longestLen = Math.max(longestLen, currentLen);
            }
        }
        return longestLen;
    }
}
