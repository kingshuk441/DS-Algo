package basics;

public class TwoDArr {
    public static int[][] multiply2Matrices(int A[][], int B[][]) {
        int x = A.length;
        int y = A[0].length;
        int z = B[0].length;
        int ans[][] = new int[x][z];

        for (int i = 0; i < ans.length; i++) {
            for (int j = 0; j < ans[0].length; j++) {
                int currAns = 0;
                for (int k = 0; k < y; k++) {
                    currAns += A[i][k] * B[k][j];
                }
                ans[i][j] = currAns;
            }
        }
        return ans;
    }

    public static void wavePrint(int arr[][]) {
        int n = arr.length, m = arr[0].length;
        for (int j = 0; j < m; j++) {
            if (j % 2 == 0) {
                for (int i = 0; i < n; i++) {
                    System.out.print(arr[i][j] + "\t");
                }
            } else {
                for (int i = n - 1; i >= 0; i--) {
                    System.out.print(arr[i][j] + "\t");
                }
            }
            System.out.println();
        }
    }

    public static void exitPoint(int arr[][], int r, int c) {
        int n = arr.length, m = arr[0].length, dir = 0;
        while (r >= 0 && r < n && c >= 0 && c < m) {
            if (arr[r][c] == 1) {
                dir++;
            }
            dir = dir % 4;
            if (dir == 0) {
                c++;
            } else if (dir == 1) {
                r++;
            } else if (dir == 2) {
                c--;
            } else {
                r--;
            }
        }
        if (dir == 0) {
            c--;
        } else if (dir == 1) {
            r--;
        } else if (dir == 2) {
            c++;
        } else {
            r++;
        }
        System.out.println(r);
        System.out.println(c);
    }

    public static void main(String[] args) {
//        int[][] A = {{9, 4, 5}, {6, 8, -1}, {11, 5, 3}, {7, 14, 5}};
//        int[][] B = {{5, -2}, {4, 5}, {11, 9}};
//        int[][] ans = multiply2Matrices(A, B);

        int[][] arr = {{11, 22, 4, 45, 110}, {33, 9, 7, 5, 89}, {66, 77, 6, 55, 99}, {13, 1, 43, 88, 44}};
        wavePrint(arr);
//        for (int i = 0; i < ans.length; i++) {
//            for (int j = 0; j < ans[0].length; j++) System.out.print(ans[i][j] + "\t");
//            System.out.println();
//        }
    }
}
