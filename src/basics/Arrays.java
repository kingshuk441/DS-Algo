package basics;

public class Arrays {
    public static void pattern(int arr[]) {
        int n = arr.length;
        int maxLines = 0;
        for (int i = 0; i < n; i++) {
            maxLines = Math.max(maxLines, arr[i]);
        }
//        int space[] = new int[n];
//        for (int i = 0; i < n; i++) {
//            space[i] = max - arr[i];
//        }
//        while (maxLines-- > 0) {
//            for (int i = 0; i < n; i++) {
//                int ele = space[i];
//                if (ele == 0) System.out.print("*");
//                else {
//                    System.out.print("_");
//                    space[i]--;
//                }
//            }
//            System.out.println();
//        }
        int curr = 1;
        while (curr++ < maxLines) {
            for (int i = 0; i < n; i++) {
                if (maxLines - curr < arr[i]) System.out.print("*");
                else System.out.print("_");
            }
            System.out.println();
        }
    }

    public static int[] sumOfArrays(int arr1[], int arr2[]) {
        int n1 = arr1.length;
        int n2 = arr2.length;
        int maxLen = Math.max(n1, n2);
        int res[] = new int[maxLen + 1];
//        reverse(arr1);
//        reverse(arr2);
        int k = res.length - 1, carry = 0, i = n1 - 1, j = n2 - 1;
        while (carry != 0 || i >= 0 || j >= 0) {
            int sum = carry + (i >= 0 ? arr1[i] : 0) + (j >= 0 ? arr2[j] : 0);
            int rem = sum % 10;
            carry = sum / 10;
            res[k--] = rem;
            i--;
            j--;
        }
//         reverse(res);
        return res;
    }

    public static int[] differenceOfArrays(int arr1[], int arr2[]) {
        int n1 = arr1.length;
        int n2 = arr2.length;
        int maxLen = Math.max(n1, n2);
        int res[] = new int[maxLen + 1];
        int k = res.length - 1, borrow = 0, i = n1 - 1, j = n2 - 1;
        while (borrow != 0 || i >= 0 || j >= 0) {
            int diff = (i >= 0 ? arr1[i] : 0) - (j >= 0 ? arr2[j] : 0) - borrow;
            if (diff < 0) {
                diff += 10;
                borrow = 1;
            } else {
                borrow = 0;
            }

            res[k--] = diff;
            i--;
            j--;
        }
        return res;
    }

    public static void reverse(int arr[]) {
        int i = 0, j = arr.length - 1;
        while (i < j) {
            int temp = arr[i];
            arr[i] = arr[j];
            arr[j] = temp;
            i++;
            j--;
        }
    }

    public static void main(String[] args) {
//        pattern(new int[]{3, 1, 8, 4, 0, 6});
//        int[] sumOfArrays = sumOfArrays(new int[]{1, 1}, new int[]{2, 1, 9, 4, 1, 3, 5});
        int[] differenceOfArrays = differenceOfArrays(new int[]{1, 0, 0}, new int[]{1});
        for (int i = 0; i < differenceOfArrays.length; i++) {
            System.out.print(differenceOfArrays[i] + " ");
        }
    }
}
