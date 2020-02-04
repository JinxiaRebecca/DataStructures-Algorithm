package com.example.greedy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

/**
 * @author:Rebecca Jin
 * @date: 2020/2/3,18:35
 * @version: 1.0
 * 使用贪心算法解决下列问题：
 * 假设存在如下表的需要付费的广播台，以及广播台信号可以覆盖的地区。
 * 如何选择最少的广播台，让所有的地区都可以接收到信号：
 * 广播台           覆盖地区
 * K1            "北京", "上海", "天津"
 * K2            "广州", "北京", "深圳"
 * K3            "成都", "上海", "杭州"
 * K4            "上海", "天津"
 * k5            "杭州", "大连"
 */
public class GreedyAlgotithm {
    public static void main(String[] args) {
        Map<String, HashSet<String>> webcasts = new HashMap<>();
        HashSet<String> hashset1 = new HashSet<>();
        hashset1.add("北京");
        hashset1.add("上海");
        hashset1.add("天津");
        webcasts.put("k1,", hashset1);

        HashSet<String> hashset2 = new HashSet<>();
        hashset2.add("广州");
        hashset2.add("北京");
        hashset2.add("深圳");
        webcasts.put("k2,", hashset2);

        HashSet<String> hashset3 = new HashSet<>();
        hashset3.add("成都");
        hashset3.add("上海");
        hashset3.add("杭州");
        webcasts.put("k3,", hashset3);

        HashSet<String> hashset4 = new HashSet<>();
        hashset4.add("上海");
        hashset4.add("天津");
        webcasts.put("k4,", hashset4);

        HashSet<String> hashset5 = new HashSet<>();
        hashset5.add("杭州");
        hashset5.add("大连");
        webcasts.put("k5,", hashset5);

        //allAreas是能够覆盖的全部区域
        HashSet<String> allAreas = new HashSet<>();
        for (String key : webcasts.keySet()) {
            allAreas.addAll(webcasts.get(key));
        }

        //挑选的电台
        ArrayList<String> selects = new ArrayList<>();
        //保存在一次遍历过程中，能够覆盖最多地区的电台的key
        String maxKey = "";

        HashSet<String> temp = new HashSet<>();
        while (allAreas.size() > 0) {
            maxKey = "";
            //遍历电台，获取覆盖区域最大的区域
            for (String key : webcasts.keySet()) {
                temp.clear();
                temp.addAll(webcasts.get(key));
                //temp和allAreas取交集，再放入temp
                temp.retainAll(allAreas);
                //找出一次遍历中覆盖区域最多的电台
                if (temp.size() > 0 && (maxKey=="" || temp.size() > webcasts.get(maxKey).size())) {
                    maxKey = key;
                }
            }
            if (maxKey!="") {
                selects.add(maxKey);
            }
            //将maxKey覆盖的地区从allAreaszh中去除掉
            allAreas.removeAll(webcasts.get(maxKey));
        }
        System.out.println("选出的电台是"+selects);

    }
}
