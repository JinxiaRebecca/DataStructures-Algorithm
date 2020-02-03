package com.example.dynamaic;

/**
 * @author:Rebecca Jin
 * @date: 2020/2/3,11:10
 * @version: 1.0
 * 动态规划解决背包问题：
 * 有一个背包，容量为4磅 ， 现有如下物品： 吉他，重量1磅，价格1500；音响，重量4磅，价格3000；电脑，重量3磅，价格2000
 * 要求达到的目标为装入的背包的总价值最大，并且重量不超出；
 * 要求装入的物品不能重复
 *
 * 算法的主要思想，利用动态规划来解决。每次遍历到的第i个物品，根据w[i]和v[i]来确定是否需要将该物品放入背包中。
 * 即对于给定的n个物品，设v[i]、w[i]分别为第i个物品的价值和重量，C为背包的容量。
 * 再令v[i][j]表示在前i个物品中能够装入容量为j的背包中的最大价值。则我们有下面的结果：
 * (1)  v[i][0]=v[0][j]=0; //表示 填入表 第一行和第一列是0
 * (2) 当w[i]> j 时：v[i][j]=v[i-1][j]   // 当准备加入新增的商品的容量大于 当前背包的容量时，就直接使用上一个单元格的装入策略
 *（3）当j>=w[i]时： v[i][j]=max{v[i-1][j], v[i]+v[i-1][j-w[i]]}  
 * 当 准备加入的新增的商品的容量小于等于当前背包的容量,v[i-1][j]： 就是上一个单元格的装入的最大值，v[i] : 表示当前商品的价值
 *  v[i-1][j-w[i]] ： 装入i-1商品，到剩余空间j-w[i]的最大值
 */
public class KnapsackProblem {
    public static void main(String[] args) {
        int[] w = {1,4,3}; //表示物品的重量
        int[] val = {1500,3000,2000};//表示物品的价值
        int n = val.length;//表示物品的数量
        int m = 4;//背包的最大容量

        //创建二维数组
        int[][] v = new int[n+1][m+1];
        int[][] path = new int[n+1][m+1];

        //第一行和第一列都是0
        for(int i=0;i<v.length;i++){
            v[i][0] = 0;
        }
        for(int i=0;i<v[0].length;i++){
            v[0][i] = 0;
        }

        for(int i=1;i<v.length;i++){
            for(int j=1;j<v[0].length;j++){
                if(w[i-1]>j){
                    v[i][j] = v[i-1][j];
                }else{
                    //v[i][j] = Math.max(v[i-1][j],val[i-1]+v[i-1][j-w[i-1]]);
                    if((val[i-1]+v[i-1][j-w[i-1]])>v[i-1][j]){
                        v[i][j] = val[i-1]+v[i-1][j-w[i-1]];
                        path[i][j]=1;
                    }else{
                        v[i][j] = v[i-1][j];
                    }
                }
            }
        }


        for(int[] value:v){
            for(int data:value){
                System.out.print(data+"\t");
            }
            System.out.println();
        }

        System.out.println("满足条件的商品放置情况如下：");
        int i = path.length-1;
        int j = path[0].length-1;
        while(i>0 && j>0){
            if(path[i][j]==1){
                System.out.printf("第%d个商品加入背包\n",i);
                j -= w[i-1];
            }
            i--;
        }

    }
}
