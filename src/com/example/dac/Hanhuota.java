package com.example.dac;

/**
 * @author:Rebecca Jin
 * @date: 2020/2/2,18:26
 * @version: 1.0
 * 使用分值算法实现汉诺塔：
 * 1：如果只有一个，最简单，直接从A-->C
 * 2:如果盘数n>2,可以看做是两部分，最小面的一部分，上面的为一部分，
 * 3：上面的盘从A-->B,最下面的盘从A-->C,上面的从B-->C
 */
public class Hanhuota {
    public static void main(String[] args) {
        hanhuota(5,'A','B','C');
    }

    public static void hanhuota(int num,char a, char b,char c){
        if(num==1){
            System.out.println("从"+a+"-->"+c);
        }else{
            //上面的盘从A到B,中间借助C
            hanhuota(num-1,a,c,b);
            System.out.println("从"+a+"-->"+c);
            //上面的盘从A到C,中间借助B
            hanhuota(num-1,a,b,c);
        }
    }
}
