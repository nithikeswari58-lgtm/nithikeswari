class Solution {

    public String multiply(String num1, String num2) {
        if(num1.length() == 1 && num2.length() == 1){
            int cnt1 = num1.charAt(0) - '0';
            int cnt2 = num2.charAt(0) - '0';
            int ans = cnt1 * cnt2;
            return String.valueOf(ans);
        }

        int carry = 0;
        int[] arr = new int[num1.length() + num2.length()];

        for(int i = num2.length() - 1; i >= 0; i--){
            char ch = num2.charAt(i);
            int count1 = ch - '0';

            for(int j = num1.length() - 1; j >= 0; j--){
                char ch2 = num1.charAt(j);
                int count2 = ch2 - '0';

                int mul = count1 * count2;
                int sum = arr[i + j + 1] + mul + carry;

                carry = sum / 10;
                arr[i + j + 1] = sum % 10;
            }

            arr[i] += carry;
            carry = 0;
        }

        StringBuilder sb = new StringBuilder();
        int index = 0;

        for(int l = 0; l < arr.length; l++){
            if(arr[l] == 0){
                index = l;
            } else {
                break;
            }
        }

        if(index == arr.length - 1){
            return "0";
        }

        if(arr[index] == 0){
            index++;
        }

        for(int k = index; k < arr.length; k++){
            sb.append(arr[k]);
        }

        return sb.toString();
    }
}