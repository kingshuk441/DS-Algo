package basics;

public class Str {
    public static String compress(String str) {
        int n = str.length();
        String ans = str.charAt(0) + "";
        char prev = str.charAt(0);
        for (int i = 1; i < n; i++) {
            char ch = str.charAt(i);
            if (prev != ch) {
                ans += ch;
            }
            prev = ch;
        }
        return ans;
    }

    public static String compress2(String str) {
        int n = str.length();
        String ans = str.charAt(0) + "";
        char prev = str.charAt(0);
        for (int i = 1; i < n; i++) {
            char ch = str.charAt(i);
            if (prev != ch) {
                ans += ch;
                prev = ch;

            } else {
                int j = i, count = 1;
                while (j < n && str.charAt(j) == prev) {
                    j++;
                    count++;
                }
                ans += count;
                i = j - 1;
            }

        }
        return ans;
    }

    public static int compress3(char[] arr) {
        int n = arr.length, j = 0, count = 1;
        char prev = arr[0];
        for (int i = 1; i < n; i++) {
            char ch = arr[i];
            if (prev == ch) {
                count++;
            } else {
                if (count > 1) {
                    String str = String.valueOf(count);
                    for (int k = 0; k < str.length(); k++) {
                        arr[++j] = str.charAt(k);
                    }
                }
                arr[++j] = ch;
                prev = ch;
                count = 1;
            }
        }

        if (count > 1) {
            String str = String.valueOf(count);
            for (int k = 0; k < str.length(); k++) {
                arr[++j] = str.charAt(k);
            }
        }
        return j + 1;
    }

    public static void main(String[] args) {
//        System.out.println(compress2("aaabbccccccdeefgghii"));
//        System.out.println(compress3(new char[]{'a', 'a', 'a', 'a', 'b', 'b', 'c', 'd', 'd', 'e', 'f', 'f'}));
        System.out.println(compress3(new char[]{'a', 'b', 'b', 'b', 'b', 'b', 'b', 'b', 'b', 'b', 'b', 'b', 'b'}));
    }
}
