class Solution {
    public int minimumDeleteSum(String s1, String s2) {
        int m = s1.length();
        int n = s2.length();
        
        // dp[i][j] will store the minimum ASCII delete sum to make 
        // s1[0...i-1] and s2[0...j-1] equal.
        int[][] dp = new int[m + 1][n + 1];
        
        // Base Case 1: If s2 is empty (j=0), we must delete all characters of s1 up to i
        for (int i = 1; i <= m; i++) {
            dp[i][0] = dp[i-1][0] + s1.charAt(i-1);
        }
        
        // Base Case 2: If s1 is empty (i=0), we must delete all characters of s2 up to j
        for (int j = 1; j <= n; j++) {
            dp[0][j] = dp[0][j-1] + s2.charAt(j-1);
        }
        
        // Fill the DP table
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                
                // If characters are the same, no deletion cost is added for this step
                if (s1.charAt(i-1) == s2.charAt(j-1)) {
                    dp[i][j] = dp[i-1][j-1];
                } else {
                    // If characters differ, we take the minimum cost of either:
                    // 1. Deleting s1's character
                    // 2. Deleting s2's character
                    int deleteS1 = dp[i-1][j] + s1.charAt(i-1);
                    int deleteS2 = dp[i][j-1] + s2.charAt(j-1);
                    
                    dp[i][j] = Math.min(deleteS1, deleteS2);
                }
            }
        }
        
        // The answer is in the bottom-right cell
        return dp[m][n];
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        String s1 = "sea", s2 = "eat";
        System.out.println("Minimum delete sum: " + sol.minimumDeleteSum(s1, s2));
    }
}