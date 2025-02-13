package basics;

public class Basics {
    public static boolean isPrime(int num) {
        boolean isPrime = true;
        for (int i = 2; i * i <= num; i++) {
            if (num % i == 0) {
                isPrime = false;
                break;
            }
        }

        if (isPrime) {
            System.out.println("This is a prime number.");
        } else {
            System.out.println("This is not a prime number.");
        }
        return isPrime;
    }

    public static int fibonacci(int n) {
        if (n <= 1) return n;
        int a = 0, b = 1;
        for (int i = 2; i <= n; i++) {
            int sum = a + b;
            a = b;
            b = sum;
        }
        return b;
    }

    public static int countDigits(long n) {
        int count = 0;
        while (n != 0) {
            n = n / 10;
            count++;
        }
        return count;
    }

    public static int sumDigits(int n) {
        int ans = 0;
        while (n != 0) {
            ans += n % 10;
            n = n / 10;
        }
        return ans;
    }

    public static int reverseNumber(int n) {
        int ans = 0;

        while (n != 0) {
            int rem = n % 10;
            ans = ans * 10 + rem;
            n = n / 10;
        }
        return ans;
    }

    public static int inverseNumber(int n) {
        int ans = 0;
        int pos = 1;
        while (n != 0) {
            int no = n % 10;
            int pow = pos * (int) Math.pow(10, no - 1);
            ans = ans + pow;
            pos++;
            n = n / 10;
        }
        return ans;
    }

    public static int rotateNumber(int number, int k) {
        int len = countDigits(number);
        k = (k + len) % len;
        int leftSide = number % (int) Math.pow(10, k);
        int rightSide = number / (int) Math.pow(10, k);
        return leftSide * (int) Math.pow(10, len - k) + rightSide;
    }

    // n1 * n2 = gcd * lcm
    public static int lcm(int n1, int n2) {
        return (n1 * n2) / gcd(n1, n2);
    }

    public static int gcd(int n1, int n2) {
//        int maxPossibleGcd = Math.max(n1, n2);
//        int possibleGcd = 1;
//        int ansGcd = 1;
//        while (possibleGcd <= maxPossibleGcd) {
//            if (n1 % possibleGcd == 0 && n2 % possibleGcd == 0) ansGcd = possibleGcd;
//            possibleGcd++;
//        }
//        return ansGcd;
        while (n1 % n2 != 0) {
            int rem = n1 % n2;
            n1 = n2;
            n2 = rem;
        }
        return n2;
    }


    public static void main(String[] args) {
        //136254
        System.out.println(gcd(24, 36));
    }

}
