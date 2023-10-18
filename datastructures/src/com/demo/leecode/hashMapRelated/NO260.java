package com.demo.leecode.hashMapRelated;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Rebecca Jin
 * @date 2023/10/16 18:05
 * <p>
 * 只出现一次的数字 III
 * <p>
 * 给你一个整数数组 nums，其中恰好有两个元素只出现一次，其余所有元素均出现两次。 找出只出现一次的那两个元素。你可以按 任意顺序 返回答案。
 * <p>
 * 示例 1：
 * <p>
 * 输入：nums = [1,2,1,3,2,5]
 * 输出：[3,5]
 * 解释：[5, 3] 也是有效的答案。
 * 示例 2：
 * <p>
 * 输入：nums = [-1,0]
 * 输出：[-1,0]
 * 示例 3：
 * <p>
 * 输入：nums = [0,1]
 * 输出：[1,0]
 */
public class NO260 {
    public static void main(String[] args) {
        int[] input = {1, 2, 1, 3, 2, 5};
        for (int i : singleNumber(input)) {
            System.out.println(i);
        }
    }

    public static int[] singleNumber(int[] nums) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int num : nums) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }
        return map.entrySet().stream().filter(i -> i.getValue().equals(1)).map(Map.Entry::getKey)
                .mapToInt(i -> i).toArray();
    }
}
