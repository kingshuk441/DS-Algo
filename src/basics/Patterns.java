package basics;

public class Patterns {
    public static void p1(int n) {
        int totalLines = n;
        int currentLines = 1;
        int stars = 1;
        while (currentLines <= totalLines) {
            for (int i = 1; i <= stars; i++) System.out.print("*\t");
            stars++;
            System.out.println();
            currentLines++;
        }
    }

    public static void p2(int n) {
        int totalLines = n;
        int currentLines = 1;
        int stars = n;
        while (currentLines <= totalLines) {
            for (int i = 1; i <= stars; i++) System.out.print("*\t");
            stars--;
            System.out.println();
            currentLines++;
        }
    }

    public static void p3(int n) {
        int totalLines = n;
        int currentLines = 1;
        int stars = 1;
        int space = n - 1;
        while (currentLines <= totalLines) {
            for (int i = 1; i <= space; i++) System.out.print("_\t");
            for (int i = 1; i <= stars; i++) System.out.print("*\t");
            stars++;
            space--;
            System.out.println();
            currentLines++;
        }
    }

    public static void p4(int n) {
        int totalLines = n;
        int currentLines = 1;
        int stars = n;
        int space = 0;
        while (currentLines <= totalLines) {
            for (int i = 1; i <= space; i++) System.out.print("_\t");
            for (int i = 1; i <= stars; i++) System.out.print("*\t");
            stars--;
            space++;
            System.out.println();
            currentLines++;
        }
    }

    public static void p5(int n) {
        int totalLines = n;
        int currentLines = 1;
        int stars = n;
        int space = 0;
        while (currentLines <= totalLines) {
            for (int i = 1; i <= space; i++) System.out.print("_\t");
            for (int i = 1; i <= stars; i++) System.out.print("*\t");
            stars--;
            space++;
            System.out.println();
            currentLines++;
        }
    }

    public static void p6(int n) {
        int totalLines = 2 * n + 1;
        int currentLines = 1;
        int stars = 1;
        int space = n;
        while (currentLines <= totalLines) {
            for (int i = 1; i <= space; i++) System.out.print("_\t");
            for (int i = 1; i <= stars; i++) System.out.print("*\t");

            if (currentLines <= n) {
                stars += 2;
                space--;
            } else {
                space++;
                stars -= 2;
            }
            System.out.println();
            currentLines++;
        }
    }

    public static void main(String[] args) {
        p6(3);
    }
}
