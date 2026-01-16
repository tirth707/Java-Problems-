class Solution {
    public int minTimeToVisitAllPoints(int[][] points) {
        int total = 0;
        for (int i = 0; i < points.length - 1; i++) {
            int x1 = points[i][0], y1 = points[i][1];
            int x2 = points[i + 1][0], y2 = points[i + 1][1];
            total += Math.max(Math.abs(x2 - x1), Math.abs(y2 - y1));
        }
        return total;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        int[][] points = {{1, 1}, {3, 4}, {-1, 0}};
        System.out.println("Minimum Time: " + sol.minTimeToVisitAllPoints(points));
    }
}