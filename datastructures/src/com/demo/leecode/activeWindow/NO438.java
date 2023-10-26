package com.demo.leecode.activeWindow;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author Rebecca Jin
 * @date 2023/10/26 17:49
 *
 * 438. 找到字符串中所有字母异位词
 *
 * 给定两个字符串 s 和 p，找到 s 中所有 p 的 异位词 的子串，返回这些子串的起始索引。不考虑答案输出的顺序。
 * 异位词 指由相同字母重排列形成的字符串（包括相同的字符串）。
 *
 * 示例 1:
 * 输入: s = "cbaebabacd", p = "abc"
 * 输出: [0,6]
 * 解释:
 * 起始索引等于 0 的子串是 "cba", 它是 "abc" 的异位词。
 * 起始索引等于 6 的子串是 "bac", 它是 "abc" 的异位词。
 *
 * 示例 2:
 * 输入: s = "abab", p = "ab"
 * 输出: [0,1,2]
 * 解释:
 * 起始索引等于 0 的子串是 "ab", 它是 "ab" 的异位词。
 * 起始索引等于 1 的子串是 "ba", 它是 "ab" 的异位词。
 * 起始索引等于 2 的子串是 "ab", 它是 "ab" 的异位词。
 *
 *思路： 这个题主要考察的用滑动窗口解决问题，怎样判断当前窗口的值和目标字符串是异位词很关键，推荐用option2，option1的空间复杂度太高
 */
public class NO438 {
    public static void main(String[] args) {
        String s1 = "cbaebabacd";
        String p1 = "abc";
        System.out.println(findAnagramsOption1(s1, p1));
        System.out.println(findAnagramsOption2(s1, p1));

        String s2 = "abab";
        String p2 = "ab";
        System.out.println(findAnagramsOption1(s2, p2));
        System.out.println(findAnagramsOption2(s2, p2));

    }

    // option1 每次比较时都要创建两个数组，空间复杂度高。会出现超出时间限制的错误
    public static List<Integer> findAnagramsOption1(String s, String p) {
        List<Integer> list = new ArrayList<>();
        int lenP = p.length();
        int lenS = s.length();
        if (lenP > lenS || s == "" || lenS == 0) {
            return list;
        }

        String s1 = s.substring(0, lenP);
        if (isTheSame(s1, p)) {
            list.add(0);
        }
        for (int j = 1;j <= lenS - lenP; j++ ) {
            String str = s.substring(j, j+ lenP);
            if (isTheSame(str, p)) {
                list.add(j);
            }
        }
        return list;

    }
    private static boolean isTheSame(String s1, String s2) {
        char[] array1 = s1.toCharArray();
        Arrays.sort(array1);

        char[] array2 = s2.toCharArray();
        Arrays.sort(array2);

        return  String.valueOf(array1).equals(String.valueOf(array2));
    }


    // option2
    public static List<Integer> findAnagramsOption2(String s, String p) {
        List<Integer> list = new ArrayList<>();
        int lenP = p.length();
        int lenS = s.length();
        if (lenP > lenS || s == "" || lenS == 0) {
            return list;
        }
        int[] countS = new int[26];
        int[] countP = new int[26];
        for (int i =0; i < lenP; i++) {
            countS[s.charAt(i)- 'a']++;
            countP[p.charAt(i) - 'a']++;
        }
        if (Arrays.equals(countP, countS)) {
            list.add(0);
        }

        for (int i =0; i< lenS - lenP;i++) {
            countS[s.charAt(i)- 'a'] --;
            countS[s.charAt(i+ lenP) -'a'] ++;
            if (Arrays.equals(countP, countS)) {
                list.add(i+1);
            }
        }
        return list;

    }
}
