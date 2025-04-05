package basics;

public class backtracking {

    public static void main(String[] args) {
//        int arr[][] = {{0, 0, 0, 0, 0, 0}, {0, 1, 0, 1, 1, 0}, {0, 1, 0, 1, 1, 0}, {0, 1, 0, 0, 0, 0,}, {0, 1, 0, 1, 1, 0}, {0, 0, 0, 1, 1, 0}};
//        floodFill(arr, 0, 0, arr.length - 1, arr[0].length - 1, "");
//        int arr[] = {2, 5, 3, 4, 6, 8, -4};
//        targetSumSubset(arr, 8, 0, "");
//        int n = 4;
//        nQueens(n, 0, new boolean[n][n], "");
//        knightsTour(3, 4);
        int arr[] = {2, 3, 5, 7};
        int tar = 10;
//        coinChangePermSingle(arr, tar);
//        coinChangePermMultiple(arr,tar);
//        coinChangeComSingle(arr, tar);
        coinChangeComMultiple(arr, tar);
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

    public static void knightsTour(int sr, int sc) {
        int board[][] = new int[8][8];
        int dir[][] = {{-2, -1}, {-2, 1}, {-1, 2}, {1, 2}, {2, 1}, {2, -1}, {1, -2}, {-1, -2}};
        knightsTourRec(sr, sc, dir, board, 0);
    }

    private static void knightsTourRec(int sr, int sc, int[][] dir, int[][] board, int count) {
        if (count == board.length * board[0].length) {
            for (int i = 0; i < board.length; i++) {
                for (int j = 0; j < board[0].length; j++) {
                    System.out.print(board[i][j] + ",");
                }
                System.out.println();
            }
            return;
        }
        board[sr][sc] = count;
        for (int d[] : dir) {
            int r = sr + d[0];
            int c = sc + d[1];
            if (r >= 0 && r < board.length && c >= 0 && c < board[0].length && board[r][c] == 0) {
                knightsTourRec(r, c, dir, board, count + 1);
            }
        }
        board[sr][sc] = 0;
    }

    public static void coinChangePermSingle(int[] arr, int tar) {
        coinChangePermSingle(arr, tar, "", new boolean[arr.length]);
    }

    public static void coinChangePermMultiple(int[] arr, int tar) {
        coinChangePermMultiple(arr, tar, "");
    }

    public static void coinChangeComSingle(int[] arr, int tar) {
        coinChangeComSingle(arr, tar, 0, "");
    }

    public static void coinChangeComMultiple(int[] arr, int tar) {
        coinChangeComMultiple(arr, tar, 0, "");
    }

    private static void coinChangeComMultiple(int[] arr, int tar, int idx, String psf) {
        int n = arr.length;
        if (tar == 0) {
            System.out.println(psf);
            return;
        }
        for (int i = idx; i < n; i++) {
            if (tar - arr[i] >= 0) coinChangeComMultiple(arr, tar - arr[i], i, psf + arr[i]);
        }
    }

    private static void coinChangeComSingle(int[] arr, int tar, int idx, String psf) {
        int n = arr.length;
        if (tar == 0) {
            System.out.println(psf);
            return;
        }
        for (int i = idx; i < n; i++) {
            if (tar - arr[i] >= 0) coinChangeComSingle(arr, tar - arr[i], i + 1, psf + arr[i]);
        }
    }

    private static void coinChangePermMultiple(int[] arr, int tar, String psf) {
        int n = arr.length;
        if (tar == 0) {
            System.out.println(psf);
            return;
        }
        for (int i = 0; i < n; i++) {
            if (tar - arr[i] >= 0) {

                coinChangePermMultiple(arr, tar - arr[i], psf + arr[i] + ",");

            }
        }
    }

    private static void coinChangePermSingle(int[] arr, int tar, String psf, boolean[] vis) {
        int n = arr.length;
        if (tar == 0) {
            System.out.println(psf);
            return;
        }
        for (int i = 0; i < n; i++) {
            if (!vis[i] && tar - arr[i] >= 0) {
                vis[i] = true;
                coinChangePermSingle(arr, tar - arr[i], psf + arr[i] + ",", vis);
                vis[i] = false;
            }
        }

    }
}
