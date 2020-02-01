package com.demo.sparsearr;

/**
 * @author:Rebecca Jin
 * @date: 2020/2/1,14:37
 * @version: 1.0
 * 演示稀疏数组和二维数组的相互转换
 */
public class SparseArrDemo {
    public static void main(String[] args) {
        int[][] chessArr = new int[11][11];
        chessArr[1][2]=1;//1代表黑棋
        chessArr[2][3]=2;//2代表白棋，0代表没有棋子

        //输出原始的棋盘
        for(int[] chess:chessArr){
            for(int data:chess ){
                System.out.printf("%d\t",data);
            }
            System.out.println();
        }

        //将二维数组转化成稀疏数组
        int[][] sparseArray = transferToSparseArray(chessArr);

        //将稀疏数组转化成二维数组
        int[][] originalArr= transferToIntArray(sparseArray);

    }



    //将二维数组转化成稀疏数组
    private static int[][]  transferToSparseArray(int[][] chessArr) {
        //创建临时变量sum,用来记录二维数组中有效数据的个数，遍历chessArr,获取有效数据个数
        int sum = 0;
        for(int i=0;i<11;i++){
            for(int j=0;j<11;j++){
                if(chessArr[i][j]!=0){
                    sum ++;
                }
            }
        }
        //根据有效数据个数创建稀疏数组spareArr,行数是sum+1,列数是3
        int[][] sparseArr = new int[sum+1][3];

        //稀疏数组的第一行数据记录了原始数组的行数，列数，已经有效数据个数
        sparseArr[0][0] = chessArr.length;
        sparseArr[0][1] = chessArr[0].length;
        sparseArr[0][2] = sum;

        //遍历原始数组，将有效数据存入到稀疏数组
        int index=1;
        for(int i=0;i<11;i++){
            for(int j=0;j<11;j++){
                if(chessArr[i][j]!=0){
                    sparseArr[index][0]=i;
                    sparseArr[index][1]=j;
                    sparseArr[index][2]=chessArr[i][j];
                    index ++;
                }
            }
        }
        //遍历输出稀疏数组
        for(int[] sparsearr:sparseArr){
            for(int value:sparsearr){
                System.out.printf("%d\t",value);
            }
            System.out.println();
        }
        return sparseArr;
    }

    //将稀疏数组转化为二维数组
    private static int[][] transferToIntArray(int[][] sparseArray) {
        //获取稀疏数组第一行的数据，获取原始数组的行数和列数
        int[][] originalArr = new int[sparseArray[0][0]][sparseArray[0][1]];
        int sum = sparseArray[0][2];
        //遍历稀疏数组，将数据还原
        for(int i=1;i<sum+1;i++){
            originalArr[sparseArray[i][0]][sparseArray[i][1]]=sparseArray[i][2];

        }
        System.out.println("还原后的二维数组如下~~~~~~");
        //输出原始的棋盘
        for(int[] chess:originalArr){
            for(int data:chess ){
                System.out.printf("%d\t",data);
            }
            System.out.println();
        }
        return originalArr;
    }
}
