package DP;

import org.junit.Test;

import java.util.*;

public class 最小必要团队 {
    @Test
    public void teste0() {
        System.out.println(Arrays.toString(smallestSufficientTeam(
                new String[]{"java","nodejs","reactjs"},
                Arrays.asList(Arrays.asList("java"), Arrays.asList("nodejs"), Arrays.asList("nodejs","reactjs")))));
    }

    public int[] smallestSufficientTeam(String[] req_skills, List<List<String>> people) {
        int plen = people.size(); //人数
        int slen = req_skills.length; //总技能数
        int dp[] = new int[1 << slen]; //记录状态为state时 最少需要的人数。
        List<Integer> rc[] = new ArrayList[1 << slen];//记录状态为 state时，最少需要的人员的编号

        Map<String, Integer> map = new HashMap<>();
        for (int i = 0; i < slen; i++) {
            map.put(req_skills[i], (1 << i));//位运算，表示每个状态。
        }

        for (int i = 0; i < (1 << slen); i++) {
            rc[i] = new ArrayList<>();
            dp[i] = -1;
        }
        //当状态为0时，也就是没有掌握技能时，需要的人数是0
        dp[0] = 0;

        for (int i = 0; i < plen; i++) { //遍历每一个人
            int sk = 0;
            for (String skill : people.get(i)) {
                sk |= (null == map.get(skill) ? 0 : map.get(skill));  //得到这个人掌握技能的状态
            }
            for (int st = 0; st < (1 << slen); st++) { //遍历所有的状态，看是否需要更新
                if (dp[st] == -1) continue;
                int newState = sk | st;
                if (dp[newState] == -1 || dp[st] + 1 < dp[newState]) { //需要更新，那么更新
                    dp[newState] = dp[st] + 1; //更新人员数量
                    rc[newState].clear();
                    rc[newState].addAll(rc[st]);  //更新人员的编号
                    rc[newState].add(i);
                }
            }
        }

        List<Integer> rsList = rc[(1 << slen) - 1]; //结果就是这个 (1<<slen) -1 状态的 人员编号。
        int rsSz = rsList.size();
        int rsArr[] = new int[rsSz];
        for (int i = 0; i < rsSz; i++) {
            rsArr[i] = rsList.get(i);
        }
        return rsArr;
    }

}
