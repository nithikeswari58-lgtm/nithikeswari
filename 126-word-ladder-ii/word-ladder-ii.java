class Solution {
    private void addWord(Map<String, List<Integer>> map, String s, int idx){
        char ch[] = s.toCharArray();
        for(int i=0; i<ch.length; i++){
            char tmp = ch[i];
            ch[i] = '*';
            map.computeIfAbsent(new String(ch), f -> new ArrayList<>())
                .add(idx);
            ch[i] = tmp;
        }
    }
    private void backtrack(List<List<String>> ans, List<Integer>[] parent, 
            List<String> ref, int idx, int n, List<String> list){
        if(idx == n){
            List<String> tmp = new ArrayList<>(list);
            Collections.reverse(tmp);
            ans.add(tmp);
            return;
        }

        for(int x: parent[idx]){
            list.add(ref.get(x));
            backtrack(ans, parent, ref, x, n, list);
            list.remove(list.size()-1);
        }
    }
    public List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList) {
        Map<String, List<Integer>> map = new HashMap<>();
        int n = wordList.size(), used[] = new int[n+1]; //nth pos for beginWord
        List<Integer>[] parent = new ArrayList[n+1];

        int endIdx = -1;
        for(int i=0; i<n; i++){
            String s = wordList.get(i);
            if(s.equals(endWord)) endIdx = i;
            if(!s.equals(beginWord)){
                addWord(map, s, i);
                used[i] = -1;
            }
            parent[i] = new ArrayList<>();
        }
        if(endIdx == -1) return new ArrayList<>();
        wordList.add(beginWord); used[n] = 0; parent[n] = new ArrayList<>();

        ArrayDeque<Integer> aq = new ArrayDeque<>();
        aq.addLast(n);

        List<Integer> blank = new ArrayList<>();
        boolean isEndReached = false; int level = 1;
        while(!aq.isEmpty()){
            breadthLoop: for(int j=0, size=aq.size(); j<size; j++){
                int strIdx = aq.removeFirst();
                String str = wordList.get(strIdx);

                char last[] = str.toCharArray();
                for(int i=0; i<last.length; i++){
                    char c = last[i];
                    last[i] = '*';

                    for(Integer nextIdx: map.getOrDefault(new String(last), blank)){
                        String next = wordList.get(nextIdx);
                        int nextLevel = used[nextIdx];

                        if(nextLevel != -1 && nextLevel != level) continue;
                        
                        parent[nextIdx].add(strIdx);
                        if(endWord.equals(next)){
                            isEndReached = true;
                            continue breadthLoop;
                        } 

                        if(nextLevel == -1){
                            used[nextIdx] = level;
                            aq.addLast(nextIdx);
                        }
                    }

                    last[i] = c;
                }
            }
            ++level;
            if(isEndReached) break;
        }

        List<List<String>> ans = new ArrayList<>();
        backtrack(ans, parent, wordList, endIdx, n, new ArrayList<>(List.of(endWord)));
        wordList.remove(wordList.size()-1);
        return ans;
    }
}