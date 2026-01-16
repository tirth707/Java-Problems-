class Solution {
    public double separateSquares(int[][] squares) {
        double minY = Double.MAX_VALUE;
        double maxY = Double.MIN_VALUE;
        double totalArea = 0;

        for (int[] s : squares) {
            double y = s[1];
            double l = s[2];
            minY = Math.min(minY, y);
            maxY = Math.max(maxY, y + l);
            totalArea += l * l;
        }

        double low = minY;
        double high = maxY;
        
        // 100 iterations for high precision
        for (int i = 0; i < 100; i++) {
            double mid = low + (high - low) / 2;
            if (calculateAreaBelow(squares, mid) < totalArea / 2.0) {
                low = mid;
            } else {
                high = mid;
            }
        }

        return low;
    }

    private double calculateAreaBelow(int[][] squares, double mid) {
        double area = 0;
        for (int[] s : squares) {
            double y = s[1];
            double l = s[2];
            double top = y + l;
            
            if (mid <= y) {
                continue; 
            } else if (mid >= top) {
                area += l * l;
            } else {
                area += l * (mid - y);
            }
        }
        return area;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        int[][] squares = {{0,0,1},{2,2,1}};
        System.out.println("Separate line: " + sol.separateSquares(squares));
    }
}