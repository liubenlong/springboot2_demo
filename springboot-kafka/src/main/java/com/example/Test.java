package com.example;

import java.util.*;

public class Test {

    public static void main(String[] args) {

        topN(new String[]{"a", "s", "d", "b","d"}, 2);
    }


    public static void topN(String[] strs, int n) {
        HashMap<String, Integer> map = new HashMap();
        for (String str : strs) {
            map.put(str, map.getOrDefault(str, 0) + 1);
        }

        ArrayList<String>[] strnums = new ArrayList[strs.length + 1];
        Set<Map.Entry<String, Integer>> entries = map.entrySet();
        Iterator<Map.Entry<String, Integer>> iterator = entries.iterator();
        while (iterator.hasNext()) {
            Map.Entry<String, Integer> entry = iterator.next();
            if(strnums[entry.getValue()]==null){
                strnums[entry.getValue()] = new ArrayList<>();
            }
            strnums[entry.getValue()].add(entry.getKey());
        }


        System.out.println("------");
        int count = 0;
        for (int i = strnums.length - 1; i > 0; i--) {
            if (null == strnums[i]) {
                continue;
            } else {
                if(count>n)continue;
                System.out.println(i + "   " + strnums[i]);

            }
        }

    }

}
