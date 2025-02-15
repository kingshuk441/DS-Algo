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

    public static void p7(int n) {
        int space = 1;
        int stars = n + 1;
        int currentLines = 1;
        int totalLines = n;
        while (currentLines <= totalLines) {
            for (int i = 1; i <= stars / 2; i++) System.out.print("*\t");
            for (int i = 1; i <= space; i++) System.out.print("_\t");
            for (int i = 1; i <= stars / 2; i++) System.out.print("*\t");
            if (currentLines <= n / 2) {
                space += 2;
                stars -= 2;
            } else {
                space -= 2;
                stars += 2;
            }
            System.out.println();
            currentLines++;
        }
    }

    public static void p8(int n) {
//        int space = 0;
//        int stars = 1;
//        int currentLines = 1;
//        int totalLines = n;
//        while (currentLines <= totalLines) {
//            for (int i = 1; i <= space; i++) System.out.print("_\t");
//            for (int i = 1; i <= stars; i++) System.out.print("*\t");
//            space++;
//            System.out.println();
//            currentLines++;
//        }
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                if (i == j)
                    System.out.print("*\t");
                else System.out.print("_\t");

            }
            System.out.println();
        }
    }

    public static void p9(int n) {
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                if (i + j == n + 1)
                    System.out.print("*\t");
                else System.out.print("_\t");

            }
            System.out.println();
        }
    }

    public static void p10(int n) {
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                if (i + j == n + 1 || i == j)
                    System.out.print("*\t");
                else System.out.print("_\t");

            }
            System.out.println();
        }
    }

    public static void p11(int n) {
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                if (i + j == n + 1 || i == j)
                    System.out.print("*\t");
                else System.out.print("_\t");

            }
            System.out.println();
        }
    }

    public static void p12(int n) {
        int os = n / 2, is = -1;
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {


//            for (int j = 1; j <= os; j++) System.out.print("_\t");
//            System.out.print("*\t");
//            for (int j = 1; j <= is; j++) System.out.print("_\t");
//            if (i > 1 && i < n) System.out.print("*\t");
//            if (i <= n / 2) {
//                os -= 1;
//                is += 2;
//            } else {
//                os += 1;
//                is -= 2;
//            }
                if (i + j == (n / 2) + 2 || i - j == n / 2 || j - i == n / 2 || j + i == (n * 2 - (n / 2)))
                    System.out.print("*\t");
                else System.out.print("_\t");
            }
            System.out.println();
        }
    }

    public static void p13(int n) {
        int num = 1;
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= i; j++) System.out.print(num++ + "\t");
            System.out.println();
        }
    }

    public static void p14(int n) {
        int a = 0, b = 1;
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= i; j++) {
                System.out.print(a + "\t");
                int c = a + b;
                a = b;
                b = c;
            }
            System.out.println();
        }
    }

    public static void p15(int n) {
        int os = n, startingNumber = 1, stars = 1;
        for (int i = 1; i <= 2 * n + 1; i++) {
            for (int j = 1; j <= os; j++) System.out.print("_\t");
            int num = startingNumber;
            for (int j = 1; j <= stars; j++) {
                if (j <= stars / 2) {
                    System.out.print(num + "\t");
                    num++;
                } else {
                    System.out.print(num + "\t");
                    num--;
                }
            }

            if (i <= n) {
                os -= 1;
                stars += 2;
                startingNumber++;
            } else {
                stars -= 2;
                startingNumber--;
                os += 1;
            }
            System.out.println();
        }
    }


    public static void main(String[] args) {
        p15(2);
    }
}
