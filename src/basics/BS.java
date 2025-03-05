package basics;

public class BS {
    public static int binarySearch(int arr[], int tar) {
        int si = 0, n = arr.length, ei = n - 1;

        while (si <= ei) {
            int mid = (si + ei) / 2;
            if (arr[mid] == tar) return mid;
            else if (arr[mid] < tar) si = mid + 1;
            else ei = mid - 1;
        }
        return -1;
    }

    public static int[] ceilFloor(int arr[], int tar) {
        int si = 0, n = arr.length, ei = n - 1;
        int ceil = Integer.MIN_VALUE, floor = Integer.MAX_VALUE;

        while (si <= ei) {
            int mid = (si + ei) / 2;
            if (arr[mid] == tar) return new int[]{tar, tar};
            else if (arr[mid] < tar) {
                floor = arr[mid];
                si = mid + 1;
            } else {
                ceil = arr[mid];
                ei = mid - 1;
            }
        }
        return new int[]{floor, ceil};
    }

    public static int firstIndex(int arr[], int tar) {
        int si = 0, n = arr.length, ei = n - 1;
        while (si < ei) {
            int mid = (si + ei) / 2;
            if (arr[mid] >= tar) ei = mid;
            else si = mid + 1;
        }
        return arr[si] == tar ? si : -1;
    }

    public static int lastIndex(int arr[], int tar) {
        int si = 0, n = arr.length, ei = n - 1;
        while (si <= ei) {
            int mid = (si + ei) / 2;
            if (arr[mid] > tar) ei = mid - 1;
            else si = mid + 1;
        }
        System.out.println(ei);
        return (ei >= 0 && arr[ei] == tar) ? ei : -1;
    }

    public static int[] firstLastIdx(int arr[], int tar) {
        return new int[]{firstIndex(arr, tar),lastIndex(arr,tar)};
    }

    public static void main(String[] args) {
//        int arr[] = {2, 3, 7, 9, 11, 16, 27, 33, 41};
//        int[] ceilFloorValues = ceilFloor(arr, 1);
//        int arr[] = {1, 2, 2, 2, 2, 3, 3, 3, 4, 4, 4, 4, 4, 4, 5, 6, 6, 6, 7, 7, 8, 8, 8, 9, 10, 10};
        int arr[] = {1};
//        int[] idxs = firstLastIdx(arr, 1);
//        for (int e : idxs) System.out.println(e);
        System.out.println(lastIndex(arr, 1));
    }
}
