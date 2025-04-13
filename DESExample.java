import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.util.Scanner;
import java.util.Base64;

public class DESExample {
    public static void main(String[] args) {
        try {
            Scanner scanner = new Scanner(System.in);

            // Input plaintext in hexadecimal format
            System.out.print("Enter plaintext (16 hex digits): ");
            String plaintextHex = scanner.nextLine();

            // Input key in hexadecimal format
            System.out.print("Enter key (16 hex digits): ");
            String keyHex = scanner.nextLine();

            // Convert hex inputs to byte arrays
            byte[] plaintext = hexStringToByteArray(plaintextHex);
            byte[] keyBytes = hexStringToByteArray(keyHex);

            // Generate secret key
            SecretKey secretKey = new SecretKeySpec(keyBytes, "DES");

            // Initialize cipher for encryption
            Cipher cipher = Cipher.getInstance("DES/ECB/NoPadding");
            cipher.init(Cipher.ENCRYPT_MODE, secretKey);

            // Perform encryption
            byte[] encryptedText = cipher.doFinal(plaintext);
            System.out.println("Encrypted Text (Hex): " + bytesToHex(encryptedText));

            // Initialize cipher for decryption
            cipher.init(Cipher.DECRYPT_MODE, secretKey);

            // Perform decryption
            byte[] decryptedText = cipher.doFinal(encryptedText);
            System.out.println("Decrypted Text (Hex): " + bytesToHex(decryptedText));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Helper method to convert hex string to byte array
    private static byte[] hexStringToByteArray(String s) {
        int len = s.length();
        byte[] data = new byte[len / 2];
        for (int i = 0; i < len; i += 2) {
            data[i / 2] = (byte) ((Character.digit(s.charAt(i), 16) << 4)
                                 + Character.digit(s.charAt(i+1), 16));
        }
        return data;
    }

    // Helper method to convert byte array to hex string
    private static String bytesToHex(byte[] bytes) {
        StringBuilder sb = new StringBuilder();
        for (byte b : bytes) {
            sb.append(String.format("%02X", b));
        }
        return sb.toString();
    }
}
