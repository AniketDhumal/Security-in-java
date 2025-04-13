import java.util.*;

public class HillCipher {

    static final int MOD = 26;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Input Key Matrix (2x2)
        int[][] key = new int[2][2];
        System.out.println("Enter 2x2 key matrix values (row-wise):");
        for (int i = 0; i < 2; i++)
            for (int j = 0; j < 2; j++)
                key[i][j] = sc.nextInt();

        // Validate invertibility
        int det = (key[0][0] * key[1][1] - key[0][1] * key[1][0]) % MOD;
        if (det < 0) det += MOD;

        int detInv = modInverse(det, MOD);
        if (detInv == -1) {
            System.out.println("Key matrix is not invertible mod 26. Choose another key.");
            return;
        }

        // Input Plaintext
        System.out.print("Enter plaintext (length 2*n, uppercase only): ");
        String plaintext = sc.next().toUpperCase().replaceAll("[^A-Z]", "");

        if (plaintext.length() % 2 != 0)
            plaintext += "X"; // Padding

        // Encrypt
        String ciphertext = encrypt(plaintext, key);
        System.out.println("Encrypted text: " + ciphertext);

        // Decrypt
        String decrypted = decrypt(ciphertext, key);
        System.out.println("Decrypted text: " + decrypted);
    }

    // Modular Inverse (for determinant)
    static int modInverse(int a, int m) {
        a = a % m;
        for (int x = 1; x < m; x++)
            if ((a * x) % m == 1)
                return x;
        return -1;
    }

    // Encrypt function
    static String encrypt(String plaintext, int[][] key) {
        StringBuilder result = new StringBuilder();

        for (int i = 0; i < plaintext.length(); i += 2) {
            int[] vec = new int[2];
            vec[0] = plaintext.charAt(i) - 'A';
            vec[1] = plaintext.charAt(i + 1) - 'A';

            int c1 = (key[0][0] * vec[0] + key[0][1] * vec[1]) % MOD;
            int c2 = (key[1][0] * vec[0] + key[1][1] * vec[1]) % MOD;

            result.append((char) (c1 + 'A'));
            result.append((char) (c2 + 'A'));
        }

        return result.toString();
    }

    // Decrypt function
    static String decrypt(String ciphertext, int[][] key) {
        int[][] invKey = inverseKey(key);
        if (invKey == null) return "Invalid inverse.";

        StringBuilder result = new StringBuilder();

        for (int i = 0; i < ciphertext.length(); i += 2) {
            int[] vec = new int[2];
            vec[0] = ciphertext.charAt(i) - 'A';
            vec[1] = ciphertext.charAt(i + 1) - 'A';

            int p1 = (invKey[0][0] * vec[0] + invKey[0][1] * vec[1]) % MOD;
            int p2 = (invKey[1][0] * vec[0] + invKey[1][1] * vec[1]) % MOD;

            if (p1 < 0) p1 += MOD;
            if (p2 < 0) p2 += MOD;

            result.append((char) (p1 + 'A'));
            result.append((char) (p2 + 'A'));
        }

        return result.toString();
    }

    // Inverse of 2x2 matrix mod 26
    static int[][] inverseKey(int[][] key) {
        int det = (key[0][0] * key[1][1] - key[0][1] * key[1][0]) % MOD;
        if (det < 0) det += MOD;

        int detInv = modInverse(det, MOD);
        if (detInv == -1) return null;

        int[][] inv = new int[2][2];
        inv[0][0] = key[1][1] * detInv % MOD;
        inv[0][1] = (-key[0][1] + MOD) * detInv % MOD;
        inv[1][0] = (-key[1][0] + MOD) * detInv % MOD;
        inv[1][1] = key[0][0] * detInv % MOD;

        return inv;
    }
}
