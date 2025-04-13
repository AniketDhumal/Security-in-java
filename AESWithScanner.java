import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.util.Scanner;

public class AESWithScanner {
    private static final String ALGORITHM = "AES";

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter 128-bit plaintext (32 hex characters): ");
        String plaintextHex = sc.nextLine().trim();

        System.out.print("Enter 128-bit key (32 hex characters): ");
        String keyHex = sc.nextLine().trim();

        if (plaintextHex.length() != 32 || keyHex.length() != 32) {
            System.out.println("Error: Input must be exactly 32 hex characters (128 bits).");
            return;
        }

        try {
            byte[] plaintext = hexStringToByteArray(plaintextHex);
            byte[] key = hexStringToByteArray(keyHex);

            byte[] encrypted = encrypt(plaintext, key);
            System.out.println("Encrypted (Hex): " + bytesToHex(encrypted));

            byte[] decrypted = decrypt(encrypted, key);
            System.out.println("Decrypted (Hex): " + bytesToHex(decrypted));
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }

        sc.close();
    }

    public static byte[] encrypt(byte[] plaintext, byte[] key) throws Exception {
        Cipher cipher = Cipher.getInstance(ALGORITHM + "/ECB/NoPadding");
        SecretKeySpec secretKey = new SecretKeySpec(key, ALGORITHM);
        cipher.init(Cipher.ENCRYPT_MODE, secretKey);
        return cipher.doFinal(plaintext);
    }

    public static byte[] decrypt(byte[] ciphertext, byte[] key) throws Exception {
        Cipher cipher = Cipher.getInstance(ALGORITHM + "/ECB/NoPadding");
        SecretKeySpec secretKey = new SecretKeySpec(key, ALGORITHM);
        cipher.init(Cipher.DECRYPT_MODE, secretKey);
        return cipher.doFinal(ciphertext);
    }

    public static byte[] hexStringToByteArray(String hex) {
        int len = hex.length();
        byte[] result = new byte[len / 2];
        for (int i = 0; i < len; i += 2) {
            result[i / 2] = (byte) ((Character.digit(hex.charAt(i), 16) << 4)
                                  + Character.digit(hex.charAt(i + 1), 16));
        }
        return result;
    }

    public static String bytesToHex(byte[] bytes) {
        StringBuilder sb = new StringBuilder();
        for (byte b : bytes) sb.append(String.format("%02X", b));
        return sb.toString();
    }
}
