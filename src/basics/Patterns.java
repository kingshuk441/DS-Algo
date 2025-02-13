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

    public static void main(String[] args) {
        p2(5);
    }
}
