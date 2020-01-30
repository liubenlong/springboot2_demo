package 字符串相关算法;

import java.util.*;

public class Test {

    public static void main(String[] args) {
        System.out.println(sortMapByValue("tree"));//方法1：直接使用集合的方法对map的value进行排序
        System.out.println(frequencySort("tree"));//方法2：桶排序【推荐】
    }

    //桶排序【推荐】
    public static String frequencySort(String s) {
        Map<Character, Integer> fre = new HashMap();
        //计算每个字符的频次，这里也可以用数组自己生成一个map
        for (char c : s.toCharArray()) {
            fre.put(c, fre.getOrDefault(c, 0) + 1);
        }
        //桶排序
        List<Character>[] bucket = new ArrayList[s.length() + 1];
        for (char c : fre.keySet()) {
            int f = fre.get(c);
            if (bucket[f] == null)
                bucket[f] = new ArrayList();
            bucket[f].add(c);
        }

        StringBuilder str = new StringBuilder();
        for (int i = bucket.length - 1; i >= 0; i--) {
            if (bucket[i] == null)
                continue;
            for (char c : bucket[i]) {
                for (int j = 0; j < i; j++) {
                    str.append(c);
                }
            }
        }
        return str.toString();
    }


    /**
     * 使用 Map按value进行排序
     * @param s
     * @return
     */
    public static String sortMapByValue(String s) {
        Map<Character, Integer> oriMap = new HashMap();
        //计算每个字符的频次，这里也可以用数组自己生成一个map
        for (char c : s.toCharArray()) {
            oriMap.put(c, oriMap.getOrDefault(c, 0) + 1);
        }

        if (oriMap == null || oriMap.isEmpty()) {
            return null;
        }

        List<Map.Entry<Character, Integer>> entryList = new ArrayList<>(oriMap.entrySet());
        //根据value排序
        Collections.sort(entryList, new Comparator<Map.Entry<Character, Integer>>() {
            @Override
            public int compare(Map.Entry<Character, Integer> o1, Map.Entry<Character, Integer> o2) {
                //按照从大到小的顺序排列，如果想正序 调换me1 me2的位置
                return o2.getValue().compareTo(o1.getValue());
            }
        });

        Iterator<Map.Entry<Character, Integer>> iter = entryList.iterator();
        StringBuilder str = new StringBuilder();
        while (iter.hasNext()) {
            Map.Entry<Character, Integer> entry = iter.next();
            for(int i = 0 ; i < entry.getValue(); i ++){
                str.append(entry.getKey());
            }
        }
        return str.toString();
    }


}
