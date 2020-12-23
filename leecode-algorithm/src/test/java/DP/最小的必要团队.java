package DP;

import org.junit.Test;

import java.util.*;

/**
 * https://blog.csdn.net/zark721/article/details/96100639
 * <p>
 * 题目：
 * 作为项目经理，你规划了一份需求的技能清单 req_skills，并打算从备选人员名单 people 中选出些人组成一个「必要团队」
 * （ 编号为 i 的备选人员 people[i] 含有一份该备选人员掌握的技能列表）。
 *
 * 所谓「必要团队」，就是在这个团队中，对于所需求的技能列表 req_skills 中列出的每项技能，团队中至少有一名成员已经掌握。
 *
 * 我们可以用每个人的编号来表示团队中的成员：例如，团队 team = [0, 1, 3] 表示掌握技能分别为 people[0]，people[1]，和 people[3] 的备选人员。
 * 请你返回 任一 规模最小的必要团队，团队成员用人员编号表示。你可以按任意顺序返回答案，本题保证答案存在。
 *
 * <p>
 * 示例 1：
 * 输入：req_skills = ["java","nodejs","reactjs"],
 *      people = [["java"],["nodejs"],["nodejs","reactjs"]]
 * 输出：[0,2]
 * <p>
 * 示例 2：
 * <p>
 * 输入：req_skills = ["algorithms","math","java","reactjs","csharp","aws"],
 *      people = [["algorithms","math","java"],["algorithms","math","reactjs"],["java","csharp","aws"],
 *      ["reactjs","csharp"],["csharp","math"],["aws","java"]]
 * 输出：[1,2]
 * <p>
 * <p>
 * 思路：状态压缩 + 动态规划dp + 位运算记录状态
 */
public class 最小的必要团队 {

    @Test
    public void teste0() {
        System.out.println(Arrays.toString(smallestSufficientTeam(
                new String[]{"java", "python"},
                Arrays.asList(Arrays.asList("java", "python"), Arrays.asList("go"), Arrays.asList("java")))));
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
