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

    public static ArrayList<String> allMazePaths(int sr, int sc, int er, int ec) {
        if (sr > er || sc > ec) return new ArrayList<>();
        if (sr == er && sc == ec) {
            ArrayList<String> base = new ArrayList<>();
            base.add("");
            return base;
        }
        ArrayList<String> ans = new ArrayList<>();
        ArrayList<String> horizontal = allMazePaths(sr, sc + 1, er, ec);
        for (String s : horizontal) ans.add("H" + s);
        ArrayList<String> vertical = allMazePaths(sr + 1, sc, er, ec);
        for (String s : vertical) ans.add("V" + s);

        return ans;
    }

    public static ArrayList<String> allMazePathsJumps(int sr, int sc, int er, int ec) {
//        if (sr > er || sc > ec) return new ArrayList<>();
        if (sr == er && sc == ec) {
            ArrayList<String> base = new ArrayList<>();
            base.add("");
            return base;
        }
        ArrayList<String> ans = new ArrayList<>();
        for (int jumps = 1; jumps <= ec; jumps++) {
            if (jumps + sc <= ec) {
                ArrayList<String> horizontal = allMazePathsJumps(sr, sc + jumps, er, ec);
                for (String s : horizontal) {
                    ans.add("H" + jumps + s);
                }
            }
        }
        for (int jumps = 1; jumps <= er; jumps++) {
            if (jumps + sr <= er) {
                ArrayList<String> vertical = allMazePathsJumps(sr + jumps, sc, er, ec);
                for (String s : vertical) {
                    ans.add("V" + jumps + s);
                }
            }
        }
        for (int jumps = 1; jumps <= er && jumps <= ec; jumps++) {
            if (jumps + sr <= er & jumps + sc <= ec) {
                ArrayList<String> diag = allMazePathsJumps(sr + jumps, sc + jumps, er, ec);
                for (String s : diag) {
                    ans.add("D" + jumps + s);
                }
            }
        }

        return ans;
    }

    // ON THE WAY UP

    public static void printSubs2(String str, String asf) {
        if (str.length() == 0) {
            System.out.println(asf);
            return;
        }
        char ch = str.charAt(0);
        printSubs2(str.substring(1), asf + ch);
        printSubs2(str.substring(1), asf);
    }

    public static void printKPC2(String str, String asf) {
        if (str.length() == 0) {
            System.out.println(asf);
            return;
        }
        char ch = str.charAt(0);
        int idx = ch - '0';
        String letters = lettersArray[idx];
        for (int i = 0; i < letters.length(); i++) {
            char letter = letters.charAt(i);
            printKPC2(str.substring(1), asf + letter);
        }
    }

    public static void printStairPaths2(int n, String asf) {
        if (n <= 0) {
            if (n == 0)
                System.out.println(asf);

            return;
        }
        printStairPaths2(n - 1, asf + "1");
        printStairPaths2(n - 2, asf + "2");
        printStairPaths2(n - 3, asf + "3");

    }

    public static void main(String[] args) {
//        pdi(5);
//         printPattern(2);
//        ArrayList<String> abc = printSubs("ABC");
//        ArrayList<String> abc = getStairPaths(4);
//        System.out.println(abc);
//        ArrayList<String> strings = allMazePaths(0, 0, 2, 2);
//        ArrayList<String> strings = allMazePathsJumps(0, 0, 2, 2);
//        System.out.println(strings);
//        printSubs2("abc", "");
        printStairPaths2(4, "");
    }
}
