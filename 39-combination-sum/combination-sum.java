class Solution {
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> ans=new ArrayList<>();
        backTrack(0,target,new ArrayList<>(),ans,candidates);
        return ans;
    }

    private void backTrack(int idx,int target,List<Integer> current,List<List<Integer>> ans,int arr[]){
        if(target==0){
            ans.add(new ArrayList<>(current));
            return;
        }
        else if(target<0){
            return;
        }

        for(int i=idx;i<arr.length;i++){
            current.add(arr[i]);
            backTrack(i,target-arr[i],current,ans,arr);
            current.remove(current.size()-1);
        }
    }
}