import java.util.*;

class Solution {
    public int maximizeSquareArea(int m, int n, int[] hFences, int[] vFences) {
        long maxSide = -1;
        long MOD = 1_000_000_007;

        // Add boundaries to the fence arrays
        int[] h = Arrays.copyOf(hFences, hFences.length + 2);
        h[h.length - 2] = 1;
        h[h.length - 1] = m;
        
        int[] v = Arrays.copyOf(vFences, vFences.length + 2);
        v[v.length - 2] = 1;
        v[v.length - 1] = n;

        // Calculate all possible horizontal gaps
        Set<Integer> hGaps = new HashSet<>();
        for (int i = 0; i < h.length; i++) {
            for (int j = i + 1; j < h.length; j++) {
                hGaps.add(Math.abs(h[i] - h[j]));
            }
        }

        // Check vertical gaps against the horizontal gaps
        for (int i = 0; i < v.length; i++) {
            for (int j = i + 1; j < v.length; j++) {
                int gap = Math.abs(v[i] - v[j]);
                if (hGaps.contains(gap)) {
                    maxSide = Math.max(maxSide, gap);
                }
            }
        }

        return maxSide == -1 ? -1 : (int) ((maxSide * maxSide) % MOD);
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        int m = 4, n = 3;
        int[] hFences = {2, 3};
        int[] vFences = {2};
        System.out.println("Max area: " + sol.maximizeSquareArea(m, n, hFences, vFences));
    }
}