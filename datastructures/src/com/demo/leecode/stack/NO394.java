package com.demo.leecode.stack;

import java.util.*;
import java.util.stream.Collectors;


/**
 * @author Rebecca Jin
 * @date 2023/10/25 12:56
 * <p>
 * 给定一个经过编码的字符串，返回它解码后的字符串。
 * 编码规则为: k[encoded_string]，表示其中方括号内部的 encoded_string 正好重复 k 次。注意 k 保证为正整数。
 * 你可以认为输入字符串总是有效的；输入字符串中没有额外的空格，且输入的方括号总是符合格式要求的。
 * 此外，你可以认为原始数据不包含数字，所有的数字只表示重复的次数 k ，例如不会出现像 3a 或 2[4] 的输入。
 * <p>
 * 示例 1：
 * 输入：s = "3[a]2[bc]"
 * 输出："aaabcbc"
 * <p>
 * 示例 2：
 * 输入：s = "3[a2[c]]"
 * 输出："accaccacc"
 * <p>
 * 示例 3：
 * 输入：s = "2[abc]3[cd]ef"
 * 输出："abcabccdcdcdef"
 * <p>
 * 示例 4：
 * 输入：s = "abc3[cd]xyz"
 * 输出："abccdcdcdxyz"
 */
public class NO394 {
    public static void main(String[] args) {
        String s1 = "3[a]2[bc]";
        System.out.println(decodeString(s1));

        String s2 = "3[a2[c]]";
        System.out.println(decodeString(s2));

        String s3 = "100[leetcode]";
        System.out.println(decodeString(s3));
    }

    public static String decodeString(String s) {
        int len = s.length();
        if (s == "" || s.length() == 0) {
            return s;
        }
        Stack<String> stack = new Stack<>();
        int i = 0;
        while (i < len) {
            Character c = s.charAt(i);
            if (Character.isDigit(c)) {
                StringBuilder sb = new StringBuilder();
                while (Character.isDigit(s.charAt(i))) {
                    sb.append(s.charAt(i++));
                }
                stack.push(sb.toString());
            } else if (Character.isLetter(c) || c == '[') {
                stack.push(String.valueOf(c));
                i++;
            } else {
                List<String> list = new LinkedList<>();
                while (!stack.peek().equals("[")) {
                    list.add(stack.pop());
                }
                stack.pop();
                Collections.reverse(list);
                String str = list.stream().collect(Collectors.joining(""));
                int count = Integer.parseInt(stack.pop());
                String assembleStr = getAssembledStr(str, count);
                stack.push(assembleStr);
                i++;
            }

        }

        return getFinalString(stack);

    }

    private static String getFinalString(Stack<String> stack) {
        List<String> list = new ArrayList<>();
        while (!stack.empty()) {
            list.add(stack.pop());
        }
        Collections.reverse(list);
        StringBuilder sb = new StringBuilder();
        for (String str : list) {
            sb.append(str);
        }
        return sb.toString();
    }

    private static String getAssembledStr(String str, int count) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < count; i++) {
            sb.append(str);
        }
        return sb.toString();

    }
}   
    