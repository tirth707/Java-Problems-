class Solution {
    public int maxDotProduct(int[] nums1, int[] nums2) {
        int n = nums1.length;
        int m = nums2.length;
        int[][] dp = new int[n][m];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                int pro = nums1[i] * nums2[j];
                dp[i][j] = pro;

                if (i > 0 && j > 0) {
                    dp[i][j] = Math.max(dp[i][j], pro + dp[i - 1][j - 1]);
                }
                if (i > 0) {
                    dp[i][j] = Math.max(dp[i][j], dp[i - 1][j]);
                }
                if (j > 0) {
                    dp[i][j] = Math.max(dp[i][j], dp[i][j - 1]);
                }
            }
        }
        return dp[n - 1][m - 1];
    }

    public static void main(String[] args) {
        Solution sol = new Solution();

        int[] nums1_1 = {2, 1, -2, 5};
        int[] nums2_1 = {3, 0, -6};
        System.out.println("Test Case 1 (Expected 18): " + sol.maxDotProduct(nums1_1, nums2_1));

        int[] nums1_2 = {3, -2};
        int[] nums2_2 = {2, -6, 7};
        System.out.println("Test Case 2 (Expected 21): " + sol.maxDotProduct(nums1_2, nums2_2));

        int[] nums1_3 = {-1, -1};
        int[] nums2_3 = {1, 1};
        System.out.println("Test Case 3 (Expected -1): " + sol.maxDotProduct(nums1_3, nums2_3));
    }
}