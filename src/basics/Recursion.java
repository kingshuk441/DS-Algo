package basics;

import java.util.ArrayList;
import java.util.List;

public class Recursion {
    public static void pdi(int n) {
        if (n == 1) {
            System.out.println(n);
            return;
        }
        System.out.println(n);
        pdi(n - 1);
        System.out.println(n);

    }

    public static int pow(int x, int y) {
        if (y == 0) return 1;
        int sAns = pow(x, y / 2);
        int ans = sAns * sAns;
        if (y % 2 != 0) ans *= x;
        return ans;
    }

    public static void printPattern(int n) {
        if (n == 0) return;
        System.out.print(n);
        printPattern(n - 1);
        System.out.print(n);
        printPattern(n - 1);
        System.out.print(n);

    }

    public static void TOH(int n, char from, char to, char via) {
        if (n == 0) return;
        TOH(n - 1, from, via, to);
        System.out.println("Moving disc " + n + " from " + from + " -> " + to);
        TOH(n - 1, via, to, from);

    }

    public static ArrayList<String> printSubs(String str) {
        if (str.length() == 0) {
            ArrayList<String> b = new ArrayList<>();
            b.add(str);
            return b;
        }
        char ch = str.charAt(0);
        ArrayList<String> sAns = printSubs(str.substring(1));
        ArrayList<String> ans = new ArrayList<>(sAns);
        for (String s : sAns) {
            ans.add(ch + s);
        }
        return ans;

    }

    static String[] lettersArray = {",:", "<;", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};

    public static ArrayList<String> getKPC(String str) {
        if (str.length() == 0) {
            ArrayList<String> b = new ArrayList<>();
            b.add("");
            return b;
        }
        char ch = str.charAt(0);
        ArrayList<String> sAns = getKPC(str.substring(1));
        int idx = ch - '0';
        ArrayList<String> ans = new ArrayList<>();
        String letters = lettersArray[idx];
        for (int i = 0; i < letters.length(); i++) {
            for (String s : sAns) {
                ans.add(s + letters.charAt(i));
            }
        }
        return ans;
    }

    public static ArrayList<String> getStairPaths(int n) {
        if (n == 0) {
            ArrayList<String> b = new ArrayList<>();
            b.add("");
            return b;
        }
        if (n < 0) return new ArrayList<>();
        ArrayList<String> ans = new ArrayList<>();
        ArrayList<String> one = getStairPaths(n - 1);
        for (String s : one) ans.add("1" + s);
        ArrayList<String> two = getStairPaths(n - 2);
        for (String s : two) ans.add("2" + s);
        ArrayList<String> three = getStairPaths(n - 3);
        for (String s : three) ans.add("3" + s);
        return ans;
    }

    public static void main(String[] args) {
//        pdi(5);
//         printPattern(2);
//        ArrayList<String> abc = printSubs("ABC");
//        ArrayList<String> abc = getStairPaths(4);
//        System.out.println(abc);
    }
}
