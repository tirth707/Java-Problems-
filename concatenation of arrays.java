class Solution {
    public int[] getConcatenation(int[] nums) {
        int n=nums.length;
        int[] ans=new int[2*n];

        for(int i=0;i<n;i++){
            ans[i]=nums[i];
            ans[i+n]=nums[i];
        }
        return ans;
        
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        int[] nums = {1, 2, 1};
        int[] result = sol.getConcatenation(nums);
        System.out.println("Input: " + java.util.Arrays.toString(nums));
        System.out.println("Output: " + java.util.Arrays.toString(result));
    }
}