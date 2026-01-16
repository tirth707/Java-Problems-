import java.util.Arrays;

class Solution {
    public int maximizeSquareHoleArea(int n, int m, int[] hBars, int[] vBars) {
        int maxH = getMaxConsecutive(hBars);
       
        int maxV = getMaxConsecutive(vBars);
        
        
        int side = Math.min(maxH + 1, maxV + 1);
        
        return side * side;
    }
    
    private int getMaxConsecutive(int[] bars) {
        Arrays.sort(bars);
        int maxLen = 1;
        int currentLen = 1;
        
        for (int i = 1; i < bars.length; i++) {
            if (bars[i] == bars[i - 1] + 1) {
                currentLen++;
            } else {
                maxLen = Math.max(maxLen, currentLen);
                currentLen = 1;
            }
        }
        return Math.max(maxLen, currentLen);
        
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        int n = 3, m = 3;
        int[] hBars = {2};
        int[] vBars = {2};
        System.out.println("Max area: " + sol.maximizeSquareHoleArea(n, m, hBars, vBars));
    }
}