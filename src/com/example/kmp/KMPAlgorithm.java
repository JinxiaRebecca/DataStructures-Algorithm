package com.example.kmp;

/**
 * @author:Rebecca Jin
 * @date: 2020/2/3,16:20
 * @version: 1.0
 */
public class KMPAlgorithm {
    public static void main(String[] args) {
        String str1 = "BBC ABCDAB ABCDABCDABDE";
        String str2 = "ABCDABD";
        int[] next = getNext(str2);
        int index = getFirstIndextOfSameStr(str1,str2,next);
        System.out.println("第一次出现的下表是"+index);

    }

    private static int[] getNext(String str2) {
        int[] next = new int[str2.length()];
        next[0] = 0;
        for(int i=1,j=0;i<str2.length();i++){
            while(j>0 && str2.charAt(i)!=str2.charAt(j)){
                j = next[j-1];
            }
            if(str2.charAt(j)==str2.charAt(i)){
                j++;
            }
            next[i] =j;
        }
        return next;
    }

    private static int getFirstIndextOfSameStr(String str1, String str2, int[] next) {
        for(int i=0,j=0;i<str1.length();i++){
            while(j>0&&str1.charAt(i)!=str2.charAt(j)){
                j=next[j-1];
            }
            if(str1.charAt(i)==str2.charAt(j)){
                j ++;
            }

            if(j==str2.length()){
                return i-j+1;
            }
        }
        return -1;
    }



}
