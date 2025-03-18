package basics;

import java.util.Arrays;

public class TwoDArr {
    public static void print(int arr[][]) {
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[0].length; j++) System.out.print(arr[i][j] + "\t");
            System.out.println();
        }
    }

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

    public static void spiralPrint(int arr[][]) {
        int n = arr.length, m = arr[0].length;
        int sr = 0, sc = 0, er = n - 1, ec = m - 1;
        int tEle = n * m;
        while (tEle-- > 0) {
            for (int j = sr; j <= er; j++) {
                tEle--;
                System.out.print(arr[j][sr] + ",");
            }
            sc++;
            for (int j = sc; j <= ec; j++) {
                tEle--;
                System.out.print(arr[er][j] + ",");
            }
            er--;
            for (int j = er; j >= sr; j--) {
                tEle--;
                System.out.print(arr[j][ec] + ",");
            }
            ec--;
            for (int j = ec; j >= sc; j--) {
                tEle--;
                System.out.print(arr[sr][j] + ",");
            }
            sr++;
        }
    }

    public static void transpose(int arr[][]) {
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < i; j++) {
                int temp = arr[i][j];
                arr[i][j] = arr[j][i];
                arr[j][i] = temp;
            }
        }
    }

    public static void rotateBy90(int arr[][]) {
        transpose(arr);
        for (int i = 0; i < arr.length; i++) {
            for (int si = 0, ei = arr[i].length - 1; si < ei; si++, ei--) {
                int temp = arr[i][si];
                arr[i][si] = arr[i][ei];
                arr[i][ei] = temp;
            }
        }
    }

    public static boolean searchInSorted2D(int arr[][], int tar) {
        int n = arr.length, m = arr[0].length;
        int tEle = n * m;
        int si = 0, ei = tEle - 1;
        while (si <= ei) {
            int mid = (si + ei) / 2;
            int i = mid / m;
            int j = mid % m;
            if (arr[i][j] == tar) return true;
            else if (arr[i][j] < tar) si = mid + 1;
            else ei = mid - 1;
        }
        return false;
    }

    public static boolean searchInSorted2DII(int arr[][], int tar) {
        int n = arr.length, m = arr[0].length;
        int i = n - 1, j = 0;
        while (i >= 0 && j < m) {
            if (arr[i][j] == tar) return true;
            else if (arr[i][j] < tar) j++;
            else i--;
        }
        return false;
    }

    public static int saddlePoint(int arr[][]) {
        int n = arr.length, m = arr[0].length;
        for (int i = 0; i < n; i++) {
            int indx = 0;
            for (int j = 0; j < m; j++) {
                if (arr[i][j] < arr[i][indx]) indx = j;
            }
            boolean flag = true;
            for (int j = 0; j < n; j++) {
                if (arr[j][indx] > arr[i][indx]) {
                    flag = false;
                    break;
                }
            }
            if (flag) return arr[i][indx];
        }
        return -1;
    }

    public static void diagonalPrint(int arr[][]) {
        int n = arr.length, m = arr[0].length;
        for (int dia = 0; dia < m; dia++) {
            for (int i = 0, j = dia; i < n && j < m; i++, j++) {
                System.out.print(arr[i][j] + ",");
            }
            System.out.println();
        }
    }


    public static void main(String[] args) {
//        int[][] A = {{9, 4, 5}, {6, 8, -1}, {11, 5, 3}, {7, 14, 5}};
//        int[][] B = {{5, -2}, {4, 5}, {11, 9}};
//        int[][] ans = multiply2Matrices(A, B);
//        int[][] arr = {{11, 22, 4, 45, 110}, {33, 9, 7, 5, 89}, {66, 77, 6, 55, 99}, {13, 1, 43, 88, 44}};
//        int[][] arr = {{11, 22, 4, 45}, {33, 9, 7, 5}, {66, 77, 6, 55}, {13, 1, 43, 88}};
        int[][] arr = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
//        print(arr);
//        wavePrint(arr);
//        spiralPrint(arr);
//        rotateBy90(arr);
//        System.out.println();
//        print(arr);
        System.out.println(saddlePoint(arr));
    }
}
