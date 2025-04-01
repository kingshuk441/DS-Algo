package basics;

public class backtracking {

    public static void main(String[] args) {
//        int arr[][] = {{0, 0, 0, 0, 0, 0}, {0, 1, 0, 1, 1, 0}, {0, 1, 0, 1, 1, 0}, {0, 1, 0, 0, 0, 0,}, {0, 1, 0, 1, 1, 0}, {0, 0, 0, 1, 1, 0}};
//        floodFill(arr, 0, 0, arr.length - 1, arr[0].length - 1, "");
//        int arr[] = {2, 5, 3, 4, 6, 8, -4};
//        targetSumSubset(arr, 8, 0, "");
        int n = 4;
        nQueens(n, 0, new boolean[n][n], "");
    }

    private static void floodFill(int[][] arr, int sr, int sc, int er, int ec, String psf) {
        if (sr < 0 || sr >= er + 1 || sc < 0 || sc >= ec + 1) return;
        if (arr[sr][sc] == 1) return;
        if (sr == er && sc == ec) {
            System.out.println(psf);
            return;
        }
        arr[sr][sc] = 1;
        floodFill(arr, sr - 1, sc, er, ec, psf + "t");
        floodFill(arr, sr, sc - 1, er, ec, psf + "l");
        floodFill(arr, sr + 1, sc, er, ec, psf + "d");
        floodFill(arr, sr, sc + 1, er, ec, psf + "r");
        arr[sr][sc] = 0;
    }

    private static void nQueens(int n, int idx, boolean[][] vis, String psf) {
        if (idx == n) {
            System.out.println(psf);
            return;
        }
        for (int i = 0; i < n; i++) {
            if (isPossibleToPlaceQueen(vis, idx, i)) {
                vis[idx][i] = true;
                nQueens(n, idx + 1, vis, psf + "(" + idx + "," + i + "),");
                vis[idx][i] = false;
            }
        }

    }

    private static boolean isPossibleToPlaceQueen(boolean[][] vis, int r, int c) {
        int[][] dir = {{-1, -1}, {-1, 0}, {-1, 1}, {0, 1}, {1, 1}, {1, 0}, {1, -1}, {0, -1}};
        int n = vis.length;
        for (int[] d : dir) {
            for (int jump = 1; jump <= n; jump++) {
                int x = r + d[0] * jump;
                int y = c + d[1] * jump;
                if (x >= 0 && x < n && y >= 0 && y < n && vis[x][y]) return false;
            }
        }

        return true;
    }

    private static void targetSumSubset(int[] arr, int tar, int idx, String psf) {
        if (tar == 0) {
            System.out.println(psf.substring(0, psf.length() - 1));
            return;
        }
        if (idx == arr.length) {
            return;
        }

        targetSumSubset(arr, tar - arr[idx], idx + 1, psf + arr[idx] + ",");
        targetSumSubset(arr, tar, idx + 1, psf);
    }
}
