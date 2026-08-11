class Solution {
   public  String countAndSay(int n) {
        String[] weKnow = {"1","11","21","1211","111221","312211","13112221","1113213211","31131211131221"};
        return findRle(n,weKnow);
    }


    public  String findRle(int n,String[] arr ){
        if (n <= 9){
            return arr[n-1];
        }
        String str =  findRle(n-1,arr);
        StringBuilder ans = new StringBuilder();
        int count = 1;
        for (int i = 1; i <= str.length(); i++) {
            if (i < str.length() &&str.charAt(i-1) == str.charAt(i)){
                count++;
            }else{
                ans.append(count);
                ans.append(str.charAt(i-1));
                count = 1;
            }
        }
        return ans.toString();

    }
}