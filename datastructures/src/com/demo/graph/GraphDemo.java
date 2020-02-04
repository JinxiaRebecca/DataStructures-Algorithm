package com.demo.graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;

/**
 * @author:Rebecca Jin
 * @date: 2020/2/4,13:07
 * @version: 1.0
 * 演示图的创建和遍历
 */
public class GraphDemo {


    public static void main(String[] args) {
        int n = 5;
        String[] vertexVal = {"A","B","C","D","E"};

        Graph graph = new Graph(5);
        for (int i=0;i<vertexVal.length;i++){
            graph.addVertext(vertexVal[i]);
        }

        //添加边
        graph.addEdge(0,1,1);//A-B
        graph.addEdge(0,2,1);
        graph.addEdge(1,2,1);
        graph.addEdge(1,3,1);
        graph.addEdge(1,4,1);

        boolean[] isVisited = new boolean[vertexVal.length];

       graph.dfs(0,isVisited);

    }



}

class Graph {
    private ArrayList<String> vertexList; //存储顶点的集合
    private int[][] edges;//存储对应的邻结矩阵
    private int numsOfEdges; //边的个数
    private boolean[] isVisited ;//存储顶点是否被访问过
    //构造方法
    public Graph(int n) {
        edges = new int[n][n];
        vertexList = new ArrayList<>(n);
        isVisited = new boolean[n];
    }

    //添加顶点
    public void addVertext(String vertext) {
        vertexList.add(vertext);
    }

    /**
     * 添加边
     *
     * @param v1     第一个顶点的下标
     * @param v2     第二个顶点的下标
     * @param weight
     */
    public void addEdge(int v1, int v2, int weight) {
        edges[v1][v2] = weight;
        edges[v2][v2] = weight;
        numsOfEdges++;

    }

    public int getNumsOfEdges() {
        return this.numsOfEdges;
    }

    public int numOfVertext() {
        return vertexList.size();
    }

    public String getValueByIndex(int i) {
        return vertexList.get(i);
    }

    //返回两个顶点的权值
    public int getWeight(int v1, int v2) {
        return edges[v1][v1];
    }

    public void showGraph() {
        for (int[] link : edges) {
            System.out.print(Arrays.toString(link));
        }
        System.out.println();
    }

    /**
     * 获取一个节点的第一个相邻节点
     *
     * @param index 这个节点的下标
     * @return 找到了返回第一个邻结点的下标，没有找到返回-1
     */
    public int firstNeighbor(int index) {
        for (int j = 0; j < vertexList.size(); j++) {
            if (edges[index][j] > 0) {
                return j;
            }
        }
        return -1;
    }

    //根据前一个邻结点的下标，来获取下一个邻结点
    public int nextNeighbor(int v1, int v2) {
        for (int i = v2 + 1; i < vertexList.size(); i++) {
            if (edges[v1][i] > 0) {
                return i;
            }
        }
        return -1;
    }


    /**
     * 图的深度优先遍历：
     * 1）访问初始节点v,并标记节点v为已访问
     * 2）查找v的第一个邻结点w
     * 3）若w存在，则继续执行4，如果w不存在，则回第一步，将从v的下一个结点继续
     * 4)若w未被访问，对w进行深度优先遍历（即把w当做另一个v，继续123步）
     * 5）查找结点w邻结点的下一个邻结点，转到3
     */
    public void dfs(int index, boolean[] isVisited) {
        //访问第一个结点，并并标记为已访问
        System.out.println(getValueByIndex(index)+"-->");
        isVisited[index] = true;
        int w = firstNeighbor(index);
        while(w!=-1){
            if(!isVisited[w]){
                dfs(w,isVisited);
            }
            w=nextNeighbor(index,w);
        }
    }

    public void dfs(){
        for(int i=0;i<numOfVertext();i++){
            if(!isVisited[i]){
                dfs(i,isVisited);
            }
        }
    }

    /**
     * 对一个节点进行广度优先：
     * @param i
     * @param isVisited
     */
    public void bfs(int i,boolean[] isVisited){
        int u;//队列的头节点
        int w;//邻结点
        //记录节点访问的顺序
        LinkedList<Integer> queue = new LinkedList<>();
        System.out.println(vertexList.get(i)+"-->");
        isVisited[i] = true;//标记为已访问
        //将节点加入队列
        queue.addLast(i);
        while(!queue.isEmpty()){
             u = queue.getFirst();
             w = firstNeighbor(u);

             while(w!=-1){
                 if(!isVisited[w]){
                     isVisited[w] = true;
                     queue.addLast(w);
                 }
                 w = nextNeighbor(u,w);
             }

        }


    }

    public void bfs(){
        for(int i=0;i<numOfVertext();i++){
            if(!isVisited[i]){
                bfs(i,isVisited);
            }
        }
    }

}
