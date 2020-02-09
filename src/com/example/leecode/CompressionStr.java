package com.example.leecode;

/**
 * @author:Rebecca Jin
 * @date: 2020/2/9,11:00
 * @version: 1.0
 * 通过键盘输入一串小写字母(a~z)组成的字符串。请编写一个字符串压缩程序，将字符串中连续出席的重复字母进行压缩，
 * 并输出压缩后的字符串。
 * 压缩规则：
 *     1、仅压缩连续重复出现的字符。比如字符串"abcbc"由于无连续重复字符，压缩后的字符串还是"abcbc"。
 *     2、压缩字段的格式为"字符重复的次数+字符"。例如：字符串"xxxyyyyyyz"压缩后就成为"3x6yz"
 */
public class CompressionStr {
    public static void main(String[] args) {
        stringZip("adef", 4, "out");

    }

    public static void stringZip(String inputStr, long inputLen, String outStr) {
        StringBuilder stringBuilder = new StringBuilder();
        int n = 0;
        for (int i = 0; i < inputLen; i++) {
            if (i<inputLen-1&&inputStr.charAt(i) == inputStr.charAt(i + 1)) {
                n++;
            } else {
                if (n > 0) {
                    stringBuilder.append(n+1 +""+ inputStr.charAt(i));
                } else {
                    stringBuilder.append("" + inputStr.charAt(i));

                }
                n = 0;


            }

        }
        outStr = stringBuilder.toString();
        System.out.println(outStr);
    }
}