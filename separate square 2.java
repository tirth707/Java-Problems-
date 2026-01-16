import java.util.*;

class Solution {
    public double separateSquares(int[][] squares) {
        Set<Integer> xCoords = new TreeSet<>();
        Set<Integer> yCoords = new TreeSet<>();
        for (int[] s : squares) {
            xCoords.add(s[0]);
            xCoords.add(s[0] + s[2]);
            yCoords.add(s[1]);
            yCoords.add(s[1] + s[2]);
        }

        List<Integer> ux = new ArrayList<>(xCoords);
        Map<Integer, Integer> xMap = new HashMap<>();
        for (int i = 0; i < ux.size(); i++) xMap.put(ux.get(i), i);

        List<Integer> uy = new ArrayList<>(yCoords);
        List<double[]> slices = new ArrayList<>();
        double totalArea = 0;

        for (int i = 0; i < uy.size() - 1; i++) {
            int yLow = uy.get(i);
            int yHigh = uy.get(i + 1);
            List<int[]> intervals = new ArrayList<>();
            for (int[] s : squares) {
                if (s[1] <= yLow && s[1] + s[2] >= yHigh) {
                    intervals.add(new int[]{s[0], s[0] + s[2]});
                }
            }

            double width = calculateUnionWidth(intervals);
            double area = width * (yHigh - yLow);
            slices.add(new double[]{yLow, yHigh, width, area});
            totalArea += area;
        }

        double target = totalArea / 2.0;
        double low = uy.get(0);
        double high = uy.get(uy.size() - 1);

        for (int i = 0; i < 60; i++) {
            double mid = low + (high - low) / 2.0;
            if (getAreaBelow(slices, mid) < target) {
                low = mid;
            } else {
                high = mid;
            }
        }

        return low;
    }

    private double calculateUnionWidth(List<int[]> intervals) {
        if (intervals.isEmpty()) return 0;
        intervals.sort((a, b) -> Integer.compare(a[0], b[0]));
        double totalWidth = 0;
        int currStart = intervals.get(0)[0];
        int currEnd = intervals.get(0)[1];

        for (int i = 1; i < intervals.size(); i++) {
            if (intervals.get(i)[0] < currEnd) {
                currEnd = Math.max(currEnd, intervals.get(i)[1]);
            } else {
                totalWidth += (currEnd - currStart);
                currStart = intervals.get(i)[0];
                currEnd = intervals.get(i)[1];
            }
        }
        totalWidth += (currEnd - currStart);
        return totalWidth;
    }

    private double getAreaBelow(List<double[]> slices, double y) {
        double area = 0;
        for (double[] slice : slices) {
            if (y <= slice[0]) break;
            if (y >= slice[1]) {
                area += slice[3];
            } else {
                area += (y - slice[0]) * slice[2];
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