package 广度优先和深度优先;

import org.junit.Test;

import java.util.*;


public class 单词接龙 {


    @Test
    public void test2() {
        System.out.println(ladderLength("hit", "cog", Arrays.asList("hot", "dot", "dog", "lot", "log", "cog")));
    }

    public int ladderLength(String beginWord, String endWord, List<String> wordList) {

        // Since all words are of same length.
        int L = beginWord.length();

        // Dictionary to hold combination of words that can be formed,
        // from any given word. By changing one letter at a time.
        HashMap<String, ArrayList<String>> allComboDict = new HashMap<>();

        wordList.forEach(
                word -> {
                    for (int i = 0; i < L; i++) {
                        // Key is the generic word
                        // Value is a list of words which have the same intermediate generic word.
                        String newWord = word.substring(0, i) + '*' + word.substring(i + 1, L);
                        ArrayList<String> transformations =
                                allComboDict.getOrDefault(newWord, new ArrayList<String>());
                        transformations.add(word);
                        allComboDict.put(newWord, transformations);
                    }
                });

        // Queue for BFS
        Queue<HashMap<String, Integer>> Q = new LinkedList<>();
        HashMap<String, Integer> hashMap = new HashMap<>();
        hashMap.put(beginWord, 1);
        Q.add(hashMap);

        // Visited to make sure we don't repeat processing same word.
        HashMap<String, Boolean> visited = new HashMap<>();
        visited.put(beginWord, true);

        while (!Q.isEmpty()) {
            HashMap<String, Integer> node = Q.remove();
            Set<Map.Entry<String, Integer>> entries = node.entrySet();
            String word = null;
            Integer level = 0;
            for (Map.Entry<String, Integer> entry : entries) {
                word = entry.getKey();
                level = entry.getValue();
            }

            for (int i = 0; i < L; i++) {

                // Intermediate words for current word
                String newWord = word.substring(0, i) + '*' + word.substring(i + 1, L);

                // Next states are all the words which share the same intermediate state.
                for (String adjacentWord : allComboDict.getOrDefault(newWord, new ArrayList<String>())) {
                    // If at any point if we find what we are looking for
                    // i.e. the end word - we can return with the answer.
                    if (adjacentWord.equals(endWord)) {
                        return level + 1;
                    }
                    // Otherwise, add it to the BFS Queue. Also mark it visited
                    if (!visited.containsKey(adjacentWord)) {

                        visited.put(adjacentWord, true);

                        HashMap<String, Integer> hashMap1 = new HashMap<>();
                        hashMap1.put(adjacentWord, level + 1);
                        Q.add(hashMap1);
                    }
                }
            }
        }

        return 0;
    }




}