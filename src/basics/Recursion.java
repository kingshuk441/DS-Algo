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
//        ArrayList<String> abc = printSubs("ABC");
        ArrayList<String> abc = getStairPaths(4);
        System.out.println(abc);
    }
}
