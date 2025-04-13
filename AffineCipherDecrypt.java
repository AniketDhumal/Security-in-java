
import java.util.Scanner;

public class AffineCipherDecrypt {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter the encrypted message (uppercase, no spaces): ");
        String encrypted = sc.nextLine().toUpperCase();

        System.out.print("Enter key 'a': ");
        int a = sc.nextInt();

        System.out.print("Enter key 'b': ");
        int b = sc.nextInt();

        int m = 26;

        // Step 1: Check if 'a' is coprime with 26
        int gcd = 1;
        for (int i = 1; i <= a && i <= m; i++) {
            if (a % i == 0 && m % i == 0)
                gcd = i;
        }

        if (gcd != 1) {
            System.out.println("Invalid key: 'a' must be coprime with 26.");
            return;
        }

        // Step 2: Find modular inverse of a
        int a_inv = -1;
        for (int i = 1; i < m; i++) {
            if ((a * i) % m == 1) {
                a_inv = i;
                break;
            }
        }

        if (a_inv == -1) {
            System.out.println("Modular inverse of 'a' does not exist.");
            return;
        }

        // Step 3: Decryption: P = a_inv * (C - b) % 26
        StringBuilder decrypted = new StringBuilder();
        for (int i = 0; i < encrypted.length(); i++) {
            char ch = encrypted.charAt(i);
            if (Character.isLetter(ch)) {
                int C = ch - 'A';
                int P = a_inv * (C - b + 26) % 26;
                decrypted.append((char) (P + 'A'));
            }
        }

        System.out.println("Decrypted text: " + decrypted);
    }
}
