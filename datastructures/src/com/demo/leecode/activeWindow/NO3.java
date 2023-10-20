package com.demo.leecode.activeWindow;

import java.util.HashSet;
import java.util.Set;

/**
 * @author Rebecca Jin
 * @date 2023/10/20 17:35
 * <p>
 * 3. 无重复字符的最长子串
 * 给定一个字符串 s ，请你找出其中不含有重复字符的 最长子串 的长度。
 * <p>
 * 示例 1:
 * 输入: s = "abcabcbb"
 * 输出: 3
 * 解释: 因为无重复字符的最长子串是 "abc"，所以其长度为 3。
 * <p>
 * 示例 2:
 * 输入: s = "bbbbb"
 * 输出: 1
 * 解释: 因为无重复字符的最长子串是 "b"，所以其长度为 1。
 * 示例 3:
 *
 * 输入: s = "pwwkew"
 * 输出: 3
 * 解释: 因为无重复字符的最长子串是 "wke"，所以其长度为 3。
 * 请注意，你的答案必须是 子串 的长度，"pwke" 是一个子序列，不是子串。
 *
 * 思路： 滑动窗口
 * 1。 使用两个指针，都从起始位置开始，每次固定左指针left，如果没有重复的，就向右移动右指针right，直到有重复的字符,
 * 此时字符串的长度就是right - left
 * 2。 当第一步退出时，说明已经有重复的字符串，则下一步就是把左指针右移，如果left 到right 不包含重复的字符，则left +1 到right一定
 * 不包含重复的字符，则right不用回到left,这样就构成了一个窗口，每次有重复的时候就把左边的移出去，没有重复的就继续扩大右侧的窗口，
 * 直到左窗口遍历到字符的最后一位
 */
public class NO3 {
    public static void main(String[] args) {
        String input = "abcabcbb";
        int result = lengthOfLongestSubstring(input);
        System.out.println(result);

        String input2 = "au";
        int result2 = lengthOfLongestSubstring(input2);
        System.out.println(result2);

    }

    public static int lengthOfLongestSubstring(String s) {
        int len = s.length();
        int result = 0;
        if (len == 0 || s == "") {
            return result;
        }
        int right = 0;
        Set<Character> map = new HashSet<>();
        for (int i = 0; i < len; i++) {
            if (i != 0) {
                map.remove(s.charAt(i - 1));
            }
            while (right < len && !map.contains(s.charAt(right))) {
                map.add(s.charAt(right));
                right++;
            }
            result = Math.max(result, right - i);
        }
        return result;
    }
}
