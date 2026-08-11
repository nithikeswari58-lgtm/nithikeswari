class Solution {
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        List<List<Integer>> res = new ArrayList<>();
        Arrays.sort(candidates);
        generateSequences(candidates, target, new ArrayList<>(), res, 0);
        return res;
    }

    void generateSequences(int[] candidates, int target, List<Integer> temp,
                           List<List<Integer>> res, int i) {
        if (target == 0) {
            res.add(new ArrayList<>(temp));
            return;
        }

        if (target < 0 || i >= candidates.length) {
            return;
        }

        temp.add(candidates[i]);
        generateSequences(candidates, target - candidates[i], temp, res, i + 1);

        int curr = temp.remove(temp.size() - 1);
        while (i < candidates.length && candidates[i] == curr) {
            i++;
        }
        generateSequences(candidates, target, temp, res, i);
    }
}